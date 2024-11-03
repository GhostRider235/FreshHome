package com.prototype.FreshHome.models;

public class Habilidad {
    private String Habilidad;
    private String IdHabilidad;
    private String IdEmpleado;
	
    
    public Habilidad() {
		super();
	}
	public Habilidad(String habilidad, String idHabilidad, String idEmpleado) {
		super();
		Habilidad = habilidad;
		IdHabilidad = idHabilidad;
		IdEmpleado = idEmpleado;
	}
	public String getHabilidad() {
		return Habilidad;
	}
	public void setHabilidad(String habilidad) {
		Habilidad = habilidad;
	}
	public String getIdHabilidad() {
		return IdHabilidad;
	}
	public void setIdHabilidad(String idHabilidad) {
		IdHabilidad = idHabilidad;
	}
	public String getIdEmpleado() {
		return IdEmpleado;
	}
	public void setIdEmpleado(String idEmpleado) {
		IdEmpleado = idEmpleado;
	}
    
    
}
