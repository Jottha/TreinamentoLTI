package br.cesed.unifacisa.lti.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.cesed.unifacisa.lti.domain.Trabalho;
import br.cesed.unifacisa.lti.repository.TrabalhoRepository;

@Service
public class TrabalhoService {
	
	@Autowired
	private TrabalhoRepository trabalhoRepository;
	
	public Trabalho createTrabalho(Trabalho trabalho) {
		return trabalhoRepository.save(trabalho);
	}
	
	public List<Trabalho> readAllTrabalho(Pageable pageable){
		return (List<Trabalho>) trabalhoRepository.findAll();
	}
	
	public Trabalho readById(Long id) {
		return trabalhoRepository.findById(id).get();
	}
	
/*	public Trabalho readByName(String nome) {
		return trabalhoRepository.findByName();
	}*/
	
	public Trabalho updateTrabalho(Trabalho trabalho) {
		if(trabalho.getId() != null) {
			trabalhoRepository.save(trabalho);
		}
		return null;
	}
	
	public void delete(Long id) {
		trabalhoRepository.deleteById(id);
	}
}