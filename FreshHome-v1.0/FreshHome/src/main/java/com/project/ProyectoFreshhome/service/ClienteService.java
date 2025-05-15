package com.project.ProyectoFreshhome.service;

import java.util.List;
import java.util.Optional;

import com.project.ProyectoFreshhome.entities.Cliente;

public interface ClienteService {
    List<Cliente> mostrarClientes();
    Optional<Cliente> buscarCliente(int id);
    Cliente agregar(Cliente cliente);
    int tamaño();
    Cliente login(String correo,String contraseña);
    String token(Cliente cliente);
    Cliente Actualizardatos(Cliente cliente);
    void eliminar(Cliente cliente);
}
