package com.fiap.microsservice.coletor.repositories;

import com.fiap.microsservice.coletor.entities.Morador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoradorRepository extends JpaRepository<Morador, Long> {
}
