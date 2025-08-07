package com.dorta.seguros.seguros.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Poliza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroPoliza;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "poliza")
    private List<Prestacion> prestaciones;

    public Poliza() {
    }

    public Poliza(Long id, String numeroPoliza, LocalDate fechaInicio, LocalDate fechaFin, Usuario usuario, List<Prestacion> prestaciones) {
        this.id = id;
        this.numeroPoliza = numeroPoliza;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.usuario = usuario;
        this.prestaciones = prestaciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Prestacion> getPrestaciones() {
        return prestaciones;
    }

    public void setPrestaciones(List<Prestacion> prestaciones) {
        this.prestaciones = prestaciones;
    }
}
