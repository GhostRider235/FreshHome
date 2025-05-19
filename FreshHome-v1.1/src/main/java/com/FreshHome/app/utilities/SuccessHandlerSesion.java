package com.FreshHome.app.utilities;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

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
        try {
            System.out.println("=== Procesando autenticación exitosa ===");
            
            UserDetails usuario = (UserDetails) authentication.getPrincipal();
            System.out.println("Usuario autenticado: " + usuario.getUsername());

            UsuarioSesiones u = rep.findByemail(usuario.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("usuario no encontrado"));
            System.out.println("Usuario encontrado en BD: " + u.getEmail() + " con ID: " + u.getId());

            String token = jwt.generarToken(usuario, (int) u.getId());
            System.out.println("Token generado: " + token);

            // Almacenar token en cookie
            jwt.almacenarTokenCookie(response, token);
            
            // Verificar que se guardó correctamente
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    System.out.println("Cookie en respuesta: " + cookie.getName());
                }
            }

            // Inicia sesion segun el rol
            for (GrantedAuthority auth : authentication.getAuthorities()) {
                String role = auth.getAuthority();
                System.out.println("Rol del usuario: " + role);

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

            response.sendRedirect("/auth/login?error=sin-rol");
        } catch (Exception e) {
            System.out.println("Error en autenticación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
