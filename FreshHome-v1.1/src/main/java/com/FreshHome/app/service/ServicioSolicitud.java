package com.FreshHome.app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FreshHome.app.model.SolicitudEntity;
import com.FreshHome.app.model.UsuarioEntity;
import com.FreshHome.app.model.UsuarioSesiones;
import com.FreshHome.app.repository.SolicitudRepository;

@Service
public class ServicioSolicitud implements ActionsSolicitud {
	@Autowired
	private SolicitudRepository rep;
	
	@Autowired
	private UsuarioSesiones sesion;

	@Override
	public SolicitudEntity AgregarSolicitudCliente(SolicitudEntity solicitud) {
		solicitud.setFechaSolicitud(LocalDateTime.now());
		return rep.save(solicitud);
	}
	
	@Override
	public SolicitudEntity CalificarEmpleado(SolicitudEntity solicitud,String observacion, int puntuacion) {
		solicitud.setCalificacionEmpleado(puntuacion);
		solicitud.setObservacioneEmpleado(observacion);
		return rep.save(solicitud);
	}

	@Override
	public SolicitudEntity CalificarCliente(SolicitudEntity solicitud,String observacion, int puntuacion) {
		solicitud.setCalificacionCliente(puntuacion);
		solicitud.setObservacioneCliente(observacion);
		return rep.save(solicitud);
	}



	@Override
	public void EliminarSolicitud(SolicitudEntity solicitud) {
		rep.delete(solicitud);
	}

	@Override
	public List<SolicitudEntity> listaSolicitudes() {
		return rep.findAll();
	}

	@Override
	public void ConfirmarSolicitudEmpleado(SolicitudEntity solicitud, UsuarioEntity Empleado) {
		solicitud.setIdUsuarioAplicante(Empleado.getIdUsuario());
	}



}
