package br.com.unifacisa.tap.app.home.broker.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.unifacisa.tap.app.home.broker.domain.CotacoesBovespa;
import br.com.unifacisa.tap.app.home.broker.domain.dto.CotacoesDTO;
import br.com.unifacisa.tap.app.home.broker.repository.CotacoesBovespaRepository;

@Service
@Validated
public class CotacoesService {

	@Autowired
	CotacoesBovespaRepository cotacoesBovespaRepository;

	@Autowired
	private ModelMapper modelMapper;

	private static final Integer COMPRA_POR_LOTE = 100;

	public List<CotacoesBovespa> listarTodasCotacoes() {
		return cotacoesBovespaRepository.findAll();
	}

	public List<CotacoesDTO> listarCotacoesDisponiveisPorMontanteDTO(Double montante) {
		List<CotacoesBovespa> todasCotacoes = listarTodasCotacoes();
		List<CotacoesDTO> cotacoesConvertidasDTO = new ArrayList<CotacoesDTO>();
		List<CotacoesDTO> cotacoesDisponiveisPorMontanteDTO = new ArrayList<CotacoesDTO>();

		// multiplica por 100 o valor de cada ativo e converte para retornar o DTO;
		for (int i = 0; i < todasCotacoes.size(); i++) {

			cotacoesConvertidasDTO.add(modelMapper.map(todasCotacoes.get(i), CotacoesDTO.class));
			cotacoesConvertidasDTO.get(i).setValorPorLote(todasCotacoes.get(i).getValor() * COMPRA_POR_LOTE);

		}

		for (int i = 0; i < cotacoesConvertidasDTO.size(); i++) {

			if (montante >= cotacoesConvertidasDTO.get(i).getValorPorLote()) {
				cotacoesDisponiveisPorMontanteDTO.add(cotacoesConvertidasDTO.get(i));

			}
		}

		return cotacoesDisponiveisPorMontanteDTO;
	}

}
