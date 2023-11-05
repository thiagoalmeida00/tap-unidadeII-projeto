package br.com.unifacisa.tap.app.home.broker.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_BOV_COTACOES_ATIVO")
public class CotacoesBovespa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAtivo;
	
	@Column(name = "ATIVO")
	private String ativo;
	
	@Column(name = "VALOR")
	private Double valor;
	
	@Column(name = "DATA")
	private LocalDate data;
	
}
