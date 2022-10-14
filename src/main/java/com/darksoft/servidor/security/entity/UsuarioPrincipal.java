package com.darksoft.servidor.security.entity;

import com.darksoft.servidor.entity.Socio;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioPrincipal implements UserDetails { //implementa los roles que tendra cada usuario

    private String usuario;
    private String password;
    private Socio socio;
    private Collection<? extends GrantedAuthority> authorities;

    public UsuarioPrincipal(String usuario, String password, Socio socio, Collection<? extends GrantedAuthority> authorities) {
        this.usuario = usuario;
        this.password = password;
        this.socio = socio;
        this.authorities = authorities;
    }

    //asigna rol a cada usuario
    public static UsuarioPrincipal build(Usuario usuario){
        //Obtenemos los roles
        List<GrantedAuthority> authorities = usuario.getRoles().stream().map( rol -> new SimpleGrantedAuthority(
                rol.getRolNombre().name())).collect(Collectors.toList());

        return new UsuarioPrincipal(usuario.getUsuario(), usuario.getPassword(), usuario.getSocio(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    public Socio getSocio() {
//        return socio;
//    }
}
