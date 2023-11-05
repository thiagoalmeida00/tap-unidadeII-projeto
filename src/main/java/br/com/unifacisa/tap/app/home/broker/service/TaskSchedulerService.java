package br.com.unifacisa.tap.app.home.broker.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unifacisa.tap.app.home.broker.domain.CotacoesBovespa;
import br.com.unifacisa.tap.app.home.broker.domain.dto.CotacaoDTO;
import br.com.unifacisa.tap.app.home.broker.repository.CotacoesBovespaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TaskSchedulerService {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@Autowired
	private CotacoesBovespaRepository cotacoesBovespaRepository;
	
	public void salvarAtivo(Map<String, CotacaoDTO> cotacaoBovespa) {
		for (String ativo : cotacaoBovespa.keySet()) {
			CotacoesBovespa cotacao = cotacoesBovespaRepository.findByAtivoEquals(ativo);
			
			if (cotacao != null) {
				cotacao.setValor(cotacaoBovespa.get(ativo).getULT());
				cotacao.setData(LocalDate.parse(cotacaoBovespa.get(ativo).getHOR(), formatter));
				cotacoesBovespaRepository.save(cotacao);
				log.info("Ativo: " + ativo + " atualizado com sucesso");
			} else {
				CotacoesBovespa novaCotacao = new CotacoesBovespa();
				novaCotacao.setAtivo(ativo);
				novaCotacao.setValor(cotacaoBovespa.get(ativo).getULT());
				novaCotacao.setData(LocalDate.parse(cotacaoBovespa.get(ativo).getHOR(), formatter));
				cotacoesBovespaRepository.save(novaCotacao);
				log.info("Ativo: " + ativo + " salvo com sucesso");
			}
		}		
	}
}
