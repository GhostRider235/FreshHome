package com.FreshHome.app.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JWTService {
	
	@Value("${jwt.secret}")
	private String SECRET_KEY;

	@Value("${jwt.expiration}")
	private long expirationTime;
	
	@Value("${jwt.cookie.name}")
    private String cookieName;
	
	
	public String getCookieName() {
		return cookieName;
	}

	public String getSecretKey() {
		return SECRET_KEY;
	}


	public String generarToken(UserDetails user) {
		return Jwts.builder()
				.setSubject(user.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+expirationTime))
				.signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()),SignatureAlgorithm.HS256)
				.compact();
	}
	
	public void respuestaToken(HttpServletResponse  request ,String token) {
		
		//Cookie donde se almacena el token
        Cookie jwtCookie = new Cookie(cookieName,token);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        jwtCookie.setSecure(false);
        jwtCookie.setMaxAge((int) expirationTime);
        request.addCookie(jwtCookie);
        
        //Header para los clientes
        request.addHeader("Authorization", "Bearer"+token);
	}
	
	
}
