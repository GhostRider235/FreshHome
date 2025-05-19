package com.FreshHome.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FreshHome.app.model.SolicitudEntity;
import com.FreshHome.app.repository.SolicitudRepository;

@Service
public class DashboardServices {
	
	@Autowired
	private SolicitudRepository rep;
	
	public Map<String,Object> mostrarSolicitudes() {
        LocalDateTime inicio = LocalDate.now().minusMonths(11).withDayOfMonth(1).atStartOfDay();
        LocalDateTime fin = LocalDate.now().plusMonths(1).withDayOfMonth(1).atStartOfDay();	
        
        List<SolicitudEntity> solicitudes = rep.findByfechaSolicitudBetween(inicio, fin);
        
        // El conteo de meses
        Map<Integer, Long> conteoPorMes = solicitudes.stream()
                .collect(Collectors.groupingBy(
                    s -> s.getFechaSolicitud().getMonthValue(), 
                    TreeMap::new,
                    Collectors.counting()
                ));
        
        List<String> labels = conteoPorMes.keySet().stream()
                .map(m -> Month.of(m)
                               .getDisplayName(TextStyle.SHORT, new Locale("es")))
                .collect(Collectors.toList());
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("labels", conteoPorMes.keySet());
        respuesta.put("valores", conteoPorMes.values());
        
        return respuesta;
 	}
}
