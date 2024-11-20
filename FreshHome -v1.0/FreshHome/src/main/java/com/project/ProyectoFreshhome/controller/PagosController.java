package com.project.ProyectoFreshhome.controller;


import java.lang.ProcessBuilder.Redirect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.ProyectoFreshhome.entities.Cliente;
import com.project.ProyectoFreshhome.entities.Pago;
import com.project.ProyectoFreshhome.entities.Solicitud;
import com.project.ProyectoFreshhome.service.PagoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/pag")
public class PagosController {
	
	 @Autowired
	private PagoService pg;
	
	//formulario de registro de pago
	@GetMapping("/{C}/{Sol}/registrarPago")
	public String alistarpago(@PathVariable("C") Cliente cliente,@PathVariable("Sol") Solicitud solicitud,Model m) {
		Pago p = new Pago();
		m.addAttribute("cliente",cliente);
		m.addAttribute("solicitud",solicitud);
		m.addAttribute("pago",p);
		return "pagoCliente";
	}
	
	//Registrar pago de la solicitud
	@PostMapping("/{C}/{Sol}/registrarPago/")
	public String registrarPago(@PathVariable("C") Cliente cliente,@PathVariable("Sol") Solicitud s,Model m) {

		return null;
	}
	
	
}
