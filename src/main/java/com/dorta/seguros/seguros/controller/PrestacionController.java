package com.dorta.seguros.seguros.controller;

import com.dorta.seguros.seguros.model.Prestacion;
import com.dorta.seguros.seguros.service.PrestacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestaciones")
public class PrestacionController {

    private final PrestacionService prestacionService;

    public PrestacionController(PrestacionService prestacionService) {
        this.prestacionService = prestacionService;
    }

    @GetMapping
    public List<Prestacion> getAll(){
        return prestacionService.findAll();
    }

    @GetMapping("/{id}")
    public Prestacion getById(@PathVariable Long id){
        return prestacionService.findById(id);
    }

    @PostMapping
    public Prestacion create(@RequestBody Prestacion prestacion){
        return prestacionService.save(prestacion);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        prestacionService.deleteById(id);
    }

}
