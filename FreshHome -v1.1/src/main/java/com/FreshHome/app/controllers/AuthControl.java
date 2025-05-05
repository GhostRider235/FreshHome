package com.FreshHome.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.FreshHome.app.jwt.JWTService;
import com.FreshHome.app.model.dto.usuarioDTO;
import com.FreshHome.app.repository.UsuarioRepository;
import com.FreshHome.app.service.AuthService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/auth")
public class AuthControl {
	
	@Autowired
	private JWTService jwt;
	
	@Autowired
	private AuthService service;
	
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}
	
	@GetMapping("/register/client")
	public String registro(Model m) {
		usuarioDTO user = new usuarioDTO();
		m.addAttribute("nuevoUsuario",user);
		return "registerRol";
	}
	
	@PostMapping("/register/client/")
	public String registrarUsuario(@ModelAttribute("nuevoUsuario") usuarioDTO entity) {
		service.registrarUsuarioCliente(entity);
		return "redirect:/app/client";
	}
	
	
	
}
