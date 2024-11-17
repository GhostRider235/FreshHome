package com.project.ProyectoFreshhome.entities;

import java.sql.Date;
import java.util.*;
import jakarta.persistence.*;

@Entity
@Table(name = "empleado")
public class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEmpleado;

	@Column(name = "nombreEmpleado", nullable = false, length = 50)
	private String nombreEmpleado;

	@Column(name = "Correo", nullable = false, length = 50)
	private String correo;

	@Column(name = "fechaNacimiento", nullable = false)
	private Date fechaNacimiento;

	@Column(name = "añosExperiencia", nullable = true)
	private int anosExperiencia;

	@Column(name = "edad", nullable = false)
	private int edad;

	@Column(name = "CalificacionEmpleado", nullable = false)
	private int calificacionEmpleado;

	@Column(name = "Contraseña", nullable = false, length = 50)
	private String contrasena;

	// Relacio de uno(Empleado) a muchos(Solicitud)
	@OneToMany(mappedBy = "empleado",cascade = CascadeType.ALL)
	private List<Solicitud> Solicitudes = new ArrayList<>();

	
	// Relacion con habilidades
	@OneToMany(mappedBy = "IdEmpleado", cascade = CascadeType.ALL)
	private Set<Habilidad> Habilidades = new HashSet<>();

	
	//Constructor
	public Empleado(String nombreEmpleado, String correo, Date fechaNacimiento, int anosExperiencia, int edad,
			int calificacionEmpleado, String contrasena, List<Solicitud> solicitudes, Set<Habilidad> habilidades) {
		super();
		this.nombreEmpleado = nombreEmpleado;
		this.correo = correo;
		this.fechaNacimiento = fechaNacimiento;
		this.anosExperiencia = anosExperiencia;
		this.edad = edad;
		this.calificacionEmpleado = calificacionEmpleado;
		this.contrasena = contrasena;
		Solicitudes = solicitudes;
		Habilidades = habilidades;
	}

	public Empleado() {
		super();
	}


	// Getters y setters
	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public java.sql.Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(java.sql.Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getAnosExperiencia() {
		return anosExperiencia;
	}

	public void setAnosExperiencia(int anosExperiencia) {
		this.anosExperiencia = anosExperiencia;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getCalificacionEmpleado() {
		return calificacionEmpleado;
	}

	public void setCalificacionEmpleado(int calificacionEmpleado) {
		this.calificacionEmpleado = calificacionEmpleado;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public List<Solicitud> getSolicitudes() {
		return Solicitudes;
	}

	public void setSolicitudes(List<Solicitud> solicitudes) {
		Solicitudes = solicitudes;
	}

	public Set<Habilidad> getHabilidades() {
		return Habilidades;
	}

	public void setHabilidades(Set<Habilidad> habilidades) {
		Habilidades = habilidades;
	}

	
}
