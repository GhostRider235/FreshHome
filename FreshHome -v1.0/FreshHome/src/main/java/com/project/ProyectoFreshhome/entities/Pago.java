package com.project.ProyectoFreshhome.entities;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "pagos")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPago;
    
    @Column(name = "Metodo",nullable = false,length = 50)
    private String metodo;
    
    @Column(name = "tarifa",nullable = false)
    private int tarifa;
    
    @Column(name = "FechaPago",nullable = false)
    private Date fechaPago;
    
    @Column(name = "idSolicitud",nullable = false)
    private int idSolicitud;

    // Getters y setters
    public int getIdPago() { return idPago; }
    public void setIdPago(int idPago) { this.idPago = idPago; }

    public String getMetodo() { return metodo; }
    public void setMetodo(String metodo) { this.metodo = metodo; }

    public int getTarifa() { return tarifa; }
    public void setTarifa(int tarifa) { this.tarifa = tarifa; }

    public Date getFechaPago() { return fechaPago; }
    public void setFechaPago(Date fechaPago) { this.fechaPago = fechaPago; }

    public int getIdSolicitud() { return idSolicitud; }
    public void setIdSolicitud(int idSolicitud) { this.idSolicitud = idSolicitud; }
}
