package com.darksoft.servidor.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Reclamos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String detalle;
    private String fecha;
    private String fechaAtencion;
    private boolean atendido;

    //Relacion con socio
    @ManyToOne
    @JoinColumn(name = "id_socio")
    @JsonIgnore
    private Socio socio;

    public Reclamos() {}

    public Reclamos(String detalle, String fecha, String fechaAtencion, boolean atendido, Socio socio) {
        this.detalle = detalle;
        this.fecha = fecha;
        this.fechaAtencion = fechaAtencion;
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

    public String getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(String fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
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
