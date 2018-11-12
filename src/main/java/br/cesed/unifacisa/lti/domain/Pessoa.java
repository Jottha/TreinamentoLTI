package br.cesed.unifacisa.lti.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author jolsy
 *
 */
@Entity
public class Pessoa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length=10, nullable = false)
	private String nome;
	
	@Column(unique = true)
	private String email;
	
	@Column(unique = true)
	private String password;
	
	@OneToOne
	private Portfolio portfolio;
	
	
	public Pessoa(Long id, String nome, String email, String password, Portfolio portfolio) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.portfolio = portfolio;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Portfolio getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}
	
}
