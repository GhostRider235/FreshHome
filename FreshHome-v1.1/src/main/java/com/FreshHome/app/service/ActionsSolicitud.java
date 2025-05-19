package com.FreshHome.app.service;

import java.util.List;

import com.FreshHome.app.model.SolicitudEntity;
import com.FreshHome.app.model.UsuarioEntity;
import com.FreshHome.app.model.UsuarioSesiones;

public interface ActionsSolicitud {
	SolicitudEntity AgregarSolicitudCliente(SolicitudEntity solicitud,UsuarioSesiones Cliente);
	void ConfirmarSolicitudEmpleado(SolicitudEntity solicitud,UsuarioEntity Empleado);
	void EliminarSolicitud(SolicitudEntity solicitud);
	SolicitudEntity CalificarEmpleado(SolicitudEntity solicitud,String observacion,int puntuacion);
	SolicitudEntity CalificarCliente(SolicitudEntity solicitud,String observacion,int puntuacion);
	List<SolicitudEntity> listaSolicitudes();
	List<SolicitudEntity> listaSolicitudesCliente();
	List<SolicitudEntity> listaSolicitudesEmpleado();
}
