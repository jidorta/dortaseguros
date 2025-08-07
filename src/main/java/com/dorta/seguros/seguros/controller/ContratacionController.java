package com.dorta.seguros.seguros.controller;

import com.dorta.seguros.seguros.model.Contratacion;
import com.dorta.seguros.seguros.service.ContratacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrataciones")
public class ContratacionController {

    private final ContratacionService contratacionService;

    public ContratacionController(ContratacionService contratacionService) {
        this.contratacionService = contratacionService;
    }

    @GetMapping
    public List<Contratacion>getAll(){
        return contratacionService.findAll();
    }

    @GetMapping("/{id}")
    public Contratacion getById(@PathVariable Long id){
        return contratacionService.findById(id);
    }

    @PostMapping
    public Contratacion create (@RequestBody Contratacion contratacion){
        return contratacionService.save(contratacion);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        contratacionService.deleteById(id);
    }

}
