package com.project.ProyectoFreshhome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.project.ProyectoFreshhome.repository")
public class ProyectoFreshhomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoFreshhomeApplication.class, args);
    }
}
