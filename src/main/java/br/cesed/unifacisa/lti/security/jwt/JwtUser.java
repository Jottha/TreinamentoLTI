package br.cesed.unifacisa.lti.security.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * Esse implementar UserDetils e controla os usuários que estão autenticados no sistema.
 * @author Jack
 *
 */
public class JwtUser implements UserDetails{
	
	private static final long serialVersionUID = -5820987640595600607L;
	
	private final Long id;
	private final String username;
	private final String password;
	
	public JwtUser(Long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	@JsonIgnore
	public Long getId() {
		return id;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	@JsonIgnore
	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return null;
	}
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return false;
	}
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return false;
	}
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}