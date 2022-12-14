package com.darksoft.servidor.repository;

import com.darksoft.servidor.entity.Medidor;
import com.darksoft.servidor.entity.Multas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MultasRepository extends JpaRepository<Multas, Long> {

    //Listar multas bajo el id del socio
    List<Multas> findBySocio_IdSocio(long idSocio);

    //Borrar todas las multas del socio
    @Transactional
    void deleteBySocio_IdSocio(long idSocio);

}
