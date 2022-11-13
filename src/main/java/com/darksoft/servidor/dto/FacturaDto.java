package com.darksoft.servidor.dto;

public class FacturaDto {

    private String razonSocial;
    private String nit;
    private String periodo;
    private float monto;
    private boolean estado;

    public FacturaDto() {
    }

    public FacturaDto(String razonSocial, String nit, String periodo, float monto, boolean estado) {
        this.razonSocial = razonSocial;
        this.nit = nit;
        this.periodo = periodo;
        this.monto = monto;
        this.estado = estado;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
