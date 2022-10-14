package com.darksoft.servidor.security.entity;

import com.darksoft.servidor.entity.Socio;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String usuario;

    private String password;

    //Relaciones
    //Este es el dueno de la tabla con socio
    @OneToOne(cascade = {CascadeType.ALL}) //cascade hace que cree los datos de la tabla socio si no existe
    @JoinColumn(name = "id_socio")
    private Socio socio;

    //Relacion con roles
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();

    public Usuario() {
    }

    public Usuario(String usuario, String password, Socio socio) {
        this.usuario = usuario;
        this.password = password;
        this.socio = socio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}
