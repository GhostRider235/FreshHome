package com.project.ProyectoFreshhome.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ProyectoFreshhome.entities.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
}
