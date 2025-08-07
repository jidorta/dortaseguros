package com.dorta.seguros.seguros.controller;


import com.dorta.seguros.seguros.model.Poliza;
import com.dorta.seguros.seguros.service.PolizaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polizas")
public class PolizaController {

    private final PolizaService polizaService;


    public PolizaController(PolizaService polizaService) {
        this.polizaService = polizaService;
    }

    @GetMapping
    public List<Poliza> getAll(){
        return polizaService.findByAll();
    }

    @GetMapping("/{id}")
    public Poliza getById(@PathVariable Long id){
        return polizaService.findById(id);
    }

    @PostMapping
    public Poliza create(@RequestBody Poliza poliza){
        return polizaService.save(poliza);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        polizaService.deleteById(id);
    }

}
