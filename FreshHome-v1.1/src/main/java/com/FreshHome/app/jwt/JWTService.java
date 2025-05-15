package com.FreshHome.app.jwt;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.FreshHome.app.model.UsuarioSesiones;
import com.FreshHome.app.repository.UsuarioSesionesRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JWTService {

	@Value("${jwt.secret}")
	private String SECRET_KEY;

	@Value("${jwt.expiration}")
	private long expirationTime;

	@Value("${jwt.cookie.name}")
	private String NombreCookie;


	public String getCookieName() {
		return this.NombreCookie;
	}

	public String getSecretKey() {
		return SECRET_KEY;
	}

	// Metedo para generar tokens
	public String generarToken(UserDetails user, int idMongo) {

		Key llave = ObtenerFirma();

		return Jwts.builder().setSubject(user.getUsername())
				.claim("rol",
						user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.claim("id_mongo", idMongo)
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(llave, SignatureAlgorithm.HS256).compact();
	}

	// Extraccion de claims (informacion extra del token)
	public Claims extraerInfoClaims(String token) {
		try {
			return Jwts.parserBuilder().setSigningKey(ObtenerFirma()).build().parseClaimsJws(token).getBody();
		} catch (JwtException e) {
			throw new JwtException("Token no valido o expirado.");
		}
	}

	public int obtenerMongoId(String token) {
		return extraerInfoClaims(token).get("id_mongo", Integer.class);
	}

	public String obtenerRol(String token) {
		return extraerInfoClaims(token).get("rol", String.class);
	}
	
	public String getUsername(String token) {
        return extraerInfoClaims(token).getSubject();
    }

	// Validar el token
	public boolean validarToken(String token, UserDetails user) {
		final String usuario = extraerInfoClaims(token).getSubject();
		return (usuario.equals(user.getUsername()) && !tokenExpirado(token));
	}

	// Mirar si el token expiro
	private boolean tokenExpirado(String token) {
		return extraerInfoClaims(token).getExpiration().before(new Date());
	}

	// metodo para firmar un token
	private Key ObtenerFirma() {
		byte[] bytes = Base64.getDecoder().decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(bytes);
	}

	// metodo para obtener el token de la cookie
	public String obtenerTokenCookie(HttpServletRequest request) {
		if (request.getCookies() == null)
			return null;

		for (Cookie i : request.getCookies()) {
			if (i.getName().equals(NombreCookie)) {
				return i.getValue();
			}
		}

		return null;
	}

	//metodo para almacenar cookies
	public void almacenarTokenCookie(HttpServletResponse response, String token) {
		ResponseCookie cookie = ResponseCookie.from("freshHomeCookie", NombreCookie)
				.httpOnly(true)
				.secure(true)
				.path("/")
				.maxAge(expirationTime)
				.sameSite("Strict")
				.build();
		response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
	}

	// Para cuando el usuario cierre la sesion
	public void EliminarCookie(HttpServletResponse response) {
		Cookie c = new Cookie(NombreCookie, null);
		c.setPath("/");
		c.setHttpOnly(true);
		c.setSecure(true); // Mismo valor que en la creación
		c.setMaxAge(0); // Elimina la cookie
		response.addCookie(c);
	}

}