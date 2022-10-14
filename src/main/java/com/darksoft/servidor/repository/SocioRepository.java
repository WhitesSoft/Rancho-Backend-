package com.darksoft.servidor.repository;

import com.darksoft.servidor.entity.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Long> {

    //Metodo para buscar socio por nombre
    //Optional<Socio> findByNombres(String nombres);

    //Metodo para ver si existe el socio
    //boolean existsByNombres(String nombres);
}
