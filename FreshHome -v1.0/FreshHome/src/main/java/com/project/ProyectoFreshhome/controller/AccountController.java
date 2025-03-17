package com.project.ProyectoFreshhome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.ProyectoFreshhome.entities.Cliente;
import com.project.ProyectoFreshhome.entities.Empleado;
import com.project.ProyectoFreshhome.service.ClienteService;
import com.project.ProyectoFreshhome.service.EmpleadoService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/auth")
public class AccountController {

	@Value("${jwt.secret}")
	private String firmaAutorizada = "gtu98#4n6$fr&d/23";
	
	@GetMapping("/rol")
	public String Direccionar() {
		return "roles";
	}
	
	@GetMapping("/rol/reg")
	public String RegistroRol() {
		return "rolesRegistr";
	}
	

	// SECCION DE CLIENTES

	@Autowired
	private ClienteService client;

	// Entrar al formulario de inicio de sesion
	@GetMapping("/login/c")
	public String FormularioIniciarSersionCliente(Model m) {
		Cliente c = new Cliente();
		m.addAttribute("Cliente", c);
		return "inicioSesion";
	}

	// inicio de sesion para clientes
	// Formulario de registro de los clientes
	@GetMapping("/registrar/C")
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
	@PostMapping("/inicioCliente")
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

	// mostrar el perfil del cliente
	@GetMapping("/perfil/{cliente}")
	public String verPerfil(Model m, @PathVariable("cliente") Cliente cli) {
		m.addAttribute("Cliente", cli);
		return "perfilCliente";
	}

	// SECCION DE EMPLEADOS

	@Autowired
	private EmpleadoService emplent;

	// formulario de registro de empleados formulario
	@GetMapping("/registrar/E")
	public String formularioRegistroEmpleado(Model m) {
		Empleado e = new Empleado();
		m.addAttribute("nuevoEmpleado", e);
		return "registrarEmpleado";
	}

	// registrar el cliente nuevo
	@PostMapping("/registrarEmpleado")
	public String Registrar(@ModelAttribute("emp") Empleado e, Model m) {
		if (e == null) {
			m.addAttribute("error", "No se pudo registrar");
		}
		emplent.agregar(e);
		return "redirect:/perfil";
	}

	// ver el perfil del empleado
	@GetMapping("/perfil/{empleado}")
	public String verPerfil(Model m, HttpServletRequest r,@PathVariable("empleado") Empleado empl) {
		m.addAttribute("cliente", empl);
		return "perfilEmpleado";
	}

	// Entrar al formulario de inicio de sesion
	@GetMapping("/login/e")
	public String FormularioIniciarSersionEmpleado(Model m) {
		Empleado e = new Empleado();
		m.addAttribute("empleado", e);
		return "InicioSesion";
	}

	// Iniciar sesion
	@PostMapping("/inicioEmpleado")
	public String inicioCliente(@RequestBody Empleado empleado, Model m) {
		Empleado e = emplent.login(empleado.getContrasena(), empleado.getContrasena());
		if (!(e == null)) {
			String token = emplent.token(empleado);
			String Respuesta = "{\"token\": \"" + token + "\"}";
			ResponseEntity.ok(Respuesta);
			return "redirect:/inicioCliente";
		}
		ResponseEntity.status(401).body("Credenciales incorrectas");
		m.addAttribute("error", "Credenciales incorrectas");
		return "errorInicio";
	}

}
