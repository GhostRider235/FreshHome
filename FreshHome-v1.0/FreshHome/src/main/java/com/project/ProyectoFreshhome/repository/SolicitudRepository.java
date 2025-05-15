package com.project.ProyectoFreshhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ProyectoFreshhome.entities.Solicitud;

public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {
}
