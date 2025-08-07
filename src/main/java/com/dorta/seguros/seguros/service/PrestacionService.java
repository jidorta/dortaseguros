package com.dorta.seguros.seguros.service;

import com.dorta.seguros.seguros.model.Prestacion;
import com.dorta.seguros.seguros.repository.PrestacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestacionService {

    private final PrestacionRepository prestacionRepository;

    public PrestacionService(PrestacionRepository prestacionRepository) {
        this.prestacionRepository = prestacionRepository;
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

}
