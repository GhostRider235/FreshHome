package com.project.ProyectoFreshhome.service;

import java.util.List;
import java.util.Optional;

import com.project.ProyectoFreshhome.entities.Empleado;

public interface EmpleadoService {
    List<Empleado> findAll();
    Optional<Empleado> findById(int id);
    Empleado save(Empleado empleado);
    void deleteById(int id);
}
