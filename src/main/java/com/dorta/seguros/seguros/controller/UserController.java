package com.dorta.seguros.seguros.controller;

import com.dorta.seguros.seguros.service.AuthService;
import com.dorta.seguros.seguros.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthService userService;

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String>getUserDashboard(){
        return ResponseEntity.ok("Bienvenido al panel de usuario");

    }

    @PutMapping("/{username}/make-admin")
    public ResponseEntity<String>makeUserAdmin(@PathVariable String username){
        boolean updated = userService.makeUserAdmin(username);

        if (updated){
            return ResponseEntity.ok("Usuario " + username + " ahora es ADMIN.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario " + username + " no encontrado.");
        }

    }

}
