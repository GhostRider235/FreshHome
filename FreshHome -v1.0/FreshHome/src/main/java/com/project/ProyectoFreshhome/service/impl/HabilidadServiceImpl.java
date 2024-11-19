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
    public List<Habilidad> mostrarHabilidades() {
        return habilidadRepository.findAll();
    }

    @Override
    public Optional<Habilidad> buscarHabilidad(int id) {
        return habilidadRepository.findById(id);
    }

    @Override
    public Habilidad agregar(Habilidad habilidad) {
        return habilidadRepository.save(habilidad);
    }

    @Override
    public void eliminar(int id) {
        habilidadRepository.deleteById(id);
    }
}
