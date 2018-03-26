package br.cesed.unifacisa.lti.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.cesed.unifacisa.lti.domain.Portfolio;
import br.cesed.unifacisa.lti.repository.PortfolioRepository;

@Service
public class PortfolioService {
	
	@Autowired
	private PortfolioRepository portfolioRepository;
	
	
	public Portfolio createPortfolio(Portfolio portfolio) {
		return portfolioRepository.save(portfolio);
	}
	
	public List<Portfolio> readAllPortifolio(Pageable pageable){
		return (List<Portfolio>) portfolioRepository.findAll();
	}
	
/*	public Portfolio readByTitle(String titulo){
		return portfolioRepository.findByTitle(titulo).get(0);
	}*/
	
	public Portfolio readById(Long id) {
		return portfolioRepository.findById(id).get();
	}
	
	public Portfolio updatePortfolio(Portfolio portfolio) {
		if(portfolio.getId() != null) {
			portfolioRepository.save(portfolio);
		}
		return null;
	}
	
	public void delete(Long id) {
		portfolioRepository.deleteById(id);
	}
}