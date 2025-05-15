package com.FreshHome.app.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuarios")
public class UsuarioSesiones implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "correos", unique = true, nullable = false)
	private String email;

	@Column(name = "contrasenas", nullable = false)
	private String password;

	@Column(name = "nombresUsuarios", nullable = true, length = 50)
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "rol_id", nullable = false)
	private RolesSesiones rol;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String rol = this.rol.getNombreRol().toUpperCase();
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + rol));
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	public UsuarioSesiones(String email, String password, String nombre, RolesSesiones rol) {
		super();
		this.email = email;
		this.password = password;
		this.nombre = nombre;
		this.rol = rol;
	}

	public UsuarioSesiones(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public UsuarioSesiones() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public RolesSesiones getRol() {
		return rol;
	}

	public void setRol(RolesSesiones rol) {
		this.rol = rol;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
