package com.darksoft.servidor.dto;

import com.darksoft.servidor.entity.Medidor;

public class ConsumosDto {

    private String fecha;
    private float lectura;
    private Medidor medidor;

    public ConsumosDto(String fecha, float lectura, Medidor medidor) {
        this.fecha = fecha;
        this.lectura = lectura;
        this.medidor = medidor;
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

}
