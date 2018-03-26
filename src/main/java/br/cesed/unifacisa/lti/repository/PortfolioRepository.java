package br.cesed.unifacisa.lti.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.cesed.unifacisa.lti.domain.Portfolio;

@Repository
public interface PortfolioRepository extends PagingAndSortingRepository<Portfolio, Long>{
	

}
