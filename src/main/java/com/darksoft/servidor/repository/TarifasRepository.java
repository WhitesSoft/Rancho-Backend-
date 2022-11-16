package com.darksoft.servidor.repository;

import com.darksoft.servidor.entity.Tarifas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifasRepository extends JpaRepository<Tarifas, Long> {
}
