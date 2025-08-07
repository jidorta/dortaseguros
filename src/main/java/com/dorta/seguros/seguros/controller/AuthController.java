package com.dorta.seguros.seguros.controller;

import com.dorta.seguros.seguros.dto.AuthRequest;
import com.dorta.seguros.seguros.dto.AuthResponse;
import com.dorta.seguros.seguros.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse>login(@RequestBody AuthRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse>register(@RequestBody AuthRequest request){
        return ResponseEntity.ok(authService.register(request));
    }
}
