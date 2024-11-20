package com.project.ProyectoFreshhome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.ProyectoFreshhome.entities.Cliente;
import com.project.ProyectoFreshhome.entities.Solicitud;
import com.project.ProyectoFreshhome.repository.ClienteRepository;
import com.project.ProyectoFreshhome.repository.SolicitudRepository;
import com.project.ProyectoFreshhome.service.impl.ClienteServiceImpl;
import com.project.ProyectoFreshhome.service.impl.SolicitudServiceImpl;

@Controller
public class SolicitudController {
	
	
	//Este objeto nos permite manejar los servicios de Solicitud
	@Autowired
	private SolicitudRepository servS;
	
	
	//Este objeto es para manejar los clientes
	@Autowired
	private ClienteRepository servC;
	

	//Para ver solicitudes del cliente
	@GetMapping("/cliente/{Cliente}/Solicitudes")
	public String VerSolicitudes(@PathVariable Cliente client,Model m) {
		m.addAttribute("Solicitudes",client.getSolicitudes());
		return "tablaClientesSolicitudes";
	}
	
	//Ingresar al formulario de creacion de solicitud
	@PostMapping("/cliente/{Cliente}/{Solicitud}/formulario")
	public String CrearSolicitud(Model m,@PathVariable Cliente client,@PathVariable Solicitud sol) {
		
		m.addAttribute("nuevaSolicitud",sol);
		return "crearSolcitudCliente";
	}
	
	//Eliminar Solicitudes
	
	
	
}
