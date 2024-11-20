package com.project.ProyectoFreshhome.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.ProyectoFreshhome.entities.Cliente;
import com.project.ProyectoFreshhome.entities.Pago;
import com.project.ProyectoFreshhome.entities.Solicitud;
import com.project.ProyectoFreshhome.repository.ClienteRepository;
import com.project.ProyectoFreshhome.repository.SolicitudRepository;
import com.project.ProyectoFreshhome.service.ClienteService;
import com.project.ProyectoFreshhome.service.impl.ClienteServiceImpl;
import com.project.ProyectoFreshhome.service.impl.SolicitudServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SolicitudController {

	@Value("${jwt.secret}")
	private String firmaAutorizada = "gtu98#4n6$fr&d/23";

	// Este objeto nos permite manejar los servicios de Solicitud
	@Autowired
	private SolicitudRepository servS;

	// Este objeto es para manejar los clientes
	@Autowired
	private ClienteService client;

	// Ingresar al formulario de creacion de solicitud
	@PostMapping("/cliente/{Cliente}/{Solicitud}/formulario")
	public String CrearSolicitud(Model m, @PathVariable Cliente client, @PathVariable Solicitud sol) {
		m.addAttribute("cliente", client);
		m.addAttribute("nuevaSolicitud", sol);
		return "crearSolcitudCliente";
	}

	// ver formulario de solicitudes
	@GetMapping("/{C}/sol")
	public String FormularioSolicitudes(Model m, @PathVariable("C") Cliente c) {
		Pago p = new Pago();
		Solicitud sol = new Solicitud();
		sol.setPago(p);
		m.addAttribute("pago", p);
		m.addAttribute("solicitud", sol);
		m.addAttribute("Cliente", c);
		return "RegSolicitud";
	}

	// Registrar solicitud
	@PostMapping("/{C}/reg")
	public String agregarSolicitud(@PathVariable("C") Cliente cliente, @ModelAttribute("solicitud") Solicitud sol) {
		cliente.getSolicitudes().add(sol);
		return "redirect:/{C}/table/sol";
	}

	// Eliminar solicitud
	@DeleteMapping("/{C}/del")
	public String eliminarSol(@PathVariable("C") Cliente cliente, Model m) {
		List<Solicitud> solicitudes = new ArrayList<>();
		solicitudes.addAll(cliente.getSolicitudes());
		solicitudes.remove(1);

		return "redirect:/{C}/table/sol";
	}

	// Actualizar solicitud
	@GetMapping("/{C}/now")
	public String actualizar(@RequestParam String param) {
		return new String();
	}

	// Ver solicitudes
	@GetMapping("/{C}/table/sol")
	public String getMethodName(@PathVariable("C") Cliente c, HttpServletRequest r, Model m) {
		// obtener el token
		String token = r.getHeader("Authorization").substring(7);

		// Desifrar el token
		Claims cl = Jwts.parser().setSigningKey(firmaAutorizada).parseClaimsJws(token).getBody();
		int clienteId = Integer.valueOf(cl.getId().toString());

		// Buscar el empleado
		Cliente cli = client.buscarCliente(clienteId).orElseThrow(() -> new RuntimeException("no se encontro"));

		m.addAttribute("listaSolicitudes", cli.getSolicitudes());
		m.addAttribute("Cliente", cli);

		return "formularioSol";
	}
}
