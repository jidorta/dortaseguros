package com.dorta.seguros.seguros.controller;

import com.dorta.seguros.seguros.dto.AuthRequest;
import com.dorta.seguros.seguros.dto.AuthResponse;
import com.dorta.seguros.seguros.dto.ErrorResponse;
import com.dorta.seguros.seguros.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register-admin")
    public ResponseEntity<AuthResponse>registerAdmin(@RequestBody AuthRequest request){
        AuthResponse response = authService.registerAdmin(request);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse>handleRuntimeException(RuntimeException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
