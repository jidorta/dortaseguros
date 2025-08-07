package com.dorta.seguros.seguros.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Contratacion {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate fechaContratacion;

    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="poliza_id")
    private Poliza poliza;

    public Contratacion() {
    }

    public Contratacion(Long id, LocalDate fechaContratacion, Usuario usuario, Poliza poliza) {
        this.id = id;
        this.fechaContratacion = fechaContratacion;
        this.usuario = usuario;
        this.poliza = poliza;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Poliza getPoliza() {
        return poliza;
    }

    public void setPoliza(Poliza poliza) {
        this.poliza = poliza;
    }
}
