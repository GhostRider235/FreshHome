package com.project.ProyectoFreshhome.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "habilidades")
public class Habilidad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idHabilidad;

	@Column(name = "Habilidad", nullable = false, length = 255)
	private String habilidad;

	
	//Relacion con empleados
	@ManyToOne
	@JoinColumn(name = "IdEmpleado", nullable = false)
	private Empleado empleado;
	
	
	//Constructores

	public Habilidad() {
		super();
	}

	public Habilidad(String habilidad, Empleado empleado) {
		super();
		this.habilidad = habilidad;
		this.empleado = empleado;
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

}
