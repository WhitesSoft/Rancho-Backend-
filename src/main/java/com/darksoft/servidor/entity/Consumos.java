package com.darksoft.servidor.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Consumos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fecha;
    private float lectura;

    //RELACIONES
    //Relacion con medidor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medidor")
    @JsonIgnore
    private Medidor medidor;

    //Relaciones con factura
    //Este es el dueno de la tabla con factura
    @OneToOne(cascade = {CascadeType.ALL}) //cascade hace que cree los datos de la tabla socio si no existe
    @JoinColumn(name = "id_factura")
    private Factura factura;

    public Consumos() {
    }

    public Consumos(String fecha, float lectura, Medidor medidor, Factura factura) {
        this.fecha = fecha;
        this.lectura = lectura;
        this.medidor = medidor;
        this.factura = factura;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
