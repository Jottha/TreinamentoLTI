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

@RestController
@RequestMapping(value = "/portfolio")
public class PortfolioController {
	
	@Autowired
	private PortfolioService portfolioService;
	
	@GetMapping
	public ResponseEntity<List<Portfolio>> readAllPortfolio(Pageable pageable){
		
		List<Portfolio> portfolios = portfolioService.readAllPortifolio(pageable);
		if(portfolios.isEmpty()) {
			return new ResponseEntity<List<Portfolio>>(portfolioService.readAllPortifolio(pageable), HttpStatus.OK);
		}
		return new ResponseEntity<List<Portfolio>>(portfolios, HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Portfolio> readPortfolioId(@PathVariable(name = "id") Long id){
		return ResponseEntity.ok(portfolioService.readById(id));
	}
	
	@PostMapping
	public ResponseEntity<Portfolio> createPortfolio(Portfolio portfolio){
		return ResponseEntity.ok(portfolioService.createPortfolio(portfolio));
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<Portfolio> updatePortfolio(@PathVariable(name = "id") Long id,
			@Valid @RequestBody Portfolio portfolio) {
		portfolio.setId(id);
		return ResponseEntity.ok(portfolioService.updatePortfolio(portfolio));
	}
	
	@DeleteMapping(path = "/{id}")
	public void delete (@PathVariable(name = "id") Long id){
		this.portfolioService.delete(id);
	}
}