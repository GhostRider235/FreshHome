package com.prototype.FreshHome.models;

import java.time.LocalDate;

public class Pago {
	private String IdPago;
	private String Metodo;
	private double tarifa;
	private LocalDate FechaPago;
	private String idSolicitud;
	
	public Pago(String idPago, String metodo, double tarifa, LocalDate fechaPago, String idSolicitud) {
		super();
		IdPago = idPago;
		Metodo = metodo;
		this.tarifa = tarifa;
		FechaPago = fechaPago;
		this.idSolicitud = idSolicitud;
	}
	public Pago() {
		super();
	}
	public String getIdPago() {
		return IdPago;
	}
	public void setIdPago(String idPago) {
		IdPago = idPago;
	}
	public String getMetodo() {
		return Metodo;
	}
	public void setMetodo(String metodo) {
		Metodo = metodo;
	}
	public double getTarifa() {
		return tarifa;
	}
	public void setTarifa(double tarifa) {
		this.tarifa = tarifa;
	}
	public LocalDate getFechaPago() {
		return FechaPago;
	}
	public void setFechaPago(LocalDate fechaPago) {
		FechaPago = fechaPago;
	}
	public String getIdSolicitud() {
		return idSolicitud;
	}
	public void setIdSolicitud(String idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	
	
	
}
