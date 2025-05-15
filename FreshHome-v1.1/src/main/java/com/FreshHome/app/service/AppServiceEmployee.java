package com.FreshHome.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FreshHome.app.jwt.JWTService;
import com.FreshHome.app.repository.SolicitudRepository;
import com.FreshHome.app.repository.UsuarioRepository;

@Service
public class AppServiceEmployee {

	@Autowired
	private SolicitudRepository sol;
	
	@Autowired
	private UsuarioRepository us;
	
	@Autowired
	private JWTService jwt;
}
