package com.project.ProyectoFreshhome.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "habilidades")
public class Habilidad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idHabilidad;

	@Column(name = "Habilidad", nullable = false, length = 50)
	private String habilidad;

	@Column(name = "IdEmpleado")
	private int idEmpleado;

	// Getters y setters
	public int getIdHabilidad() {
		return idHabilidad;
	}

	public void setIdHabilidad(int idHabilidad) {
		this.idHabilidad = idHabilidad;
	}

	public String getHabilidad() {
		return habilidad;
	}

	public void setHabilidad(String habilidad) {
		this.habilidad = habilidad;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
}
