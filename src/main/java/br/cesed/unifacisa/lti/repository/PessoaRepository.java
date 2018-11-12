package br.cesed.unifacisa.lti.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.cesed.unifacisa.lti.domain.Pessoa;

@Repository
public interface PessoaRepository extends PagingAndSortingRepository<Pessoa, Long>{

	public List<Pessoa> findByNome(String nome);

	public Pessoa findByEmail(String email);

	public boolean findByNome(boolean equals);

	
}