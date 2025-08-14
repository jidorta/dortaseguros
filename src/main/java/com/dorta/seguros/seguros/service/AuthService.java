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
import java.util.HashSet;
import java.util.Optional;
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
        String username = request.getUsername();
        String password = request.getPassword();

        if(username == null || username.trim().isEmpty()){
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío");
        }

        if(username.length() < 3 || username.length() > 20){
            throw new IllegalArgumentException("El nombre de usuario debe tener entre 3 y 20 caracteres");
        }

        if (request.getPassword() == null || request.getPassword().length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        }

        // Validar contraseña con al menos un número y una letra mayúscula
        if (!request.getPassword().matches("^(?=.*[A-Z])(?=.*\\d).+$")) {
            throw new IllegalArgumentException("La contraseña debe contener al menos una letra mayúscula y un número");
        }

        if (!username.matches("^[a-zA-Z0-9_]+$")) {
            throw new IllegalArgumentException("El nombre de usuario solo puede contener letras, números y guiones bajos");
        }

        if (usuarioRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }

        if (request.getPassword().length()<8){
            throw new RuntimeException("La contraseña debe tener al menos 8 caracteres");
        }

        if (usuarioRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("El mail ya está registrado");
        }

        Usuario user= new Usuario();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        //AQUI AÑADIMOS DE MANERA TEMPORAL PARA AGREGAR UN USUARIO ADMIN
        user.setRoles(Set.of(Role.ADMIN));
        if(request.isAdmin()){
            user.setRoles(Set.of(Role.ADMIN));
        }else{
            user.setRoles(Set.of(Role.USER));
        }

        usuarioRepository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    public AuthResponse registerAdmin(AuthRequest request){
        if(usuarioRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("Usuario ya existe");
        }

        Usuario admin = new Usuario();
        admin.setUsername(request.getUsername());
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.setRoles(Set.of(Role.ADMIN));

        usuarioRepository.save(admin);
        String token = jwtService.generateToken(admin);
        return new AuthResponse(token);
    }

    public boolean makeUserAdmin(String username){
         Optional<Usuario> optionalUser = usuarioRepository.findByUsername(username);
         if(optionalUser.isPresent()){
             Usuario user = optionalUser.get();
             Set<Role> roles = new HashSet<>(user.getRoles());
             roles.add(Role.ADMIN);
             user.setRoles(roles);
             usuarioRepository.save(user);
             return true;
         }else{
             return false;
         }
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
