package com.darksoft.servidor.entity;

import javax.persistence.*;

@Entity
public class Cobros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fechaHora;
    private float monto;

    //Relaciones
    //Relacion con Factura
    //Este es el dueno de la tabla con factura
    @OneToOne
    @JoinColumn(name = "id_factura")
    private Factura factura;

    public Cobros() {
    }

    public Cobros(String fechaHora, float monto, Factura factura) {
        this.fechaHora = fechaHora;
        this.monto = monto;
        this.factura = factura;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
