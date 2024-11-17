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

	@Column(name = "direccionCliente", nullable = false)
	private String direccionCliente;

	@Column(name = "tarifa", nullable = false)
	private int tarifa;

	
	//Relacion de IdCliente con la clase cliente.
	@ManyToOne
	@JoinColumn(name = "idCliente", nullable = false)
	private Cliente cliente;
	
	//Relacion de IdEmpleado con la clase empleado
	@ManyToOne
	@JoinColumn(name = "idEmpleado", nullable = false)
	private Empleado empleado;
	
	//Relacion de IdPago con la clase Pago
	@OneToOne(mappedBy = "solicitud",cascade = CascadeType.ALL)
	private Pago pago;
	

	@Column(name = "CalificacionCliente", nullable = false)
	private int calificacionCliente;

	@Column(name = "ObservacioneCliente", nullable = false)
	private String observacioneCliente;

	@Column(name = "CalificacionEmpleado", nullable = false)
	private int calificacionEmpleado;

	@Column(name = "ObservacioneEmpleado", nullable = false)
	private String observacioneEmpleado;

	//Constructores
	public Solicitud(String descripcion, Date fechaSolicitud, String nombreCliente, String direccionCliente, int tarifa,
			Cliente cliente, Empleado empleado, Pago pago, int calificacionCliente, String observacioneCliente,
			int calificacionEmpleado, String observacioneEmpleado) {
		super();
		this.descripcion = descripcion;
		this.fechaSolicitud = fechaSolicitud;
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.tarifa = tarifa;
		this.cliente = cliente;
		this.empleado = empleado;
		this.pago = pago;
		this.calificacionCliente = calificacionCliente;
		this.observacioneCliente = observacioneCliente;
		this.calificacionEmpleado = calificacionEmpleado;
		this.observacioneEmpleado = observacioneEmpleado;
	}
	
	
	public Solicitud() {
		super();
	}


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

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}
	
	
}
