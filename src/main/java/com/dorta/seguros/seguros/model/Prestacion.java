package com.dorta.seguros.seguros.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Prestacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private String tipo;

    @ManyToOne(fetch = FetchType.LAZY)  // muchas prestaciones pueden pertenecer a una póliza
    @JoinColumn(name = "poliza_id", nullable = false)
    @JsonBackReference("poliza-prestaciones") // evita ciclos infinitos al serializar
    private Poliza poliza;

    public Prestacion() {
    }

    public Prestacion( String descripcion, String tipo, Poliza poliza) {
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
