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
    public List<Solicitud> findAll() {
        return solicitudRepository.findAll();
    }

    @Override
    public Optional<Solicitud> findById(int id) {
        return solicitudRepository.findById(id);
    }

    @Override
    public Solicitud save(Solicitud solicitud) {
        return solicitudRepository.save(solicitud);
    }

    @Override
    public void deleteById(int id) {
        solicitudRepository.deleteById(id);
    }
}
