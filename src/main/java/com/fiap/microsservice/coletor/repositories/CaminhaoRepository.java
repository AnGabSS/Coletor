package com.fiap.microsservice.coletor.repositories;

import com.fiap.microsservice.coletor.entities.Caminhao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaminhaoRepository extends JpaRepository<Caminhao, Long> {
}
