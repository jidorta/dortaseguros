package com.dorta.seguros.seguros.repository;

import com.dorta.seguros.seguros.model.Prestacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestacionRepository extends JpaRepository<Prestacion,Long> {
}
