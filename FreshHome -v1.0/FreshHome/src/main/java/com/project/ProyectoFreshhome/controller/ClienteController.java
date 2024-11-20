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
	
	
	
	
	
	//mostrar el perfil de la persona
	@GetMapping("/perfil/{cliente}")
	public String verPerfil(Model m,HttpServletRequest r,@ModelAttribute("cliente") Cliente cli) {
		//obtener el token
		String token = r.getHeader("Authorization").substring(7);
		
		//Desifrar el token
		Claims cl = Jwts.parser().setSigningKey(clave).parseClaimsJws(token).getBody();
		int clienteId = Integer.valueOf(cl.getId().toString());
		
		//Buscar el empleado
		Cliente c = client.buscarCliente(clienteId).orElseThrow(()->new RuntimeException("no se encontro")); 
		m.addAttribute("Cliente",c);
		return "perfilCliente";
	}
	
	
	
	
	//Iniciar sesion 
	@PostMapping("/iniciocliente")
	public String inicioCliente(@RequestBody Cliente cliente, Model m) {
		Cliente c = client.login(cliente.getContrasena(), cliente.getContrasena());
		if (!(c==null)) {
			String token = client.token(cliente);
			String Respuesta = "{\"token\": \"" + token +"\"}";
			ResponseEntity.ok(Respuesta);
			m.addAttribute("Cliente",cliente);
			return "redirect:/inicioCliente";
		}
		ResponseEntity.status(401).body("Credenciales incorrectas");
		m.addAttribute("error","Credenciales incorrectas");
		return "errorInicio";
	}
	
	
	
}
