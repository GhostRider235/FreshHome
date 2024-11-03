package com.prototype.FreshHome.models;

import java.time.LocalDate;

public class Cliente {
    private String nombreCliente;
    private String IdCliente;
    private String Correo;
    private String direccionCliente;
    private LocalDate FechaNacimiento;
    private short edad;
    private int CalificacionCliente;
	
    
    public Cliente() {
		super();
	}
	public Cliente(String nombreCliente, String idCliente, String correo, String direccionCliente, LocalDate fechaNacimiento,
			short edad, int calificacionCliente) {
		super();
		this.nombreCliente = nombreCliente;
		IdCliente = idCliente;
		Correo = correo;
		this.direccionCliente = direccionCliente;
		FechaNacimiento = fechaNacimiento;
		this.edad = edad;
		CalificacionCliente = calificacionCliente;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getIdCliente() {
		return IdCliente;
	}
	public void setIdCliente(String idCliente) {
		IdCliente = idCliente;
	}
	public String getCorreo() {
		return Correo;
	}
	public void setCorreo(String correo) {
		Correo = correo;
	}
	public String getDireccionCliente() {
		return direccionCliente;
	}
	public void setDireccion(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}
	public LocalDate getFechaNacimiento() {
		return FechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		FechaNacimiento = fechaNacimiento;
	}
	public short getEdad() {
		return edad;
	}
	public void setEdad(short edad) {
		this.edad = edad;
	}
	public int getCalificacionCliente() {
		return CalificacionCliente;
	}
	public void setCalificacionCliente(int calificacionCliente) {
		CalificacionCliente = calificacionCliente;
	}
    
}
