package com.darksoft.servidor.dto;

import com.darksoft.servidor.entity.Socio;

public class ReclamosDto {

    private String detalle;
    private String fecha;
    private String fechaAtencion;
    private boolean atendido;
    private Socio socio;

    public ReclamosDto() {}

    public ReclamosDto(String detalle, String fecha, String fechaAtencion, boolean atendido, Socio socio) {
        this.detalle = detalle;
        this.fecha = fecha;
        this.fechaAtencion = fechaAtencion;
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
