package com.project.ProyectoFreshhome.service;

import java.util.List;
import java.util.Optional;

import com.project.ProyectoFreshhome.entities.Pago;

public interface PagoService {
    List<Pago> findAll();
    Optional<Pago> findById(int id);
    Pago save(Pago pago);
    void deleteById(int id);
}

