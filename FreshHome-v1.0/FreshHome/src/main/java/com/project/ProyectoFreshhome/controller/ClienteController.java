package com.project.ProyectoFreshhome.controller;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.project.ProyectoFreshhome.entities.Cliente;
import com.project.ProyectoFreshhome.entities.Pago;
import com.project.ProyectoFreshhome.entities.Solicitud;
import com.project.ProyectoFreshhome.service.ClienteService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@RequestMapping("/app/cli")
public class ClienteController {
	
	@Autowired
	private ClienteService client;
	
	@Value("${jwt.secret}")
	private String clave = "gtu98#4n6$fr&d/23";
	
	//mostrar la lista de clientes
	@GetMapping("/clienteslista")
	public String VerClientes(Model m) {
		m.addAttribute("listadoClientes",client.mostrarClientes());
		return "tablaClientes";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
