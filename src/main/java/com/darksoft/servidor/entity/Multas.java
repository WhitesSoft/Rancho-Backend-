package com.darksoft.servidor.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Multas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fechaVigencia;
    private float monto;

    //Relaciones
    //Relacion con socio
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_socio")
    @JsonIgnore
    private Socio socio;

    public Multas(){}

    public Multas(String fechaVigencia, float monto, Socio socio) {
        this.fechaVigencia = fechaVigencia;
        this.monto = monto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(String fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

}
