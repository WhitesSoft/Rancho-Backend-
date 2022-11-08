package com.darksoft.servidor.repository;

import com.darksoft.servidor.entity.Comunicados;
import com.darksoft.servidor.entity.Solicitudes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ComunicadosRepository extends JpaRepository<Comunicados, Long> {

    //Listar solicitudes bajo el id del usuario
    List<Comunicados> findByUsuario_Id (long idUsuario);

    //Borrar todos los comunicados del usuario
    @Transactional
    void deleteByUsuario_Id(long idUsuario);

}
