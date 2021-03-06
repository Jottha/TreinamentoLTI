package br.cesed.unifacisa.lti.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.cesed.unifacisa.lti.domain.Trabalho;
import br.cesed.unifacisa.lti.services.TrabalhoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/trabalho", description = "shows trabalhos")
@RestController
@RequestMapping(value = "/api/trabalho")
public class TrabalhoController {
	
	@Autowired
	private TrabalhoService trabalhoService;
	
	@ApiOperation(value = "Ler todas as trabalhos")
	@GetMapping
	public ResponseEntity<List<Trabalho>> readAllPortfolio(Pageable pageable){
		
		List<Trabalho> trabalhos = trabalhoService.readAllTrabalho(pageable);
		if(trabalhos.isEmpty()) {
			return new ResponseEntity<List<Trabalho>>(trabalhoService.readAllTrabalho(pageable), HttpStatus.OK);
		}
		return new ResponseEntity<List<Trabalho>>(trabalhos, HttpStatus.OK);
	}
	@ApiOperation(value = "Ler trabalho por ID")
	@GetMapping(path = "/{id}")
	public ResponseEntity<Trabalho> readTrabalhoId(@PathVariable(name = "id") Long id){
		return ResponseEntity.ok(trabalhoService.readById(id));
	}
	@ApiOperation(value = "Cria uma trabalho")
	@PostMapping(path = "/{id}")
	public ResponseEntity<Trabalho> createTrabalho(@PathVariable(name = "id") Long id,
			@Valid @RequestBody Trabalho trabalho){
		trabalho.setId(id);
		return ResponseEntity.ok(trabalhoService.updateTrabalho(trabalho));
	}
	@ApiOperation(value = "Atualiza um trabalho recebendo o ID")
	@PutMapping(path = "/{id}")
	public ResponseEntity<Trabalho> updateTrabalho(@PathVariable(name = "id") Long id,
			@Valid @RequestBody Trabalho trabalho) {
		trabalho.setId(id);
		return ResponseEntity.ok(trabalhoService.updateTrabalho(trabalho));
	}
	@ApiOperation(value = "Deleta um trabalho recebendo um ID ")
	@DeleteMapping(path = "/{id}")
	public void delete (@PathVariable(name = "id") Long id){
		this.trabalhoService.delete(id);
	}
}