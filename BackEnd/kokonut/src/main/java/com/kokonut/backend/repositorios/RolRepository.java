package com.kokonut.backend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kokonut.backend.modelos.Rol;

import java.util.Optional;

@Repository("rolRepository")
public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByName(String name);
}