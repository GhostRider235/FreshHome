package com.FreshHome.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/")
public class InitControl {
	@GetMapping
	public String inicio() {
		return "InicioVista";
	}
	
}
