package com.FreshHome.app.model.dto;

import com.FreshHome.app.model.RolesSesiones;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class usuarioDTO {

	private String email;
	private String password;
	private String nombre;
	private RolesSesiones rol;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

}
