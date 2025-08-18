package com.dorta.seguros.seguros.controller;

import com.dorta.seguros.seguros.model.Prestacion;
import com.dorta.seguros.seguros.service.PrestacionService;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/{id}")
    public Prestacion update(@PathVariable Long id, @RequestBody Prestacion prestacion){

        Prestacion existing = prestacionService.findById(id);
        if (existing == null){
            throw new RuntimeException("Prestacion no encontrada");
        }
        return existing;


    }

    @PostMapping("/poliza/{polizaId}")
    public ResponseEntity<Prestacion>createPrestacion(
            @PathVariable Long polizaId,
            @RequestBody Prestacion prestacion) {

        Prestacion nueva = prestacionService.addPrestacionToPoliza(polizaId, prestacion);
        return ResponseEntity.ok(nueva);
    }

}
