package com.FreshHome.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.FreshHome.app.utilities.SuccessHandler;

@Configuration
@EnableWebSecurity
public class config {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
		.authorizeHttpRequests(auth -> auth
                .requestMatchers("(rutas publicas)").permitAll()
                //Rutas a las que se puede acceder con un rol despues de iniciar sesion
                .requestMatchers("/app/client").hasRole("CLIENT")
                .requestMatchers("/app/employee").hasRole("EMPLOYEE")
                .requestMatchers("/admin").hasRole("AMDIN")
                //proteger el resto de rutas
                .anyRequest().authenticated()
        )
        .formLogin(login -> login
			//Pagina de login presonalizada
        		.loginPage("/auth/login")
        		.successHandler(new SuccessHandler())
        		.permitAll()
                )
        .logout(out -> out
			//Cerrado de sesion
        		.logoutUrl("/login?logout")
        		.permitAll()
        		)
        .build();
		
	}


}
