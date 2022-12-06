package com.darksoft.servidor.dto;

import com.darksoft.servidor.entity.Socio;

public class MultasDto {

    private String fechaVigencia;
    private float monto;
    private Socio socio;

    public MultasDto() {
    }

    public MultasDto(String fechaVigencia, float monto, Socio socio) {
        this.fechaVigencia = fechaVigencia;
        this.monto = monto;
        this.socio = socio;
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
