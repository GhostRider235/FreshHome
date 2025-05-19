package com.FreshHome.app.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

import jakarta.servlet.http.HttpSession;

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

	public UsuarioSesiones findByEmail(String email) {
		return repSQL.findByemail(email)
			.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioSesiones user = findByEmail(username);
		
		ServletRequestAttributes at = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		
		HttpSession sesionActual = at.getRequest().getSession(true);
		
		UsuarioEntity userServices = repNoSQL.findByidUsuario((int)user.getId()).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado en nuestra base de datos"));
		
		sesionActual.setAttribute("servicioUsuarioActual", userServices);
		
		return User.withUsername(user.getEmail())
				.password(user.getPassword())
				.roles(user.getRol().getNombreRol())
				.build();
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
