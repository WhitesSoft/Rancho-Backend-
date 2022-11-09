package com.darksoft.servidor.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Consumos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fecha;
    private float lectura;

    //RELACIONES
    //Relacion con medidor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medidor")
    @JsonIgnore
    private Medidor medidor;

    public Consumos() {
    }

    public Consumos(String fecha, float lectura, Medidor medidor) {
        this.fecha = fecha;
        this.lectura = lectura;
        this.medidor = medidor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getLectura() {
        return lectura;
    }

    public void setLectura(float lectura) {
        this.lectura = lectura;
    }

    public Medidor getMedidor() {
        return medidor;
    }

    public void setMedidor(Medidor medidor) {
        this.medidor = medidor;
    }

}
