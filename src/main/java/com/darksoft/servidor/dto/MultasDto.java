package com.darksoft.servidor.dto;

public class MultasDto {

    private String fechaVigencia;
    private float monto;

    public MultasDto() {
    }

    public MultasDto(String fechaVigencia, float monto) {
        this.fechaVigencia = fechaVigencia;
        this.monto = monto;
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

}
