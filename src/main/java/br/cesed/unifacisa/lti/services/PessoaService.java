package br.cesed.unifacisa.lti.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.cesed.unifacisa.lti.domain.Pessoa;
import br.cesed.unifacisa.lti.exception.ChamadaInvalidaException;
import br.cesed.unifacisa.lti.exception.NomeInvalidoException;
import br.cesed.unifacisa.lti.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa createPessoa(Pessoa pessoa) throws ChamadaInvalidaException,NomeInvalidoException{
		if(pessoa.getId() != null) {
			if(pessoaRepository.findById(pessoa.getId()) != null) {
				throw new ChamadaInvalidaException("Chamada equivocada no método POST, utilize o PUT");
			}
		}
		if(pessoa.getNome().equals("")) {
			if(pessoaRepository.findByNome(pessoa.getNome().equals(""))) {
				throw new NomeInvalidoException("O nome não pode ser vazio");
			}
		}
		return pessoaRepository.save(pessoa);
	}
	
	public List<Pessoa> readAllPessoa(Pageable pageable){
		return (List<Pessoa>)pessoaRepository.findAll();
	}
	
	public Pessoa readPessoaNome(String nome) {
		return pessoaRepository.findByNome(nome).get(0);
	}
	
	public Pessoa readPessoaEmail(String email) {
		return pessoaRepository.findByEmail(email);
	}
	
	public Optional<Pessoa> readPessoaId(Long id) {
		return pessoaRepository.findById(id);
	}
	
	public Pessoa updatePessoa(Pessoa pessoa) {
		if(pessoa.getId() != null) {
			pessoaRepository.save(pessoa);
		}
		return null;
	}
	
	public void delete(Long id) {
		pessoaRepository.deleteById(id);
	}
	
}
