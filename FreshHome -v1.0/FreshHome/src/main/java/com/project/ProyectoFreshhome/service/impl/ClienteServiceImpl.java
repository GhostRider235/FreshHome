package com.project.ProyectoFreshhome.service.impl;

import com.project.ProyectoFreshhome.entities.Cliente;
import com.project.ProyectoFreshhome.repository.ClienteRepository;
import com.project.ProyectoFreshhome.service.ClienteService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository rep;
    
    @Value("${jwt.secret}")
    private String firmaAutorizada = "gtu98#4n6$fr&d/23";
    
    @Override
    public List<Cliente> mostrarClientes() {
        return rep.findAll();
    }

    @Override
    public Optional<Cliente> buscarCliente(int id) {
        return rep.findById(id);
    }

    @Override
    public Cliente agregar(Cliente cliente) {
        return rep.save(cliente);
    }

    @Override
    public void eliminar(int id) {
    	rep.deleteById(id);
    }

	@Override
	public int tamaño() {
		return (int) rep.count();
	}

	@Override
	public Cliente login(String correo,String contraseña) {
		Cliente cliente = rep.findByCorreo(correo)
				.orElseThrow(()->new RuntimeException("No se encuentra el usuario."));
		if (!(contraseña.equals(cliente.getContrasena()))) {
			throw new  RuntimeException("Contraseña incorrecta.");
		}
		return cliente;
	}

	@Override
	public String token(Cliente cliente) {
		Map<String, Object> c = new HashMap<>();
		c.put("correo", cliente.getCorreo());
		c.put("Id",cliente.getIdCliente());
		
		return Jwts.builder()
				.setSubject(String.valueOf(cliente.getIdCliente()))
                .setClaims(c)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
                .signWith(SignatureAlgorithm.HS256, firmaAutorizada)
                .compact();
	}
	
	
}
