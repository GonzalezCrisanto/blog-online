package com.blog.tpfinal.repository;

import com.blog.tpfinal.enums.ERol;
import com.blog.tpfinal.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombre(ERol nombre);
}
