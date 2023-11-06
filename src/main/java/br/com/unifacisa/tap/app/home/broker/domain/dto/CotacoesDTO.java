package br.com.unifacisa.tap.app.home.broker.domain.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CotacoesDTO {
	
	private String ativo;
	
	private Double valor;
	
	private Double valorPorLote;
	
	private LocalDate data;

}
