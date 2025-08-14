package com.dorta.seguros.seguros.controller;


import com.dorta.seguros.seguros.model.Poliza;
import com.dorta.seguros.seguros.service.PolizaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return polizaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poliza> getById(@PathVariable Long id){
        return polizaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(()-> new RuntimeException("Poliza no encontrada con id " + id));
    }

    @PostMapping
    public ResponseEntity<Poliza> create(@RequestBody Poliza poliza){
        Poliza savedPoliza = polizaService.save(poliza);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPoliza);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if (!polizaService.existsById(id)){
            throw new RuntimeException("Poliza no encontrada con id " + id );
        }
        polizaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
