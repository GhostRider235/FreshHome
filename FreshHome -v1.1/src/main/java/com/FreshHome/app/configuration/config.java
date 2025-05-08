package com.FreshHome.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.FreshHome.app.repository.UsuarioSesionesRepository;
import com.FreshHome.app.utilities.SuccessHandler;

@Configuration
@EnableWebSecurity
public class config {
	
	@Autowired
	private UsuarioSesionesRepository rep;
	
	//Encriptar contraseñas
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return usuario -> {
			if (usuario == null || usuario.isBlank()) {
				throw new IllegalArgumentException("Por favor ingrese un correo valido.");
			}
			return rep.findByemail(usuario).orElseThrow(() -> new UsernameNotFoundException("usuario no encontrado."));
		};
	}
	
	//Proveedor de autenticacion
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(passwordEncoder());
		dao.setUserDetailsService(userDetailsService());
		return dao;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
		.authorizeHttpRequests(auth -> auth
                //Rutas a las que se puede acceder con un rol despues de iniciar sesion
                .requestMatchers("/init").permitAll()
                .requestMatchers("/app/client/**").hasRole("CLIENT")
                .requestMatchers("/app/employee/**").hasRole("EMPLOYEE")
                .requestMatchers("/admin/*").hasRole("AMDIN")
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
