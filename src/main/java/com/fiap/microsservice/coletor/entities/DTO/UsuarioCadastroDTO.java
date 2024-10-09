package com.fiap.microsservice.coletor.entities.DTO;

public record UsuarioCadastroDTO(
        Long usuarioId,
        String nome,
        String email,
        String senha
) {
}
