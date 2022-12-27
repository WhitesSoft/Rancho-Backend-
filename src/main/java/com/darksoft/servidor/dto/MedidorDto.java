package com.darksoft.servidor.dto;

import com.darksoft.servidor.entity.Socio;

public class MedidorDto {

    private String serial;
    private String marca;
    private float registroInicio;
    private String fechaInstalacion;
    private Socio socio;

    public MedidorDto(){

    }

    public MedidorDto(String serial, String marca, float registroInicio, String fechaInstalacion, Socio socio) {
        this.serial = serial;
        this.marca = marca;
        this.registroInicio = registroInicio;
        this.fechaInstalacion = fechaInstalacion;
        this.socio = socio;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getRegistroInicio() {
        return registroInicio;
    }

    public void setRegistroInicio(float registroInicio) {
        this.registroInicio = registroInicio;
    }

    public String getFechaInstalacion() {
        return fechaInstalacion;
    }

    public void setFechaInstalacion(String fechaInstalacion) {
        this.fechaInstalacion = fechaInstalacion;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

}
