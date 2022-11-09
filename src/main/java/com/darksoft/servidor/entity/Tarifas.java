package com.darksoft.servidor.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tarifas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fechaInicio;
    private int consumoMaximo;
    private float costoUnitario;

    public Tarifas() {
    }

    public Tarifas(String fechaInicio, int consumoMaximo, float costoUnitario) {
        this.fechaInicio = fechaInicio;
        this.consumoMaximo = consumoMaximo;
        this.costoUnitario = costoUnitario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getConsumoMaximo() {
        return consumoMaximo;
    }

    public void setConsumoMaximo(int consumoMaximo) {
        this.consumoMaximo = consumoMaximo;
    }

    public float getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(float costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

}
