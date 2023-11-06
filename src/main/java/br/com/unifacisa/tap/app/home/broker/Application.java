/**
 * Sistemas de Informação - Tópicos Avançados em Programação
 * Professor: Ruan Oliveira
 * 
 * Projeto Unidade II - Aplicação 01: Sistema Home Broker
 * 
 * [Equipe 01]
 * @author Allan Dellon @author Bruno Andrade
 * @author Lucas Lago @author Thiago Almeida
 * 
 * [Equipe 02]
 * @author Genildo Júnior @author José Maykon
 * @author Diogo Clemente @author Tales Noberto
 * 
 */

package br.com.unifacisa.tap.app.home.broker;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
