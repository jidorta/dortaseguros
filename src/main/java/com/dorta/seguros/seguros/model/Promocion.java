package com.dorta.seguros.seguros.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Promocion {
    @Id
    @GeneratedValue
    private Long id;

    private String codigo;
    private String descripcion;
    private double descuento;

    public Promocion() {
    }

    public Promocion(Long id, String codigo, String descripcion, double descuento) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.descuento = descuento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
}
