package com.project.ProyectoFreshhome.entities;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "solicitud")
public class Solicitud {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSolicitud;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@Column(name = "fechaSolicitud", nullable = false)
	private Date fechaSolicitud;

	@Column(name = "nombreCliente", nullable = false)
	private String nombreCliente;
	
	//Se deja igual ya que en una relacion uno a uno y mas cuando 
	//solo usaras el atributo como tal.
	@Column(name = "direccionCliente", nullable = false)
	private String direccionCliente;

	@Column(name = "tarifa", nullable = false)
	private int tarifa;

	@Column(name = "IdPago", nullable = false)
	private int idPago;

	
	//Relacion de IdCliente con la clase cliente como tal.
	@OneToOne
	@JoinColumn(name = "IdCliente", nullable = false)
	private Cliente cliente;

	@Column(name = "IdEmpleado", nullable = false)
	private int idEmpleado;

	@Column(name = "IdHabilidad", nullable = false)
	private int idHabilidad;

	@Column(name = "CalificacionCliente", nullable = false)
	private int calificacionCliente;

	@Column(name = "ObservacioneCliente", nullable = false)
	private String observacioneCliente;

	@Column(name = "CalificacionEmpleado", nullable = false)
	private int calificacionEmpleado;

	@Column(name = "ObservacioneEmpleado", nullable = false)
	private String observacioneEmpleado;

	// Getters y setters
	public int getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
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

	public int getTarifa() {
		return tarifa;
	}

	public void setTarifa(int tarifa) {
		this.tarifa = tarifa;
	}

	public int getIdPago() {
		return idPago;
	}

	public void setIdPago(int idPago) {
		this.idPago = idPago;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getIdCliente() {
		return cliente;
	}

	public void setIdCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public int getIdHabilidad() {
		return idHabilidad;
	}

	public void setIdHabilidad(int idHabilidad) {
		this.idHabilidad = idHabilidad;
	}

	public int getCalificacionCliente() {
		return calificacionCliente;
	}

	public void setCalificacionCliente(int calificacionCliente) {
		this.calificacionCliente = calificacionCliente;
	}

	public String getObservacioneCliente() {
		return observacioneCliente;
	}

	public void setObservacioneCliente(String observacioneCliente) {
		this.observacioneCliente = observacioneCliente;
	}

	public int getCalificacionEmpleado() {
		return calificacionEmpleado;
	}

	public void setCalificacionEmpleado(int calificacionEmpleado) {
		this.calificacionEmpleado = calificacionEmpleado;
	}

	public String getObservacioneEmpleado() {
		return observacioneEmpleado;
	}

	public void setObservacioneEmpleado(String observacioneEmpleado) {
		this.observacioneEmpleado = observacioneEmpleado;
	}
}
