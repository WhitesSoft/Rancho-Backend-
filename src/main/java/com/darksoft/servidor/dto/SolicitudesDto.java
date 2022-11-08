package com.darksoft.servidor.dto;

import com.darksoft.servidor.entity.Socio;

public class SolicitudesDto {

    private String detalle;
    private String fecha;
    private boolean atendido;
    private Socio socio;

    public SolicitudesDto(){}

    public SolicitudesDto(String detalle, String fecha, boolean atendido, Socio socio) {
        this.detalle = detalle;
        this.fecha = fecha;
        this.atendido = atendido;
        this.socio = socio;
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
