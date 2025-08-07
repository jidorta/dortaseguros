package com.dorta.seguros.seguros.service;

import com.dorta.seguros.seguros.dto.AuthRequest;
import com.dorta.seguros.seguros.dto.AuthResponse;
import com.dorta.seguros.seguros.model.Role;
import com.dorta.seguros.seguros.model.Usuario;
import com.dorta.seguros.seguros.repository.UsuarioRepository;
import com.dorta.seguros.seguros.security.JwtService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(AuthRequest request){


        Usuario user= new Usuario();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(Role.USER));
        usuarioRepository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    public AuthResponse login(AuthRequest request) {
        Usuario user = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}
