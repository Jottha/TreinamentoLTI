package br.cesed.unifacisa.lti.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_MODIFIED)
public class ObjetoNaoRelacionavelExecption extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ObjetoNaoRelacionavelExecption(String oMaior, String oMenor) {
		super("O Objeto " + oMaior +  " não pode ser realcionado a " + oMenor);
	}
}