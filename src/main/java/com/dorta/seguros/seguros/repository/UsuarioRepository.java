package com.dorta.seguros.seguros.repository;

import com.dorta.seguros.seguros.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.polizas WHERE u.username = :username")
    Optional<Usuario> findByUsernameWithPolizas(@Param("username") String username);
}
