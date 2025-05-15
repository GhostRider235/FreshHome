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
import com.FreshHome.app.model.RolesSesiones;
import com.FreshHome.app.model.UsuarioEntity;
import com.FreshHome.app.model.UsuarioSesiones;
import com.FreshHome.app.model.dto.HabilidadesDto;
import com.FreshHome.app.model.dto.usuarioDTO;
import com.FreshHome.app.repository.HabilidadesRepository;
import com.FreshHome.app.repository.RolesSesionesRepository;
import com.FreshHome.app.repository.UsuarioRepository;
import com.FreshHome.app.repository.UsuarioSesionesRepository;

@Service
public class AuthService implements UserDetailsServiceCustom {

	@Autowired
	private UsuarioSesionesRepository repSQL;

	@Autowired
	private RolesSesionesRepository repRol;
	
	@Autowired
	private UsuarioRepository repNoSQL;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioSesiones user = repSQL.findByemail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
		String autoridad = user.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority).orElse(null);
		return User.withUsername(user.getEmail()).password(user.getPassword()).roles(autoridad).build();
	}

	@Override
	public void eliminarUsuario(String correo) {
		UsuarioSesiones usuario = repSQL.findByemail(correo)
				.orElseThrow(() -> new UsernameNotFoundException("usuario no encontrado"));
		int id = (int) usuario.getId();
		repSQL.delete(usuario);
		repNoSQL.deleteByIdUsuario(id);
	}

	@Override
	public UsuarioSesiones registrarUsuario(usuarioDTO usuario) {
		// objeto del nuevo usuario en PostgreSQL (SQL)
		UsuarioSesiones u = new UsuarioSesiones();
		RolesSesiones rolUser = new RolesSesiones(usuario.getRol());

		// Objeto del nuevo usuario en Mongo (no SQL)
		UsuarioEntity us = new UsuarioEntity();

		// Guardar en Mongo
		us.setContraseña(passwordEncoder.encode(usuario.getPassword()));
		us.setCorreo(usuario.getEmail());
		us.setDireccion(usuario.getDireccion());
		us.setEdad((int) ChronoUnit.YEARS.between(usuario.getFechaNacimiento(), LocalDateTime.now()));
		us.setNombre(usuario.getNombre());
		us.setFechaNacimiento(usuario.getFechaNacimiento().atStartOfDay());
		us.idUsuario((int) u.getId());
		us.setTelefono(usuario.getTelefono());


		// Guardar en Posgre
		u.setEmail(usuario.getEmail());
		u.setNombre(usuario.getNombre());
		u.setPassword(passwordEncoder.encode(usuario.getPassword()));
		u.setRol(rolUser);
		
		repRol.save(rolUser);
		repNoSQL.save(us);
		return repSQL.save(u);
	}

}
