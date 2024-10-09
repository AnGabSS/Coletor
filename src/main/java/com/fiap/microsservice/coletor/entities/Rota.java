package com.fiap.microsservice.coletor.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "tb_rota")
public class Rota implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "distancia_km")
    private Double distancia;
    private String descricao;

    public Rota() {

    }

    public Rota(Long id, Double distancia) {
        this.id = id;
        this.distancia = distancia;
    }

    public Rota(Long id, Double distancia, String descricao) {
        this.id = id;
        this.distancia = distancia;
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rota rota = (Rota) o;
        return Objects.equals(id, rota.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
