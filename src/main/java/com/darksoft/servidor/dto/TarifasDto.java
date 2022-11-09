package com.darksoft.servidor.dto;

public class TarifasDto {

    private String fechaInicio;
    private int consumoMaximo;
    private float costoUnitario;

    public TarifasDto() {
    }

    public TarifasDto(String fechaInicio, int consumoMaximo, float costoUnitario) {
        this.fechaInicio = fechaInicio;
        this.consumoMaximo = consumoMaximo;
        this.costoUnitario = costoUnitario;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getConsumoMaximo() {
        return consumoMaximo;
    }

    public void setConsumoMaximo(int consumoMaximo) {
        this.consumoMaximo = consumoMaximo;
    }

    public float getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(float costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

}
