package com.project.ProyectoFreshhome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.ProyectoFreshhome.entities.Cliente;
import com.project.ProyectoFreshhome.service.ClienteService;
import com.project.ProyectoFreshhome.service.EmpleadoService;

@Controller
@RequestMapping("/auth")
public class AccountController {

	@Value("${jwt.secret}")
	private String firmaAutorizada = "gtu98#4n6$fr&d/23";

	// SECCION DE CLIENTES

	@Autowired
	private ClienteService client;

	// Entrar al formulario de inicio de sesion
	@GetMapping("/login")
	public String FormularioIniciarSersion(Model m) {
		Cliente c = new Cliente();
		m.addAttribute("Cliente", c);
		return "inicioCliente";
	}

	// inicio de sesion para clientes
	// Formulario de registro de los clientes
	@GetMapping("/registrar")
	public String formularioRegistroCliente(Model m) {
		Cliente c = new Cliente();
		m.addAttribute("nuevoCliente", c);
		return "registrarCliente";
	}

	// registrar el cliente nuevo
	@PostMapping("/registrarCliente")
	public String Registrar(@ModelAttribute("Cliente") Cliente c, Model m) {
		if (c == null) {
			m.addAttribute("error", "No se pudo registrar");
		}
		client.agregar(c);
		m.addAttribute("cliente", c);
		return "redirect:/perfil/{Cliente}";
	}

	// Iniciar sesion
	@PostMapping("/iniciocliente")
	public String inicioCliente(@RequestBody Cliente cliente, Model m) {
		Cliente c = client.login(cliente.getContrasena(), cliente.getContrasena());
		if (!(c == null)) {
			String token = client.token(cliente);
			String Respuesta = "{\"token\": \"" + token + "\"}";
			ResponseEntity.ok(Respuesta);
			m.addAttribute("Cliente", cliente);
			return "redirect:/inicioCliente";
		}
		ResponseEntity.status(401).body("Credenciales incorrectas");
		m.addAttribute("error", "Credenciales incorrectas");
		return "errorInicio";
	}

	// SECCION DE EMPLEADOS

	@Autowired
	private EmpleadoService empler;

}
