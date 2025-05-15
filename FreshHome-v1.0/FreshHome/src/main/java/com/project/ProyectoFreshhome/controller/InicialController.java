package com.project.ProyectoFreshhome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/init")
public class InicialController {
	@GetMapping("/Inicio")
    public String inicio() {
        return "InicioVista"; 
    }
	
}
