package com.darksoft.servidor.repository;

import com.darksoft.servidor.entity.Consumos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ConsumosRepository extends JpaRepository<Consumos, Long> {

    //Listar consumos bajo el id del medidor
    List<Consumos> findByMedidor_Id(long id);

    //Borrar todos los consumos del medidor
    @Transactional
    void deleteByMedidor_Id(long id);

}
