package com.FreshHome.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.FreshHome.app.repository.SolicitudRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {


	@GetMapping("/home")
    public String inicio(){
        return "admin/inicioPanel";
    }
    
    @GetMapping("/dashboard")
	public String dashboard() {
    	return "admin/visualizacion";
    }

}
