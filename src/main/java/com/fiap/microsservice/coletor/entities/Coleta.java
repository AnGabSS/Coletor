package com.fiap.microsservice.coletor.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "tb_coleta")
public class Coleta implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date hora;

    @ManyToOne
    private Caminhao caminhao;

    @ManyToOne
    private Rota rota;

    public Coleta() {

    }

    public Coleta(Long id, Date hora, Caminhao caminhao, Rota rota) {
        this.id = id;
        this.hora = hora;
        this.caminhao = caminhao;
        this.rota = rota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coleta coleta = (Coleta) o;
        return Objects.equals(id, coleta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
