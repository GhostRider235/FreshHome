package com.project.ProyectoFreshhome.service.impl;

import com.project.ProyectoFreshhome.entities.Solicitud;
import com.project.ProyectoFreshhome.repository.SolicitudRepository;
import com.project.ProyectoFreshhome.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Override
    public List<Solicitud> listaSolicitudes() {
        return solicitudRepository.findAll();
    }

    @Override
    public Optional<Solicitud> BuscarSolicitud(int id) {
        return solicitudRepository.findById(id);
    }

    @Override
    public Solicitud agregar(Solicitud solicitud) {
        return solicitudRepository.save(solicitud);
    }

    @Override
    public void eliminar(int id) {
        solicitudRepository.deleteById(id);
    }
}
