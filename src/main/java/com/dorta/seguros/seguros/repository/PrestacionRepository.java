package com.dorta.seguros.seguros.repository;

import com.dorta.seguros.seguros.model.Prestacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrestacionRepository extends JpaRepository<Prestacion,Long> {

     Optional<Prestacion>findById(Long id);

}
