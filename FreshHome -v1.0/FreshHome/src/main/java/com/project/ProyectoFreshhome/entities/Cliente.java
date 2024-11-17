package com.project.ProyectoFreshhome.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCliente;

	@Column(name = "nombreCliente", nullable = false, length = 50)
	private String nombreCliente;

	@Column(name = "Correo", nullable = false, length = 50)
	private String correo;

	@Column(name = "FechaNacimiento", nullable = false)
	private Date fechaNacimiento;

	@Column(name = "edad", nullable = false)
	private int edad;

	@Column(name = "direccionCliente", nullable = false)
	private String direccionCliente;

	@Column(name = "CalificacionCliente", nullable = false)
	private int calificacionCliente;

	@OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Solicitud solicitud;

	@Column(name = "Contraseña", nullable = false, length = 50)
	private String contrasena;
	
	//Cuando es una relacion de uno a muchos en la que hace de uno se crea una lista
	//con objetos de la otra clase que hace de muchos.
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Solicitud> Solicitudes = new ArrayList<>(); 

	
	//Constructores
	public Cliente() {
		super();
	}

	public Cliente(String nombreCliente, String correo, Date fechaNacimiento, int edad, String direccionCliente,
			int calificacionCliente, String contrasena) {
		super();
		this.nombreCliente = nombreCliente;
		this.correo = correo;
		this.fechaNacimiento = fechaNacimiento;
		this.edad = edad;
		this.direccionCliente = direccionCliente;
		this.calificacionCliente = calificacionCliente;
		this.contrasena = contrasena;
	}

	// Getters y setters
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getDireccionCliente() {
		return direccionCliente;
	}

	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}

	public int getCalificacionCliente() {
		return calificacionCliente;
	}

	public void setCalificacionCliente(int calificacionCliente) {
		this.calificacionCliente = calificacionCliente;
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
	
}
