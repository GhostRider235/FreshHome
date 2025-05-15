package com.FreshHome.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FreshHome.app.model.RolesSesiones;

@Repository
public interface RolesSesionesRepository extends JpaRepository<RolesSesiones, Long> {

}
