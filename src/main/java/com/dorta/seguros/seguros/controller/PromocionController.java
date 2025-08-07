package com.dorta.seguros.seguros.controller;

import com.dorta.seguros.seguros.model.Promocion;
import com.dorta.seguros.seguros.service.PromocionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promociones")
public class PromocionController {

    private final PromocionService promocionService;

    public PromocionController(PromocionService promocionService) {
        this.promocionService = promocionService;
    }

    @GetMapping
    public List<Promocion> getAll(){
        return promocionService.findAll();
    }

    @GetMapping("/{id}")
    public Promocion getById(@PathVariable Long id){
        return promocionService.findById(id);
    }

    @PostMapping
    public Promocion create (@RequestBody Promocion promocion){
        return promocionService.save(promocion);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        promocionService.deleteById(id);
    }



}
