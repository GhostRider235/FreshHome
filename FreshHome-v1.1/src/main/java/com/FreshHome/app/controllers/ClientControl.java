package com.FreshHome.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.FreshHome.app.model.SolicitudEntity;
import com.FreshHome.app.model.UsuarioEntity;
import com.FreshHome.app.model.UsuarioSesiones;
import com.FreshHome.app.repository.SolicitudRepository;
import com.FreshHome.app.service.HabilidadesService;
import com.FreshHome.app.service.ServicioSolicitud;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;


@Controller
@RequestMapping("/app/client")
public class ClientControl {

    @Autowired
    private ServicioSolicitud services;

    @GetMapping("/home")
    public String home() {
        return "client/home";
    }
    
    @GetMapping("/solicitud-create")
    public String fomrularioSolicitud(HttpSession sesionAbierta,Model m) {
    	SolicitudEntity sol = new SolicitudEntity();
    	UsuarioEntity usuarioActual = (UsuarioEntity) sesionAbierta.getAttribute("servicioUsuarioActual");
    	m.addAttribute("usuarioLogeado", usuarioActual);
    	m.addAttribute("nuevaSolicitud", sol);
        return "crearSolicitudCliente";
    }
    
    @PostMapping("/solicitud-create")
    public String postMethodName(@ModelAttribute("nuevaSolicitud") SolicitudEntity s,@ModelAttribute("usuarioLogeado")) {
    	services.AgregarSolicitudCliente(s);
        return "redirect:/app/client/solicitudes";
    }
    
    

    @GetMapping("/perfil-cliente")
    public String perfilCliente(){
        return "client/perfilCliente";
    }
    
    @GetMapping("/solicitudes")
    public String ListadoSolicitudes(Model m) {
    	m.addAttribute("solicitudes", habilidadesService.getAllHabilidadesEntities());
        return "tablaClientesSolicitudes";
    }
    

}
