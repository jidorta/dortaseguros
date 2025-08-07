package com.dorta.seguros.seguros.service;

import com.dorta.seguros.seguros.model.Promocion;
import com.dorta.seguros.seguros.repository.PromocionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromocionService {

    private final PromocionRepository promocionRepository;

    public PromocionService(PromocionRepository promocionRepository) {
        this.promocionRepository = promocionRepository;
    }

    public List<Promocion>findAll(){
       return this.promocionRepository.findAll();
    }

    public Promocion save(Promocion promocion){
        return promocionRepository.save(promocion);
    }

    public void deleteById(Long id){
        promocionRepository.deleteById(id);
    }

    public Promocion findById(Long id){
        return promocionRepository.findById(id).orElse(null);
    }

}
