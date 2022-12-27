package com.darksoft.servidor.repository;

import com.darksoft.servidor.entity.Reclamos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReclamosRepository extends JpaRepository<Reclamos, Long> {

    //Listar reclamos bajo el id del socio
    List<Reclamos> findBySocio_IdSocio(long idSocio);

    //Borrar todos los reclamos del socio
    @Transactional
    void deleteBySocio_IdSocio(long idSocio);

}
