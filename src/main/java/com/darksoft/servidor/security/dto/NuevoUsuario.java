package com.darksoft.servidor.security.dto;

import com.darksoft.servidor.entity.Socio;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class NuevoUsuario {

    @NotNull
    private String usuario;
    private String password;
    private Socio socio;
    private Set<String> roles = new HashSet<>();

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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
