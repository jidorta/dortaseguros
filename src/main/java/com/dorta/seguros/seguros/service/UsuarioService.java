package com.dorta.seguros.seguros.service;

import com.dorta.seguros.seguros.model.Usuario;
import com.dorta.seguros.seguros.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario>findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario save (Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id){
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> findById(Long id){
        return usuarioRepository.findById(id);
    }

    public boolean existsById(Long id){
        return usuarioRepository.existsById(id);
    }

    public boolean existsByUsername(String username){
        return usuarioRepository.existsByUsername(username);
    }
}
