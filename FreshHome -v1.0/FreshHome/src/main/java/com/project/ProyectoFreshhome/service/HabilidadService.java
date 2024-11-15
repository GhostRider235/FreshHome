package com.project.ProyectoFreshhome.service;

import java.util.List;
import java.util.Optional;

import com.project.ProyectoFreshhome.entities.Habilidad;

public interface HabilidadService {
    List<Habilidad> findAll();
    Optional<Habilidad> findById(int id);
    Habilidad save(Habilidad habilidad);
    void deleteById(int id);
}
