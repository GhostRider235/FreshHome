package com.project.ProyectoFreshhome.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ProyectoFreshhome.entities.Pago;
import com.project.ProyectoFreshhome.repository.PagoRepository;
import com.project.ProyectoFreshhome.service.PagoService;

@Service
public class PagoServiceImpl implements PagoService {

	    @Autowired
	    private PagoRepository pagoRepository;

	    @Override
	    public List<Pago> mostrarPagos() {
	        return pagoRepository.findAll();
	    }


	    @Override
	    public Pago agregar(Pago pago) {
	        return pagoRepository.save(pago);
	    }

	    @Override
	    public void eliminar(int id) {
	        pagoRepository.deleteById(id);
	    }

		@Override
		public Optional<Pago> buscarPago(int id) {
	        return pagoRepository.findById(id);
		}
	
}
