package com.darksoft.servidor.security.repository;

import com.darksoft.servidor.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //obtener usuario
    Optional<Usuario> findByUsuario(String usuario);

    //Existe usuario?
    boolean existsByUsuario(String usuario);

}
