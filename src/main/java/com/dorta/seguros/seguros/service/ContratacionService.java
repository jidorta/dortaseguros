package com.dorta.seguros.seguros.service;

import com.dorta.seguros.seguros.model.Contratacion;
import com.dorta.seguros.seguros.repository.ContratacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratacionService {

    private final ContratacionRepository contratacionRepository;

    public ContratacionService(ContratacionRepository contratacionRepository) {
        this.contratacionRepository = contratacionRepository;
    }

    public Contratacion save(Contratacion contratacion){
        return contratacionRepository.save(contratacion);
    }

    public List<Contratacion> findAll(){
        return contratacionRepository.findAll();
    }

    public void deleteById(Long id){
        contratacionRepository.deleteById(id);
    }

    public Contratacion findById(Long id){
        return contratacionRepository.findById(id).orElse(null);
    }



}
