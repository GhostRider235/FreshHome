package com.project.ProyectoFreshhome.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.project.ProyectoFreshhome.entities.Cliente;
import com.project.ProyectoFreshhome.service.ClienteService;

import ch.qos.logback.core.net.server.Client;

import org.springframework.web.bind.annotation.*;



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
	public String formularioRegistroCliente(Model m) {
		Cliente c = new Cliente();
		m.addAttribute("nuevoCliente",c);
		return "registrarCliente";
	}
	
	@GetMapping("/registrar/r")
	public String Registrar(@ModelAttribute Cliente c, Model m) {
		if (c==null) {
			m.addAttribute("error","No se pudo registrar");
		}
		client.agregar(c);
		return "redirect:/perfil";
	}
	
	
	
	//mostrar el perfil de la persona
	@GetMapping("/perfil")
	public String verPerfil(Model m) {
		m.addAttribute("cliente",client.buscarCliente(0));
		return "perfilCliente";
	}
	
	
	//Entrar al formulario de Registro
	@GetMapping("/inicioCliente")
	public String FormularioIniciarSersion(Model m) {
		Cliente c = new Cliente();
		m.addAttribute("cliente",c);
		return "inicioCliente";
	}
	
	
	//Iniciar sesion
	@PostMapping("/login")
	public String inicioCliente(@RequestBody Cliente cliente, Model m) {
		Cliente c = client.login(cliente.getContrasena(), cliente.getContrasena());
		if (!(c==null)) {
			String token = client.token(cliente);
			String Respuesta = "{\"token\": \"" + token +"\"}";
			ResponseEntity.ok(Respuesta);
			return "redirect:/inicioCliente";
		}
		ResponseEntity.status(401).body("Credenciales incorrectas");
		m.addAttribute("error","Credenciales incorrectas");
		return "errorInicio";
	}
	
	
}
