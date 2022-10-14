package com.darksoft.servidor.security.service;

import com.darksoft.servidor.entity.Socio;
import com.darksoft.servidor.security.entity.Usuario;
import com.darksoft.servidor.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    //Listar todos los USUARIOS
    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    //Obtener un usuario especifico por su id
    public Optional<Usuario> getUsuario(long id){
        return usuarioRepository.findById(id);
    }

    //Borrar usuario
    public void delete(long id){
        usuarioRepository.deleteById(id);
    }

    //Obtener un usuario especifico
    public Optional<Usuario> getByUsuario(String id){
        return usuarioRepository.findByUsuario(id);
    }

    //Guardar usuario
    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    //Existe usuario?
    public boolean existsByUsuario(String usuario){
        return usuarioRepository.existsByUsuario(usuario);
    }

    //Existe socio por id?
    public boolean existsById(long id){
        return usuarioRepository.existsById(id);
    }


}
