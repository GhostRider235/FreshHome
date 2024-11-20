package com.project.ProyectoFreshhome.service.impl;

import com.project.ProyectoFreshhome.entities.Empleado;
import com.project.ProyectoFreshhome.repository.EmpleadoRepository;
import com.project.ProyectoFreshhome.service.EmpleadoService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository rep;
    
    @Value("${jwt.secret}")
    private String firmaAutorizada = "gtu98#4n6$fr&d/23";

    @Override
    public List<Empleado> mostrarEmpleados() {
        return rep.findAll();
    }

    @Override
    public Optional<Empleado> buscarEmpleado(int id) {
        return rep.findById(id);
    }

    @Override
    public Empleado agregar(Empleado empleado) {
        return rep.save(empleado);
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
	public Empleado login(String correo, String contraseña) {
		Empleado empleado = rep.findByCorreo(correo).
				orElseThrow(()-> new RuntimeException("No se encotnro el usuario."));
		if (!(contraseña.equals(empleado.getContrasena()))) {
			throw new RuntimeException("La contraseña es incorrecta.");
		}
		return empleado;
	}

	@Override
	public String token(Empleado empleado) {
		Map<String, Object> c = new HashMap<>();
		c.put("correo", empleado.getCorreo());
		c.put("Id",empleado.getIdEmpleado());
		
		return Jwts.builder()
				.setSubject(String.valueOf(empleado.getIdEmpleado()))
                .setClaims(c)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
                .signWith(SignatureAlgorithm.HS256, firmaAutorizada)
                .compact();
	}
}
