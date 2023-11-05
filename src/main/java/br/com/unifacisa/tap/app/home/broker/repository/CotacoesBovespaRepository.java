package br.com.unifacisa.tap.app.home.broker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unifacisa.tap.app.home.broker.domain.CotacoesBovespa;

public interface CotacoesBovespaRepository extends JpaRepository<CotacoesBovespa, Integer> {
	
	CotacoesBovespa findByAtivoEquals(String ativo);

}
