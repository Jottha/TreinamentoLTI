package br.cesed.unifacisa.lti.controller;

import java.util.List;
import java.util.Optional;

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

import br.cesed.unifacisa.lti.domain.Pessoa;
import br.cesed.unifacisa.lti.exception.ChamadaInvalidaException;
import br.cesed.unifacisa.lti.exception.NomeInvalidoException;
import br.cesed.unifacisa.lti.services.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/pessoa", description = "shows pessoas")
@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	@ApiOperation(value = "Ler todas as pessoas")
	@GetMapping
	public ResponseEntity<List<Pessoa>> readAllPessoa(Pageable pageable) {

		List<Pessoa> pessoas = pessoaService.readAllPessoa(pageable);
		if (pessoas.isEmpty()) {
			return new ResponseEntity<List<Pessoa>>(pessoaService.readAllPessoa(pageable), HttpStatus.OK);
		}
		return new ResponseEntity<List<Pessoa>>(pessoas, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Ler pessoa por ID")	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Optional<Pessoa>> readPessoaId(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(pessoaService.readPessoaId(id));
	}
	@ApiOperation(value = "Cria uma pessoa")
	@PostMapping
	public ResponseEntity<Pessoa> createPessoa(@Valid @RequestBody Pessoa pessoa) {
		Pessoa body;
		try {
			body = pessoaService.createPessoa(pessoa);
		} catch (ChamadaInvalidaException e) {
			return ResponseEntity.badRequest().header("error", e.getMessage()).build();
		}catch(NomeInvalidoException e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).header("error", e.getMessage()).build();
		}
		return new ResponseEntity<Pessoa>(body, HttpStatus.CREATED);
	}
	@ApiOperation(value = "Atualiza uma pessoa recebendo o ID")
	@PutMapping(path = "/{id}")
	public ResponseEntity<Pessoa> updatePessoa(@PathVariable(name = "id") Long id,
			@Valid @RequestBody Pessoa pessoa) {
		pessoa.setId(id);
		return ResponseEntity.ok(pessoaService.updatePessoa(pessoa));
	}
	@ApiOperation(value = "Deleta uma pessoa recebendo um ID ")
	@DeleteMapping(path = "/{id}")
	public void delete (@PathVariable(name = "id") Long id){
		this.pessoaService.delete(id);
	}
}