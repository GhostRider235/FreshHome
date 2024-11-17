package com.project.ProyectoFreshhome.entities;

import java.util.HashSet;
import java.util.Set;

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
	
	//Relacion con empleados
	@ManyToMany(mappedBy = "Habilidades",cascade = CascadeType.ALL)
	private Set<Empleado> Empleados = new HashSet<>();
	
	
	//Constructores
	public Habilidad(String habilidad, int idEmpleado, Set<Empleado> empleados) {
		super();
		this.habilidad = habilidad;
		this.idEmpleado = idEmpleado;
		Empleados = empleados;
	}
	public Habilidad() {
		super();
	}



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
