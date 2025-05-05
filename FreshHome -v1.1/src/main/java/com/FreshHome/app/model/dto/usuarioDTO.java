package com.FreshHome.app.model.dto;

import java.time.LocalDateTime;

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
	private Long userIdSql;
	private LocalDateTime fechaNacimiento;
	private Integer edad;
	private String direccion;

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

	public Long getUserIdSql() {
		return userIdSql;
	}

	public void setUserIdSql(Long userIdSql) {
		this.userIdSql = userIdSql;
	}

	public LocalDateTime getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
