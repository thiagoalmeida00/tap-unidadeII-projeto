package br.com.unifacisa.tap.app.home.broker.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder
public class CotacaoDTO {

	@JsonProperty
	private Double ULT;
	
	@JsonProperty
	private String HOR;
	
}
