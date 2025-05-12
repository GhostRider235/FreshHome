package com.FreshHome.app.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.FreshHome.app.model.HabilidadesEntity;
import com.FreshHome.app.model.UsuarioSesiones;
import com.FreshHome.app.model.dto.usuarioDTO;


public interface UserDetailsServiceCustom extends UserDetailsService{
	public UsuarioSesiones registrarUsuarioCliente(usuarioDTO user);
	public UsuarioSesiones registrarUsuarioEmpleado(usuarioDTO user);
	public HabilidadesEntity registroHabilidadesEmpleado(HabilidadesDto habilidad);
}
