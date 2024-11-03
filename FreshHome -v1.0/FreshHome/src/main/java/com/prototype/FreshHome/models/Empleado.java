package com.prototype.FreshHome.models;

import java.time.LocalDate;

public class Empleado {
    private String IdEmpleado;
    private String nombreEmpleado;
    private String Correo;
    private LocalDate FechaNacimiento;
    private short añosExperiencia;
    private short edad;
    private int CalificacionEmpleado;
	
    
    public Empleado() {
		super();
	}
	public Empleado(String idEmpleado, String nombreEmpleado, String correo, LocalDate fechaNacimiento,
			short añosExperiencia, short edad, int calificacionEmpleado) {
		super();
		IdEmpleado = idEmpleado;
		this.nombreEmpleado = nombreEmpleado;
		Correo = correo;
		FechaNacimiento = fechaNacimiento;
		this.añosExperiencia = añosExperiencia;
		this.edad = edad;
		CalificacionEmpleado = calificacionEmpleado;
	}
	public String getIdEmpleado() {
		return IdEmpleado;
	}
	public void setIdEmpleado(String idEmpleado) {
		IdEmpleado = idEmpleado;
	}
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	public String getCorreo() {
		return Correo;
	}
	public void setCorreo(String correo) {
		Correo = correo;
	}
	public LocalDate getFechaNacimiento() {
		return FechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		FechaNacimiento = fechaNacimiento;
	}
	public short getAñosExperiencia() {
		return añosExperiencia;
	}
	public void setAñosExperiencia(short añosExperiencia) {
		this.añosExperiencia = añosExperiencia;
	}
	public short getEdad() {
		return edad;
	}
	public void setEdad(short edad) {
		this.edad = edad;
	}
	public int getCalificacionEmpleado() {
		return CalificacionEmpleado;
	}
	public void setCalificacionEmpleado(int calificacionEmpleado) {
		CalificacionEmpleado = calificacionEmpleado;
	}
    
    
}
