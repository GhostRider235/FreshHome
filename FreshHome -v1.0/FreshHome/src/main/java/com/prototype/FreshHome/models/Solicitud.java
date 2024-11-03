package com.prototype.FreshHome.models;

import java.time.LocalDate;

public class Solicitud {
    private String IdSolicitud;
    private String descripcion;
    private LocalDate fechaSolicitud;
    private String nombreCliente;
    private String direccionCliente;
    private double tarifa;
    private String IdPago;
    private String IdCliente;
    private String IdEmpleado;
    private String IdHabilidad;
    private int CalificacionCliente;
    private String ObservacionCliente;
    private int CalificacionEmpleado;
    private String ObservacionEmpleado;
	
    public Solicitud() {
		super();
	}
	public Solicitud(String idSolicitud, String descripcion, LocalDate fechaSolicitud, String nombreCliente,
			String direccionCliente, double tarifa, String idPago, String idCliente, String idEmpleado,
			String idHabilidad, int calificacionCliente, String observacionCliente, int calificacionEmpleado,
			String observacionEmpleado) {
		super();
		IdSolicitud = idSolicitud;
		this.descripcion = descripcion;
		this.fechaSolicitud = fechaSolicitud;
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.tarifa = tarifa;
		IdPago = idPago;
		IdCliente = idCliente;
		IdEmpleado = idEmpleado;
		IdHabilidad = idHabilidad;
		CalificacionCliente = calificacionCliente;
		ObservacionCliente = observacionCliente;
		CalificacionEmpleado = calificacionEmpleado;
		ObservacionEmpleado = observacionEmpleado;
	}
	public String getIdSolicitud() {
		return IdSolicitud;
	}
	public void setIdSolicitud(String idSolicitud) {
		IdSolicitud = idSolicitud;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LocalDate getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(LocalDate fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getDireccionCliente() {
		return direccionCliente;
	}
	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}
	public double getTarifa() {
		return tarifa;
	}
	public void setTarifa(double tarifa) {
		this.tarifa = tarifa;
	}
	public String getIdPago() {
		return IdPago;
	}
	public void setIdPago(String idPago) {
		IdPago = idPago;
	}
	public String getIdCliente() {
		return IdCliente;
	}
	public void setIdCliente(String idCliente) {
		IdCliente = idCliente;
	}
	public String getIdEmpleado() {
		return IdEmpleado;
	}
	public void setIdEmpleado(String idEmpleado) {
		IdEmpleado = idEmpleado;
	}
	public String getIdHabilidad() {
		return IdHabilidad;
	}
	public void setIdHabilidad(String idHabilidad) {
		IdHabilidad = idHabilidad;
	}
	public int getCalificacionCliente() {
		return CalificacionCliente;
	}
	public void setCalificacionCliente(int calificacionCliente) {
		CalificacionCliente = calificacionCliente;
	}
	public String getObservacionCliente() {
		return ObservacionCliente;
	}
	public void setObservacionCliente(String observacionCliente) {
		ObservacionCliente = observacionCliente;
	}
	public int getCalificacionEmpleado() {
		return CalificacionEmpleado;
	}
	public void setCalificacionEmpleado(int calificacionEmpleado) {
		CalificacionEmpleado = calificacionEmpleado;
	}
	public String getObservacionEmpleado() {
		return ObservacionEmpleado;
	}
	public void setObservacionEmpleado(String observacionEmpleado) {
		ObservacionEmpleado = observacionEmpleado;
	}
    
    
    
}
