package br.com.unifacisa.tap.app.home.broker.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.unifacisa.tap.app.home.broker.domain.dto.CotacaoDTO;
import br.com.unifacisa.tap.app.home.broker.domain.dto.CotacoesBovespaDTO;
import br.com.unifacisa.tap.app.home.broker.service.TaskSchedulerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("scheduler")
public class TaskScheduler {

	private static final String SITE_BOVESPA = "https://opcoes.net.br/opcoes2/bovespa";
	private static final String API_ATIVO_BOVESPA = "https://opcoes.net.br/cotacoes?ativos=";
	
	@Autowired
	private TaskSchedulerService taskSchedulerService;
	
	@Scheduled(fixedRate = 300000) /* Executa a cada 5 minutos*/
	private void retornaAtivosBovespa() {
		
		List<String> ativosLista = new ArrayList<String>();
		
		try {
			final String url = SITE_BOVESPA;
			RestTemplate client = new RestTemplate();
			String retornoHtml = client.getForObject(url, String.class);
			log.info("Acesso ao site: " + url);
			
			ativosLista = mapeaAtivoHtml(retornoHtml);
			retornaDadosAtivo(ativosLista);
			log.info("TaskScheduler finalizado com sucesso");		
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<String> mapeaAtivoHtml(String retornoHtml) {
		
		List<String> ativosLista = new ArrayList<>();
		Document doc = Jsoup.parse(retornoHtml);
		Element opcaoTA = doc.select("option[value=TA]").first();
		
		if (opcaoTA != null) {
			String dataAcoes = opcaoTA.attr("data-acoes");
			String[] ativos = dataAcoes.split(",");
			for (String ativo : ativos) {
				ativosLista.add(ativo);
			}
		}
		
		return ativosLista;
	}
	
	private void retornaDadosAtivo(List<String> ativosLista) {
		for (String ativo : ativosLista) {
			final String url = API_ATIVO_BOVESPA + ativo;
			RestTemplate client = new RestTemplate();
			CotacoesBovespaDTO ativoRetorno = client.getForObject(url, CotacoesBovespaDTO.class);
			Map<String, CotacaoDTO> cotacao = ativoRetorno.getData();
			
			if (ativoRetorno.isSuccess()) {
				taskSchedulerService.salvarAtivo(cotacao);
			} else {
				log.error("Erro ao obter resposta da api para o ativo: " + ativo);
			}
		}
		log.info("Processo de consulta à API finalizado com sucesso!");
	}
	
}
