package br.cesed.unifacisa.lti.security.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.cesed.unifacisa.lti.domain.Pessoa;
import br.cesed.unifacisa.lti.security.jwt.JwtUserFactory;
import br.cesed.unifacisa.lti.services.PessoaService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService{
	
		@Autowired
		private PessoaService pessoaService;
		
		@Override
		public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			
			Pessoa pessoa = pessoaService.readPessoaEmail(email);
			if (pessoa == null) {
				throw new UsernameNotFoundException(String.format("Usuário não encontrado com este email ", email));
			} else {
				return JwtUserFactory.create(pessoa);
			}
		}	
}