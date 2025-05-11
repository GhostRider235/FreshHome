package com.FreshHome.app.service;

import java.util.List;

import com.FreshHome.app.model.SolicitudEntity;
import com.FreshHome.app.model.UsuarioEntity;

public interface ActionsSolicitud {
	SolicitudEntity AgregarSolicitudCliente(SolicitudEntity solicitud);
	void ConfirmarSolicitudEmpleado(SolicitudEntity solicitud,UsuarioEntity Empleado);
	void EliminarSolicitud(SolicitudEntity solicitud);
	SolicitudEntity CalificarEmpleado(SolicitudEntity solicitud,String observacion,int puntuacion);
	SolicitudEntity CalificarCliente(SolicitudEntity solicitud,String observacion,int puntuacion);
	List<SolicitudEntity> listaSolicitudes();
}
