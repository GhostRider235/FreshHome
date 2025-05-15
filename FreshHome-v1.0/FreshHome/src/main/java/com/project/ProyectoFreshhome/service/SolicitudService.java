package com.project.ProyectoFreshhome.service;

import java.util.List;
import java.util.Optional;

import com.project.ProyectoFreshhome.entities.Solicitud;

public interface SolicitudService {
    List<Solicitud> listaSolicitudes();
    Optional<Solicitud> BuscarSolicitud(int id);
    Solicitud agregar(Solicitud solicitud);
    void eliminar(int id);
}
