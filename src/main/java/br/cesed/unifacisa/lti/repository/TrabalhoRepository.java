package br.cesed.unifacisa.lti.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.cesed.unifacisa.lti.domain.Trabalho;

@Repository
public interface TrabalhoRepository extends PagingAndSortingRepository<Trabalho, Long> {

	
}