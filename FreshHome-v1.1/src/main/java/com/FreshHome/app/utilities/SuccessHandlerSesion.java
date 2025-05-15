package com.FreshHome.app.utilities;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.FreshHome.app.jwt.JWTService;
import com.FreshHome.app.model.UsuarioEntity;
import com.FreshHome.app.model.UsuarioSesiones;
import com.FreshHome.app.repository.UsuarioRepository;
import com.FreshHome.app.repository.UsuarioSesionesRepository;

import java.io.IOException;

@Component
public class SuccessHandlerSesion implements AuthenticationSuccessHandler {
	
	@Autowired
	private JWTService jwt;
	
	@Autowired
	private UsuarioSesionesRepository rep;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
    	// Buscar el usuario
    	UserDetails usuario = (UserDetails) authentication.getPrincipal();
    	
    	UsuarioSesiones u = rep.findByemail(usuario.getUsername()).orElseThrow(()-> new UsernameNotFoundException("usuario no encontrado"));
    	 
    	String token = jwt.generarToken(usuario,(int)u.getId());
    	
    	jwt.almacenarTokenCookie(response, token);

    	// Inicia sesion segun el rol
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            String role = auth.getAuthority();

            if (role.equals("ROLE_ADMIN")) {
                response.sendRedirect("/admin/home");
                return;
            } else if (role.equals("ROLE_CLIENT")) {
                response.sendRedirect("/app/client/home");
                return;
            } else if (role.equals("ROLE_EMPLOYEE")) {
                response.sendRedirect("/app/employee/home");
                return;
            }
        }

        // Si no tiene ningún rol conocido
        response.sendRedirect("/auth/login?error=sin-rol");
    }
}
