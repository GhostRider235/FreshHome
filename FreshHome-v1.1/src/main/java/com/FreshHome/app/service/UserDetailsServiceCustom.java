package com.FreshHome.app.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.FreshHome.app.model.HabilidadesEntity;
import com.FreshHome.app.model.UsuarioSesiones;
import com.FreshHome.app.model.dto.HabilidadesDto;
import com.FreshHome.app.model.dto.usuarioDTO;


public interface UserDetailsServiceCustom extends UserDetailsService{
	public UsuarioSesiones registrarUsuario(usuarioDTO usuario);
	public void eliminarUsuario(String correo);
	
}
