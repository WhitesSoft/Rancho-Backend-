package com.darksoft.servidor.repository;

import com.darksoft.servidor.entity.Multas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultasRepository extends JpaRepository<Multas, Long> {
}
