package com.dorta.seguros.seguros.model;

import jakarta.persistence.*;

@Entity
public class Prestacion {

    @Id
    @GeneratedValue
    private Long id;

    private String descripcion;
    private String tipo;

    @ManyToOne
    @JoinColumn(name= "poliza_id")
    private Poliza poliza;

    public Prestacion() {
    }

    public Prestacion(Long id, String descripcion, String tipo, Poliza poliza) {
        this.id = id;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.poliza = poliza;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Poliza getPoliza() {
        return poliza;
    }

    public void setPoliza(Poliza poliza) {
        this.poliza = poliza;
    }
}
