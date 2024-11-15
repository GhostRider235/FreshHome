package com.project.ProyectoFreshhome.service.impl;

import com.project.ProyectoFreshhome.entities.Habilidad;
import com.project.ProyectoFreshhome.repository.HabilidadRepository;
import com.project.ProyectoFreshhome.service.HabilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabilidadServiceImpl implements HabilidadService {

    @Autowired
    private HabilidadRepository habilidadRepository;

    @Override
    public List<Habilidad> findAll() {
        return habilidadRepository.findAll();
    }

    @Override
    public Optional<Habilidad> findById(int id) {
        return habilidadRepository.findById(id);
    }

    @Override
    public Habilidad save(Habilidad habilidad) {
        return habilidadRepository.save(habilidad);
    }

    @Override
    public void deleteById(int id) {
        habilidadRepository.deleteById(id);
    }
}
