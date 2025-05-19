package com.FreshHome.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FreshHome.app.service.DashboardServices;

@RestController
@RequestMapping("/api/dashboard")
public class RestDashboard {
	@Autowired
    private DashboardServices dashboardService;

    @GetMapping("/solicitudes")
    public Map<String, Object> solicitudesPorMes() {
        return dashboardService.mostrarSolicitudes();
    }}
