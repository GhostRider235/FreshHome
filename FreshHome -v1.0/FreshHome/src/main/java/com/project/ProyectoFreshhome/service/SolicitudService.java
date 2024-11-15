package com.project.ProyectoFreshhome.service;

import java.util.List;
import java.util.Optional;

import com.project.ProyectoFreshhome.entities.Solicitud;

public interface SolicitudService {
    List<Solicitud> findAll();
    Optional<Solicitud> findById(int id);
    Solicitud save(Solicitud solicitud);
    void deleteById(int id);
}
