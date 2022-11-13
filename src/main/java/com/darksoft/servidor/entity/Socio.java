package com.darksoft.servidor.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_socio", length = 10)
    private long idSocio;

    @Column(length = 70)
    private String nombres;

    @Column(length = 70)
    private String apellidos;

    @Column
    private String cedula;

    @Column(length = 100)
    private String correo;

    @Column(length = 30)
    private String fechaNacimiento;

    @Column(length = 100)
    private String direccion;

    @Column(length = 200)
    private String foto;

    @Column(length = 5)
    private boolean activo;

    //Relaciones
    //Relacion con medidor
    @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL)
    private List<Medidor> medidores = new ArrayList<>();

    //Relacion con solicitudes
    @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL)
    private List<Solicitudes> solicitudes = new ArrayList<>();

    public Socio() {
    }

    public Socio(String nombres, String apellidos, String cedula, String correo, String fechaNacimiento, String direccion, String foto, boolean activo) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.foto = foto;
        this.activo = activo;
    }

    public long getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(long idSocio) {
        this.idSocio = idSocio;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Medidor> getMedidores() {
        return medidores;
    }

    public void setMedidores(List<Medidor> medidores) {
        this.medidores = medidores;
    }

    public List<Solicitudes> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<Solicitudes> solicitudes) {
        this.solicitudes = solicitudes;
    }

}
