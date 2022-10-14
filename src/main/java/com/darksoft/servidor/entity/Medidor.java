package com.darksoft.servidor.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Medidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String serial;
    private String marca;
    private float registroInicio;
    private String fechaInstalacion;

    //Relacion con socio
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_socio")
    @JsonIgnore
    private Socio socio;

    public Medidor() {
    }

    public Medidor(String serial, String marca, float registroInicio, String fechaInstalacion, Socio socio) {
        this.serial = serial;
        this.marca = marca;
        this.registroInicio = registroInicio;
        this.fechaInstalacion = fechaInstalacion;
        this.socio = socio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getRegistroInicio() {
        return registroInicio;
    }

    public void setRegistroInicio(float registroInicio) {
        this.registroInicio = registroInicio;
    }

    public String getFechaInstalacion() {
        return fechaInstalacion;
    }

    public void setFechaInstalacion(String fechaInstalacion) {
        this.fechaInstalacion = fechaInstalacion;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }
}
