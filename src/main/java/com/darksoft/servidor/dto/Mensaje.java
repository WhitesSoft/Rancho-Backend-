package com.darksoft.servidor.dto;

public class Mensaje {

    //Mostrar mensajes por pantalla en el lado del cliente
    private String mensaje;

    public Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}