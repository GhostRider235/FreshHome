package com.project.ProyectoFreshhome;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.project.ProyectoFreshhome.entities.Cliente;
import com.project.ProyectoFreshhome.repository.ClienteRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.project.ProyectoFreshhome.repository")
public class ProyectoFreshhomeApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(ProyectoFreshhomeApplication.class, args);
    }

    @Autowired
    private ClienteRepository re;
    
	@Override
	public void run(String... args) throws Exception {
		
		//Casting de LocalDate a Date
		LocalDate f = LocalDate.of(2000, Month.AUGUST, 5);
		Date d = Date.valueOf(f);
		
		//Obejto de ejemplo
		Cliente c = new Cliente("Luis", "Luchomaniaco@outlook.es",d, 15, "", 0, "tupac");
		
	}
}
