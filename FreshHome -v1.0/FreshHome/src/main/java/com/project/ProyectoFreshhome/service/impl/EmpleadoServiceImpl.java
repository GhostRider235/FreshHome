package com.project.ProyectoFreshhome.service.impl;

import com.project.ProyectoFreshhome.entities.Empleado;
import com.project.ProyectoFreshhome.repository.EmpleadoRepository;
import com.project.ProyectoFreshhome.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> findById(int id) {
        return empleadoRepository.findById(id);
    }

    @Override
    public Empleado save(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public void deleteById(int id) {
        empleadoRepository.deleteById(id);
    }
}
