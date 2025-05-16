package com.FreshHome.app.controllers;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.FreshHome.app.model.HabilidadesEntity;
import com.FreshHome.app.service.HabilidadesService;

@Controller
@RequestMapping("/habilidades")
public class HabilidadesController {

    private final HabilidadesService habilidadesService;

    @Autowired
    public HabilidadesController(HabilidadesService habilidadesService) {
        this.habilidadesService = habilidadesService;
    }

    @GetMapping
    public String listarHabilidades(Model model) {
        model.addAttribute("habilidades", habilidadesService.getAllHabilidadesEntities());
        return "habilidades/listar"; 
    }

    @GetMapping("/nueva")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("habilidad", new HabilidadesEntity());
        return "habilidades/formulario"; 
    }

    @PostMapping
    public String guardarHabilidad(@ModelAttribute("habilidad") HabilidadesEntity habilidad) {
        habilidadesService.create(habilidad);
        return "redirect:/habilidades";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable String id, Model model) {
        habilidadesService.getHabilidadesEntityById(new ObjectId(id)).ifPresent(habilidad ->
            model.addAttribute("habilidad", habilidad)
        );
        return "habilidades/formulario"; 
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarHabilidad(@PathVariable String id, @ModelAttribute("habilidad") HabilidadesEntity habilidad) {
        habilidadesService.updateHabilidadesEntities(habilidad, new ObjectId(id));
        return "redirect:/habilidades";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarHabilidad(@PathVariable String id) {
        habilidadesService.getHabilidadesEntityById(new ObjectId(id)).ifPresent(habilidad ->
            habilidadesService.delete(habilidad)
        );
        return "redirect:/habilidades";
    }

}
