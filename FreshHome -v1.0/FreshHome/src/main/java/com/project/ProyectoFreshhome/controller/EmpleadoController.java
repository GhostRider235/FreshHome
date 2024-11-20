package com.project.ProyectoFreshhome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.ProyectoFreshhome.entities.Empleado;
import com.project.ProyectoFreshhome.service.EmpleadoService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/app/emp")
public class EmpleadoController {
	
	@Autowired
	private EmpleadoService emplent;
	
	@Value("${jwt.secret}")
	private String clave = "gtu98#4n6$fr&d/23";
	
	//mostrar la lista de empleados
	@GetMapping("/empleadoslista")
	public String VerClientes(Model m) {
		m.addAttribute("listadoClientes",emplent.mostrarEmpleados());
		return "tablaEmpleado";
	}
	
	//formulario de registro de empleados formulario
	@GetMapping("/registrar")
	public String formularioRegistroCliente(Model m) {
		Empleado e = new Empleado();
		m.addAttribute("nuevoEmpleado",e);
		return "registrarEmpleado";
	}
	
	//registrar el cliente nuevo
	@GetMapping("/registrarEmpleado")
	public String Registrar(@ModelAttribute("emp") Empleado e, Model m) {
		if (e==null) {
			m.addAttribute("error","No se pudo registrar");
		}
		emplent.agregar(e);
		return "redirect:/perfil";
	}
	
	//ver el perfil del empleado
	@GetMapping("/perfil")
	public String verPerfil(Model m,HttpServletRequest r) {
		//obtener el token
		String token = r.getHeader("Authorization").substring(7);
				
		//Desifrar el token
		Claims cl = Jwts.parser().setSigningKey(clave).parseClaimsJws(token).getBody();
		int empleadoId = Integer.valueOf(cl.getId().toString());
				
		//Buscar el empleado
		Empleado emp = emplent.buscarEmpleado(empleadoId).orElseThrow(()->new RuntimeException("no se encontro")); 
		m.addAttribute("cliente",emp);
		return "perfilEmpleado";
	}
	
	//Entrar al formulario de inicio de sesion
	@GetMapping("/login")
	public String FormularioIniciarSersion(Model m) {
		Empleado e = new Empleado();
		m.addAttribute("empleado",e);
		return "inicioEmpleado";
	}
		
	//Iniciar sesion 
	@PostMapping("/iniciocliente")
	public String inicioCliente(@RequestBody Empleado empleado, Model m) {
		Empleado e = emplent.login(empleado.getContrasena(), empleado.getContrasena());
		if (!(e==null)) {
			String token = emplent.token(empleado);
			String Respuesta = "{\"token\": \"" + token +"\"}";
			ResponseEntity.ok(Respuesta);
			return "redirect:/inicioCliente";
		}
		ResponseEntity.status(401).body("Credenciales incorrectas");
		m.addAttribute("error","Credenciales incorrectas");
		return "errorInicio";
		}
		
}
