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

import br.cesed.unifacisa.lti.domain.Portfolio;
import br.cesed.unifacisa.lti.services.PortfolioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/portfolio", description = "shows portfolios")
@RestController
@RequestMapping(value = "/api/portfolio")
public class PortfolioController {
	
	@Autowired
	private PortfolioService portfolioService;
	
	@ApiOperation(value = "Ler todas as portfolios")
	@GetMapping
	public ResponseEntity<List<Portfolio>> readAllPortfolio(Pageable pageable){
		
		List<Portfolio> portfolios = portfolioService.readAllPortifolio(pageable);
		if(portfolios.isEmpty()) {
			return new ResponseEntity<List<Portfolio>>(portfolioService.readAllPortifolio(pageable), HttpStatus.OK);
		}
		return new ResponseEntity<List<Portfolio>>(portfolios, HttpStatus.OK);
	}
	@ApiOperation(value = "Ler portfolio por ID")
	@GetMapping(path = "/{id}")
	public ResponseEntity<Portfolio> readPortfolioId(@PathVariable(name = "id") Long id){
		return ResponseEntity.ok(portfolioService.readById(id));
	}
	@ApiOperation(value = "Cria um portfolio")
	@PostMapping
	public ResponseEntity<Portfolio> createPortfolio(Portfolio portfolio){
		return ResponseEntity.ok(portfolioService.createPortfolio(portfolio));
	}
	@ApiOperation(value = "Atualiza um portfolio recebendo o ID")
	@PutMapping(path = "/{id}")
	public ResponseEntity<Portfolio> updatePortfolio(@PathVariable(name = "id") Long id,
			@Valid @RequestBody Portfolio portfolio) {
		portfolio.setId(id);
		return ResponseEntity.ok(portfolioService.updatePortfolio(portfolio));
	}
	@ApiOperation(value = "Deleta um portfolio recebendo um ID ")
	@DeleteMapping(path = "/{id}")
	public void delete (@PathVariable(name = "id") Long id){
		this.portfolioService.delete(id);
	}
}