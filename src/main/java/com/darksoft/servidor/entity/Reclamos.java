package com.darksoft.servidor.entity;

import javax.persistence.*;

@Entity
public class Reclamos extends Solicitudes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fechaAtencion;
    private String resultado;

    public Reclamos(String detalle, String fecha, boolean atendido, Socio socio, String fechaAtencion, String resultado) {
        super(detalle, fecha, atendido, socio);
        this.fechaAtencion = fechaAtencion;
        this.resultado = resultado;
    }

    public Reclamos() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(String fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

}
