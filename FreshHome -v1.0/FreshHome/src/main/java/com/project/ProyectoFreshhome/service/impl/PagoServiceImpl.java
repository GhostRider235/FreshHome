package com.project.ProyectoFreshhome.service.impl;

import com.project.ProyectoFreshhome.entities.Pago;
import com.project.ProyectoFreshhome.repository.PagoRepository;
import com.project.ProyectoFreshhome.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Override
    public List<Pago> findAll() {
        return pagoRepository.findAll();
    }

    @Override
    public Optional<Pago> findById(int id) {
        return pagoRepository.findById(id);
    }

    @Override
    public Pago save(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public void deleteById(int id) {
        pagoRepository.deleteById(id);
    }
}
