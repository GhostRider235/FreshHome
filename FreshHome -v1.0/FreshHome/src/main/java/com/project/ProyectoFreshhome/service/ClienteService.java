package com.project.ProyectoFreshhome.service;

import java.util.List;
import java.util.Optional;

import com.project.ProyectoFreshhome.entities.Cliente;

public interface ClienteService {
    List<Cliente> findAll();
    Optional<Cliente> findById(int id);
    Cliente save(Cliente cliente);
    void deleteById(int id);
}
