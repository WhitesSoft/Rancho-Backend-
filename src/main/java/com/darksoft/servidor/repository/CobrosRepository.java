package com.darksoft.servidor.repository;

import com.darksoft.servidor.entity.Cobros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CobrosRepository extends JpaRepository<Cobros, Long> {
}
