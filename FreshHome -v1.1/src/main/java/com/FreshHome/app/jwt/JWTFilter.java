package com.FreshHome.app.jwt;

import java.io.IOException;
import java.util.Iterator;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.FreshHome.app.controllers.AuthControl;
import com.FreshHome.app.service.UserDetailsServiceCustom;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.WeakKeyException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {

	private UserDetailsServiceCustom service;

	private JWTService jwt;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = obtenerToken(request);

		if (token != null && validarToken(token, request)) {
			autenticar(token, request);
		}

		Cookie[] cookies = request.getCookies();

		filterChain.doFilter(request, response);
	}

	private String obtenerToken(HttpServletRequest request) {
		// Buscar en las cookies
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals(jwt.getCookieName())) {
					return c.getValue();
				}
			}
		}

		// Buscar en el Header
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}

		return null;
	}

	//Metodo para validadr el token en jwt
	private boolean validarToken(String token, HttpServletRequest request) {
		try {
			Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwt.getSecretKey().getBytes())).build()
					.parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//Metodo para autenticar
	private void autenticar(String token, HttpServletRequest request) {
		String username = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwt.getSecretKey().getBytes())).build()
				.parseClaimsJws(token).getBody().getSubject();

		UserDetails user = service.loadUserByUsername(username);

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null,
				user.getAuthorities());

		auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		SecurityContextHolder.getContext().setAuthentication(auth);
	}
}
