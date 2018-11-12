package br.cesed.unifacisa.lti.security.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Essa classe servirá para manipular o Token
 * @author Jack
 */
public class JwtTokenUtil implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Strings a serem utilizadas em métodos.
	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_CREATED = "created";
	static final String CLAIM_KEY_EXPIRED = "exp";
	
	//Chave passada no aplication.propries
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	/**
	 * Obtem o email que está dentro do Token
	 * @param token
	 * @return
	 */
	public String getUsernameFromToken(String token) {
		String username;
		try {
			final Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		}catch(Exception e) {
			username = null;
		}
		return username;
	}
	
	//Retona data da expiração de um Token Jwt.
	/**
	 * Esse método guarda a data da expiração.
	 * @param token
	 * @return
	 */
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		}catch(Exception e) {
			expiration = null;
		}
		return expiration;
	}
	/**
	 *Realiza o parser do Token Jwt, para extrair as informações contidas no corpo dele. 
	 * @param token
	 * @return
	 */
	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody();
		}catch(Exception e) {
			claims = null;
		}
		return claims;
	}
	
	/**
	 * Esse método verifica se o Token tá expirado 
	 * @param token
	 * @return
	 */
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	//Responsável por gerar o Token
	/**
	 * Esse método é responsável por gerar o Token
	 * @param userDetails
	 * @return
	 */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap();
		
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		
		final Date createdDate = new Date();
		claims.put(CLAIM_KEY_CREATED, createdDate);
		
		return doGenerateToken(claims);
	}
	/**
	 * Método auxiliar
	 * @param claims
	 * @return
	 */
	private String doGenerateToken(Map<String, Object> claims) {
		final Date createdDate = (Date) claims.get(CLAIM_KEY_CREATED);
		final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
	/**
	 * Esse método verifica se o Token pode ser atualizado. 
	 * @param token
	 * @return
	 */
	public Boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token));
	}
	/**
	 * Esse método atualiza o Token
	 * @param token
	 * @return
	 */
	public String refreshToken(String token) {
		String refreshedToken;
		try {
			final Claims claims = getClaimsFromToken(token);
			claims.put(CLAIM_KEY_CREATED, new Date());
			refreshedToken = doGenerateToken(claims);
		}catch(Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}
	//
	/**
	 * Esse método verfica se o Token está valido 
	 * @param token
	 * @param userDetails
	 * @return
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		JwtUser user = (JwtUser) userDetails;
		final String username = getUsernameFromToken(token);
		return(
				username.equals(user.getUsername())
						&& !isTokenExpired(token));
	}
	//Classe Factory que converte nosso usuário, no usuário reconhecido no SpringSecurity
	
}