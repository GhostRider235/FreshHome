package com.project.ProyectoFreshhome.service;

import java.util.List;
import java.util.Optional;

import com.project.ProyectoFreshhome.entities.Habilidad;

public interface HabilidadService {
    List<Habilidad> mostrarHabilidades();
    Optional<Habilidad> buscarHabilidad(int id);
    Habilidad agregar(Habilidad habilidad);
    void eliminar(int id);
}
