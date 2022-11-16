package com.darksoft.servidor.dto;

import com.darksoft.servidor.entity.Factura;

public class CobrosDto {

    private String fechaHora;
    private float monto;
    private Factura factura;

    public CobrosDto() {
    }

    public CobrosDto(String fechaHora, float monto, Factura factura) {
        this.fechaHora = fechaHora;
        this.monto = monto;
        this.factura = factura;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

}
