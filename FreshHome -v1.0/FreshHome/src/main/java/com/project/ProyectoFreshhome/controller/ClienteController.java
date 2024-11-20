package com.project.ProyectoFreshhome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.ProyectoFreshhome.service.ClienteService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/app/cliente")
public class ClienteController {
	private ClienteService client;
	
	//mostrar la lista de clientes
	@GetMapping("/clienteslista")
	public String VerClientes(Model m) {
		m.addAttribute("listadoClientes",client.mostrarClientes());
		return "tablaClientes";
	}
	
	//Formulario de registro de los clientes
	@GetMapping("/registrar")
	public String getMethodName(Model m) {
		m.addAttribute("clienteNuevo");
		return "registrarCliente";
	}
	
	@GetMapping("/perfil")
	public String getMethodName(@RequestParam String param) {
		return "perfilCliente";
	}
	
	
	@PostMapping("/")
	public String postMethodName(@RequestBody String entity) {
		//TODO: process POST request
		
		return "redirect:/perfil";
	}
	
	
}
