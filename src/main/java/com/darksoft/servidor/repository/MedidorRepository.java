package com.darksoft.servidor.repository;

import com.darksoft.servidor.entity.Medidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MedidorRepository extends JpaRepository<Medidor, Long> {

    //Listar medidores bajo el id del socio
    List<Medidor> findBySocio_IdSocio(long idSocio);

    //Borrar todos los medidores del socio
    @Transactional
    void deleteBySocio_IdSocio(long idSocio);

}

