package com.FreshHome.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FreshHome.app.model.UsuarioSesiones;

@Repository
public interface UsuarioSesionesRepository extends JpaRepository<UsuarioSesiones, Long>{
	Optional<UsuarioSesiones> findByemail(String email);

}
