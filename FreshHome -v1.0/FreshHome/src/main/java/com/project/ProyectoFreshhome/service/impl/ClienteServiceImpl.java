package com.project.ProyectoFreshhome.service.impl;

import com.project.ProyectoFreshhome.entities.Cliente;
import com.project.ProyectoFreshhome.repository.ClienteRepository;
import com.project.ProyectoFreshhome.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> findById(int id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void deleteById(int id) {
        clienteRepository.deleteById(id);
    }
}
