package com.darksoft.servidor.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Solicitudes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String detalle;
    private String fecha;
    private boolean atendido;

    //Relacion con socio
    @ManyToOne
    @JoinColumn(name = "id_socio")
    @JsonIgnore
    private Socio socio;

    public Solicitudes(){}

    public Solicitudes(String detalle, String fecha, boolean atendido, Socio socio) {
        this.detalle = detalle;
        this.fecha = fecha;
        this.atendido = atendido;
        this.socio = socio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isAtendido() {
        return atendido;
    }

    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

}
