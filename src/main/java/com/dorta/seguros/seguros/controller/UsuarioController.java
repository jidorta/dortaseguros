package com.dorta.seguros.seguros.controller;

import com.dorta.seguros.seguros.model.Usuario;
import com.dorta.seguros.seguros.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario>getAll(){
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getById(@PathVariable Long id){
        return usuarioService.findById(id);
    }

    @PostMapping
    public Usuario create(@RequestBody Usuario usuario){
        return usuarioService.save(usuario);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        usuarioService.deleteById(id);
    }

}
