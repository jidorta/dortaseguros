package com.dorta.seguros.seguros.repository;

import com.dorta.seguros.seguros.model.Poliza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolizaRepository extends JpaRepository<Poliza,Long> {

    Poliza findByNumeroPoliza(String numeroPoliza);


}
