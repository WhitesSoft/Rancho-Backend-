package com.darksoft.servidor.dto;

import com.darksoft.servidor.entity.Factura;
import com.darksoft.servidor.entity.Medidor;

public class ConsumosDto {

    private String fecha;
    private float lectura;
    private Medidor medidor;
    private Factura factura;

    public ConsumosDto(String fecha, float lectura, Medidor medidor, Factura factura) {
        this.fecha = fecha;
        this.lectura = lectura;
        this.medidor = medidor;
        this.factura = factura;
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

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

}
