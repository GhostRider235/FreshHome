package com.FreshHome.app.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.FreshHome.app.jwt.JWTService;
import com.FreshHome.app.model.UsuarioSesiones;
import com.FreshHome.app.model.dto.usuarioDTO;
import com.FreshHome.app.repository.UsuarioRepository;
import com.FreshHome.app.service.AuthService;
import com.FreshHome.app.utilities.SuccessHandlerSesion;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/auth")
public class AuthControl {
	
	@Autowired
	private JWTService jwt;
	
	@Autowired
	private AuthService service;
	
	@Autowired
	private SuccessHandlerSesion successHandlerSesion;
	
	@GetMapping("/login")
	public String iniciarSesion() {
		return "InicioSesion";
	}
	
	@GetMapping("/register")
	public String registro(Model m) {
		usuarioDTO user = new usuarioDTO();
		m.addAttribute("nuevoUsuario",user);
		return "registrarEmpleado";
	}

	@PostMapping("/register")
	public String registrarUsuario(@ModelAttribute("nuevoUsuario") usuarioDTO entity,HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
		//Se crea el usuario y lo almacena
		UsuarioSesiones user = service.registrarUsuario(entity);
		/**
		 
		 //Se genera el token
		String token = jwt.generarToken(user, (int)user.getId());
		//Lo almacena en la cookie
		jwt.almacenarTokenCookie(response, token);
		
		//realiza la autenticacion
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
		
		authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		
	    successHandlerSesion.onAuthenticationSuccess(request, response, authToken);
		 * */
		
	    //no retorna nada porque el SuccessHandler se encarga de redireccionar
		return "redirect/auth/login";
	}
	
	
	
}
