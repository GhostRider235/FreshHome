package com.FreshHome.app.jwt;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.FreshHome.app.service.UserDetailsServiceCustom;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;


    
    public JWTFilter(JWTService jwtService, UserDetailsService userDetailsService) {
		super();
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
	}




	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestPath = request.getRequestURI();
        System.out.println("=== JWTFilter procesando request ===");
        System.out.println("URL solicitada: " + requestPath);
        
        // Si es una ruta pública, permitir el acceso sin token
        if (requestPath.startsWith("/auth/") || requestPath.equals("/") || 
            requestPath.startsWith("/css/") || requestPath.startsWith("/js/")) {
            filterChain.doFilter(request, response);
            return;
        }
        
        String Token = jwtService.obtenerTokenCookie(request);
        System.out.println("Token encontrado en cookie: " + (Token != null ? "Sí" : "No"));

        if (Token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                String username = jwtService.getUsername(Token);
                System.out.println("Username del token: " + username);
                
                try {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    System.out.println("UserDetails cargado: " + (userDetails != null ? "Sí" : "No"));

                    if (jwtService.validarToken(Token, userDetails)) {
                        System.out.println("Token válido, estableciendo autenticación");
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    } else {
                        System.out.println("Token inválido");
                        response.sendRedirect("/auth/login?error=invalid-token");
                        return;
                    }
                } catch (UsernameNotFoundException e) {
                    System.out.println("Usuario no encontrado: " + username);
                    response.sendRedirect("/auth/login?error=user-not-found");
                    return;
                }
            } catch (Exception e) {
                System.out.println("Error procesando token: " + e.getMessage());
                response.sendRedirect("/auth/login?error=token-error");
                return;
            }
        } else if (Token == null && !requestPath.startsWith("/auth/")) {
            // Solo redirigir si no es una ruta de autenticación
            response.sendRedirect("/auth/login");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
