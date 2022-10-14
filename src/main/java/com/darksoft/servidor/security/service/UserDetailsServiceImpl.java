package com.darksoft.servidor.security.service;

import com.darksoft.servidor.security.entity.Usuario;
import com.darksoft.servidor.security.entity.UsuarioPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Obtenemos el usuario de la clase principal
        Usuario usuario = usuarioService.getByUsuario(username).get();
        return UsuarioPrincipal.build(usuario);
    }
}
