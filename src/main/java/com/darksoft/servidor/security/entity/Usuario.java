package com.darksoft.servidor.security.entity;

import com.darksoft.servidor.entity.Comunicados;
import com.darksoft.servidor.entity.Socio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String usuario;
    private String password;
    private boolean estadoPassword;

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

    //Relacion con Comunicados
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Comunicados> comunicados = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String usuario, String password, boolean estadoPassword, Socio socio) {
        this.usuario = usuario;
        this.password = password;
        this.estadoPassword = estadoPassword;
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

    public boolean isEstadoPassword() {
        return estadoPassword;
    }

    public void setEstadoPassword(boolean estadoPassword) {
        this.estadoPassword = estadoPassword;
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

    public List<Comunicados> getComunicados() {
        return comunicados;
    }

    public void setComunicados(List<Comunicados> comunicados) {
        this.comunicados = comunicados;
    }
}
