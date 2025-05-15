package com.FreshHome.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.FreshHome.app.jwt.JWTFilter;
import com.FreshHome.app.jwt.JWTService;
import com.FreshHome.app.model.UsuarioSesiones;
import com.FreshHome.app.repository.UsuarioSesionesRepository;
import com.FreshHome.app.service.AuthService;
import com.FreshHome.app.service.UserDetailsServiceCustom;

@Configuration
@EnableWebSecurity
public class config {

	@Autowired
	private  UsuarioSesionesRepository rep;
	
	@Autowired
	private  JWTService jwtService;



	@Bean
    public UsuarioSesiones usuarioSesiones() {
        return new UsuarioSesiones();
    }

	@Bean
	public UserDetailsService userDetailsService() {
		return email -> rep.findByemail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
	}
	
	@Bean
	public JWTFilter jwtFilter(@Qualifier("authService")UserDetailsService userdetails) {
		return new JWTFilter(jwtService, userdetails);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}



	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setUserDetailsService(userDetailsService());
		dao.setPasswordEncoder(passwordEncoder());
		return dao;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http,JWTFilter filter) throws Exception {
		return http
				.authorizeHttpRequests(auth -> auth
						//Manejo de rutas
						.requestMatchers("/home/init", "/auth/**", "/css/**", "/js/**").permitAll()
						.requestMatchers("/app/client/**").hasRole("CLIENT")
						.requestMatchers("/app/employee/**").hasRole("EMPLOYEE")
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.anyRequest().authenticated())
				.formLogin(form -> form
						.loginPage("/auth/login").permitAll())
				// Cierre de sesion
				.logout(logout -> logout
						.logoutUrl("/auth/logout")
						.logoutSuccessUrl("/auth/login?logout")
						.deleteCookies("AUTH_TOKEN").permitAll())
				// Filtro de JWT
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
}

