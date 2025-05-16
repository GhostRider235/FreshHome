package com.FreshHome.app.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FreshHome.app.model.HabilidadesEntity;
import com.FreshHome.app.repository.HabilidadesRepository;

@Service
public class HabilidadesService {

    private HabilidadesRepository habilidadesRepository;

    @Autowired
    public HabilidadesService(HabilidadesRepository habilidadesRepository) {
        this.habilidadesRepository = habilidadesRepository;
    }

    public void create(HabilidadesEntity habilidadesEntity) {
        habilidadesRepository.save(habilidadesEntity);
    }

    public List<HabilidadesEntity> getAllHabilidadesEntities() {
        return habilidadesRepository.findAll();
    }

    public Optional<HabilidadesEntity> getHabilidadesEntityById(ObjectId id) {
        return habilidadesRepository.findById(id);
    }

    public HabilidadesEntity updateHabilidadesEntities(HabilidadesEntity habilidadesEntityRequest, ObjectId id) {
        if (habilidadesEntityRequest == null || id == null) {
            throw new IllegalArgumentException("La entidad y el ID no pueden ser nulos");
        }

        return habilidadesRepository.findById(id)
                .map(habilidadesExistentes -> {
                    if (habilidadesEntityRequest.getHabilidad() != null) {
                        habilidadesExistentes.setHabilidad(habilidadesEntityRequest.getHabilidad());
                    }
                    if (habilidadesEntityRequest.getAñosExperiencia() != null) {
                        habilidadesExistentes.setAñosExperiencia(habilidadesEntityRequest.getAñosExperiencia());
                    }
                    if (habilidadesEntityRequest.getIdUsuario() != null) {
                        habilidadesExistentes.setIdUsuario(habilidadesEntityRequest.getIdUsuario());
                    }

                    return habilidadesRepository.save(habilidadesExistentes);
                })
                .orElseThrow(() -> new RuntimeException("No se encontró la habilidad con ID: " + id));
    }

    public void delete(HabilidadesEntity habilidad) {
        habilidadesRepository.delete(habilidad);
    }

}
