package com.fiap.microsservice.coletor.repositories;

import com.fiap.microsservice.coletor.entities.Coleta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColetaRepository extends JpaRepository<Coleta, Long> {
}
