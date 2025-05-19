package com.FreshHome.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.FreshHome.app.jwt.JWTService;
import com.FreshHome.app.model.HabilidadesEntity;
import com.FreshHome.app.service.HabilidadesService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/app/employee")
public class EmployeeControl {

    @Autowired
    private HabilidadesService habilidadesService;
    
    @Autowired
    private JWTService jwtService;

    @GetMapping("/home")
    public String home() {
        return "employee/home";
    }

    @GetMapping("/perfil-empleado")
    public String perfilEmpleado() {
        return "employee/perfilEmpleado";
    }

    @GetMapping("/registrar-habilidad")
    public String mostrarFormularioHabilidad(Model model, HttpServletRequest request) {
        System.out.println("=== Intentando acceder a registrar-habilidad ===");
        
        String token = jwtService.obtenerTokenCookie(request);
        System.out.println("Token encontrado: " + (token != null ? "Sí" : "No"));
        
        if (token == null) {
            System.out.println("No se encontró token, redirigiendo a login");
            return "redirect:/auth/login";
        }
        
        try {
            String email = jwtService.getUsername(token);
            int idUsuario = jwtService.obtenerMongoId(token);
            System.out.println("Email del usuario: " + email);
            System.out.println("ID del usuario: " + idUsuario);
            
            HabilidadesEntity habilidad = new HabilidadesEntity();
            habilidad.setIdUsuario(idUsuario);
            model.addAttribute("habilidad", habilidad);
            return "employee/registrarHabilidad";
        } catch (Exception e) {
            System.out.println("Error al procesar el token: " + e.getMessage());
            return "redirect:/auth/login";
        }
    }

    @PostMapping("/guardar-habilidad")
    public String guardarHabilidad(@RequestParam("habilidad") String nombreHabilidad,
                                 @RequestParam("añosExperiencia") Integer añosExperiencia,
                                 HttpServletRequest request) {
        String token = jwtService.obtenerTokenCookie(request);
        if (token == null) {
            return "redirect:/auth/login";
        }
        
        try {
            int idUsuario = jwtService.obtenerMongoId(token);
            
            HabilidadesEntity habilidad = new HabilidadesEntity();
            habilidad.setHabilidad(nombreHabilidad);
            habilidad.setAñosExperiencia(añosExperiencia);
            habilidad.setIdUsuario(idUsuario);
            
            habilidadesService.create(habilidad);
            return "redirect:/app/employee/home";
        } catch (Exception e) {
            System.out.println("Error al guardar la habilidad: " + e.getMessage());
            return "redirect:/app/employee/registrar-habilidad?error=true";
        }
    }
}
