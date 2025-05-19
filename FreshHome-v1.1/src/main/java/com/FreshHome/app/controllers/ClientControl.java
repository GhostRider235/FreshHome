package com.FreshHome.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.FreshHome.app.model.HabilidadesEntity;
import com.FreshHome.app.service.HabilidadesService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/app/client")
public class ClientControl {

    @Autowired
    private HabilidadesService habilidadesService;

    @GetMapping("/home")
    public String home(Model model) {
        List<HabilidadesEntity> habilidades = habilidadesService.getAllHabilidadesEntities();
        model.addAttribute("habilidades", habilidades);
        return "client/home";
    }

    @GetMapping("/perfil-cliente")
    public String perfilCliente(){
        return "client/perfilCliente";
    }
    
    @GetMapping("/solicitudes")
    public String ListadoSolicitudes(Model m) {
    	m.addAttribute("solicitudes", habilidadesService.getAllHabilidadesEntities());
        return "";
    }
    

}
