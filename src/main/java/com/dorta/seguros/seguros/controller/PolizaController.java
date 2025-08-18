package com.dorta.seguros.seguros.controller;


import com.dorta.seguros.seguros.model.Poliza;
import com.dorta.seguros.seguros.model.Usuario;
import com.dorta.seguros.seguros.repository.PolizaRepository;
import com.dorta.seguros.seguros.repository.UsuarioRepository;
import com.dorta.seguros.seguros.service.PolizaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polizas")
public class PolizaController {

    private final PolizaService polizaService;
    private final UsuarioRepository usuarioRepository;
    private final PolizaRepository polizaRepository;


    public PolizaController(PolizaService polizaService, UsuarioRepository usuarioRepository, PolizaRepository polizaRepository) {
        this.polizaService = polizaService;
        this.usuarioRepository = usuarioRepository;
        this.polizaRepository = polizaRepository;
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

        if(poliza.getUsuario() != null && poliza.getUsuario().getId() != null){
            Usuario usuario = usuarioRepository.findById(poliza.getUsuario().getId())
                    .orElseThrow(()-> new RuntimeException("Usuario no encontrado con id" + poliza.getUsuario().getId()));
            poliza.setUsuario(usuario);
        }
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

    @GetMapping("/numero/{numeroPoliza}")
    public Poliza getPolizaByNumero(@PathVariable String numeroPoliza){
        return polizaService.findByNumeroPoliza(numeroPoliza);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Poliza>update(@PathVariable Long id, @RequestBody Poliza polizaDetails){
        return polizaRepository.findById(id)
                .map(poliza ->{
                    poliza.setNumeroPoliza(polizaDetails.getNumeroPoliza());
                    poliza.setFechaInicio(polizaDetails.getFechaInicio());
                    poliza.setFechaFin(polizaDetails.getFechaFin());
                    poliza.setUsuario(polizaDetails.getUsuario());
                    poliza.setPrestaciones(polizaDetails.getPrestaciones());
                    Poliza updatedPoliza = polizaRepository.save(poliza);
                    return ResponseEntity.ok(updatedPoliza);

                })
                .orElse(ResponseEntity.notFound().build());
    }

}
