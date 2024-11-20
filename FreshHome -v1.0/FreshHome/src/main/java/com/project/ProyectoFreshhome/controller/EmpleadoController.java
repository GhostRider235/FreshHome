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
import com.project.ProyectoFreshhome.entities.Solicitud;
import com.project.ProyectoFreshhome.service.EmpleadoService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;


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
	
	
	
	
	
		
}
