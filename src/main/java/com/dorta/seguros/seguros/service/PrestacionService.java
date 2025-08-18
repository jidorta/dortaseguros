package com.dorta.seguros.seguros.service;

import com.dorta.seguros.seguros.model.Poliza;
import com.dorta.seguros.seguros.model.Prestacion;
import com.dorta.seguros.seguros.repository.PolizaRepository;
import com.dorta.seguros.seguros.repository.PrestacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestacionService {

    private final PrestacionRepository prestacionRepository;
    private final PolizaRepository polizaRepository;

    public PrestacionService(PrestacionRepository prestacionRepository, PolizaRepository polizaRepository) {
        this.prestacionRepository = prestacionRepository;
        this.polizaRepository = polizaRepository;
    }

    public List<Prestacion>findAll(){
        return prestacionRepository.findAll();
    }

    public Prestacion save(Prestacion prestacion){
        return prestacionRepository.save(prestacion);
    }

    public void deleteById(Long id){
        prestacionRepository.deleteById(id);
    }

    public Prestacion findById(Long id){
        return  prestacionRepository.findById(id).orElse(null);
    }

    public Prestacion addPrestacionToPoliza(Long polizaId, Prestacion prestacion){

        Poliza poliza = polizaRepository.findById(polizaId)
                .orElseThrow(()-> new RuntimeException("Poliza no encontrada"));

        prestacion.setPoliza(poliza);
        poliza.getPrestaciones().add(prestacion);

        return prestacionRepository.save(prestacion);
    }

}
