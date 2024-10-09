package com.fiap.microsservice.coletor.entities.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
public class ColetaDTO {

    private Date hora;
    private Long caminhao_id;
    private Long rota_id;

    public ColetaDTO(Date hora, Long caminhao_id, Long rota_id) {
        this.hora = hora;
        this.caminhao_id = caminhao_id;
        this.rota_id = rota_id;
    }


}
