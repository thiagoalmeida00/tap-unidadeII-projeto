package br.com.unifacisa.tap.app.home.broker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.unifacisa.tap.app.home.broker.domain.dto.CotacoesDTO;
import br.com.unifacisa.tap.app.home.broker.service.CotacoesService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/api")
public class CotacaoController {

	@Autowired
	CotacoesService cotacoesService;

	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			content = { @Content(schema = @Schema(implementation = CotacoesDTO.class),
			mediaType = "application/json") }),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado")
	})
	@GetMapping(value = "/cotacoes/{montante}")
	public ResponseEntity<List<CotacoesDTO>> listarCotacoesDisponiveis(@PathVariable Double montante) {
		List<CotacoesDTO> cotacoesDisponiveis = cotacoesService.listarCotacoesDisponiveisPorMontanteDTO(montante);

		if (cotacoesDisponiveis != null && cotacoesDisponiveis.isEmpty()) {
			return new ResponseEntity<List<CotacoesDTO>>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<CotacoesDTO>>(cotacoesDisponiveis, HttpStatus.OK);
		}
	}

}
