package com.project.ProyectoFreshhome.service;

import java.util.List;
import java.util.Optional;

import com.project.ProyectoFreshhome.entities.Pago;

public interface PagoService {
    List<Pago> mostrarPagos();
    Optional<Pago> buscarPago(int id);
    Pago agregar(Pago pago);
    void eliminar(int id);
}

