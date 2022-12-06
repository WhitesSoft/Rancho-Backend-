package com.darksoft.servidor.dto;

import com.darksoft.servidor.entity.Socio;

public class ReclamosDto extends SolicitudesDto {

    private String fechaAtencion;
    private String resultado;

    public ReclamosDto(String detalle, String fecha, boolean atendido, Socio socio, String fechaAtencion, String resultado) {
        super(detalle, fecha, atendido, socio);
        this.fechaAtencion = fechaAtencion;
        this.resultado = resultado;
    }

    public String getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(String fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

}
