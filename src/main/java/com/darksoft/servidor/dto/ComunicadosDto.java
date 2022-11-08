package com.darksoft.servidor.dto;

import com.darksoft.servidor.security.entity.Usuario;

public class ComunicadosDto {

    private String descripcion;
    private String fechaInicio;
    private int vigencia;
    private Usuario usuario;

    public ComunicadosDto(){}

    public ComunicadosDto(String descripcion, String fechaInicio, int vigencia, Usuario usuario) {
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.vigencia = vigencia;
        this.usuario = usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getVigencia() {
        return vigencia;
    }

    public void setVigencia(int vigencia) {
        this.vigencia = vigencia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
