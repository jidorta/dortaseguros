package com.dorta.seguros.seguros.model;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Builder
public class Usuario {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String username;
    private String email;
    private String password;
    private LocalDate fechaRegistro;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(mappedBy = "usuario")
    private List<Poliza> polizas;

    public Usuario() {
    }

    public Usuario(Long id, String username, String email, String password, LocalDate fechaRegistro, Set<Role> roles, List<Poliza> polizas) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.fechaRegistro = fechaRegistro;
        this.roles = roles;
        this.polizas = polizas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<Poliza> getPolizas() {
        return polizas;
    }

    public void setPolizas(List<Poliza> polizas) {
        this.polizas = polizas;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
