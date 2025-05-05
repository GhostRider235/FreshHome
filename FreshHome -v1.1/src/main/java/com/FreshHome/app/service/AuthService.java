package com.FreshHome.app.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.FreshHome.app.jwt.JWTService;
import com.FreshHome.app.model.HabilidadesEntity;
import com.FreshHome.app.model.UsuarioEntity;
import com.FreshHome.app.model.UsuarioSesiones;
import com.FreshHome.app.model.dto.usuarioDTO;
import com.FreshHome.app.repository.UsuarioRepository;
import com.FreshHome.app.repository.UsuarioSesionesRepository;

@Service
public class AuthService implements UserDetailsServiceCustom{
	
	@Autowired
	private UsuarioSesionesRepository repSQL;
	
	@Autowired
	private UsuarioRepository repNoSQL;
	
	@Autowired
	private PasswordEncoder password;
	


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioSesiones user = repSQL.findByemail(username).orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));
		String autoridad = user.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority).orElse(null);
		return User
				.withUsername(user.getEmail())
				.password(user.getPassword())
				.roles(autoridad)
				.build();
	}

	@Override
	public UsuarioSesiones registrarUsuarioCliente(usuarioDTO user) {
		//objeto del nuevo usuario en Posgre (SQL)
		UsuarioSesiones u = new UsuarioSesiones();
		
		//Objeto del nuevo usuario en Mongo (no SQL)
		UsuarioEntity us = new UsuarioEntity();
		HabilidadesEntity h = new HabilidadesEntity();
		
		//Guardar en Mongo
		us.setContraseña(password.encode(user.getPassword()));
		us.setCorreo(user.getEmail());
		us.setDireccion(user.getDireccion());
		us.setEdad((int)ChronoUnit.YEARS.between(user.getFechaNacimiento(), LocalDateTime.now()));
		us.setNombre(user.getNombre());	
		us.setUserIdSql(u.getId());
		
		//Guardar en Posgre
		u.setEmail(user.getEmail());
		u.setNombre(user.getNombre());
		u.setPassword(password.encode(user.getPassword()));
		u.setRol(user.getRol());
		
		repNoSQL.save(us);
		return repSQL.save(u);
	}

	@Override
	public UsuarioSesiones registrarUsuarioEmpleado(usuarioDTO user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
