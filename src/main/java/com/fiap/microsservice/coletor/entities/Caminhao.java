package com.fiap.microsservice.coletor.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "tb_caminhao")
public class Caminhao implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String placa;
    private Double capacidade;
    private String motorista;

    public Caminhao(){

    }

    public Caminhao(Long id, String placa, Double capacidade) {
        this.id = id;
        this.placa = placa;
        this.capacidade = capacidade;
    }

    public Caminhao(Long id, String placa, Double capacidade, String motorista) {
        this.id = id;
        this.placa = placa;
        this.capacidade = capacidade;
        this.motorista = motorista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Caminhao caminhao = (Caminhao) o;
        return Objects.equals(id, caminhao.id) && Objects.equals(placa, caminhao.placa);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(placa);
        return result;
    }
}
