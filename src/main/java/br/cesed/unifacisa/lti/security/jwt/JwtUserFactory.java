package br.cesed.unifacisa.lti.security.jwt;

import br.cesed.unifacisa.lti.domain.Pessoa;

/**
 * Essa classe é responsável por converter nosso usuário, no usuário reconhecido no SpringSecurity.
 * @author Jack
 */
public class JwtUserFactory {
	
	private JwtUserFactory() {
		
	}
	
	public static JwtUser create(Pessoa pessoa) {
		return new JwtUser(
				pessoa.getId(), 
				pessoa.getEmail(), 
				pessoa.getPassword());
	}
}