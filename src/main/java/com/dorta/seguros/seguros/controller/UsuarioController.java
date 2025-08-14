package com.dorta.seguros.seguros.controller;

import com.dorta.seguros.seguros.model.Role;
import com.dorta.seguros.seguros.model.Usuario;
import com.dorta.seguros.seguros.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    public  static class UsuarioDTO{
        public Long id;
        public String username;
        public Set<Role>roles;

        public UsuarioDTO(Usuario u) {
            this.id = u.getId();
            this.username = u.getUsername();
            this.roles = u.getRoles();
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UsuarioDTO>getAll(){
        return  usuarioService.findAll().stream()
                .map(UsuarioDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    public UsuarioDTO getById(@PathVariable Long id){
        Usuario usuario = usuarioService.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario no encontrado"));

        return new UsuarioDTO(usuario);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public UsuarioDTO create(@Valid @RequestBody Usuario usuario){

        if(usuarioService.existsByUsername(usuario.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El nombre de usuario ya existe");
        }

        if(usuario.getPassword()== null || usuario.getPassword().length()< 8){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La contraseña debe tener al menos 8 caracteres");
        }

        if (usuario.getEmail() == null || !usuario.getEmail().matches("^(.+)@(.+)$")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email inválido");
        }

        Usuario saved = usuarioService.save(usuario);
        return new UsuarioDTO(saved);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id){
        if(!usuarioService.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario no encontrado");
        }
        usuarioService.deleteById(id);

    }

}
