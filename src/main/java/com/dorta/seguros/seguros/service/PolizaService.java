package com.dorta.seguros.seguros.service;

import com.dorta.seguros.seguros.model.Poliza;
import com.dorta.seguros.seguros.repository.PolizaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolizaService {

    private final PolizaRepository polizaRepository;

    public PolizaService(PolizaRepository polizaRepository) {
        this.polizaRepository = polizaRepository;
    }

    public List<Poliza> findAll(){
        return polizaRepository.findAll();
    }

    public Poliza save(Poliza poliza){
        return polizaRepository.save(poliza);
    }

    public void deleteById(Long id){
        polizaRepository.deleteById(id);
    }

    public Optional<Poliza> findById(Long id){
        return polizaRepository.findById(id);
    }

    public Poliza findByNumeroPoliza(String numeroPoliza){
        return polizaRepository.findByNumeroPoliza(numeroPoliza);
    }

    public boolean existsById(Long id){
        return polizaRepository.existsById(id);
    }

}
