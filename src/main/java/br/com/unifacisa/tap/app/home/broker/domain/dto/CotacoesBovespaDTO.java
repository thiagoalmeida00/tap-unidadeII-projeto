package br.com.unifacisa.tap.app.home.broker.domain.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder
public class CotacoesBovespaDTO {

	@JsonProperty
	private boolean success;
	
	@JsonProperty
	private Map<String, CotacaoDTO> data;
	
}
