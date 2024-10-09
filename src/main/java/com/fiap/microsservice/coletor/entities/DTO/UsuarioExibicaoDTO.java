package com.fiap.microsservice.coletor.entities.DTO;


import com.fiap.microsservice.coletor.entities.User;

public record UsuarioExibicaoDTO(
        Long usuarioId,
        String nome,
        String email) {

    public UsuarioExibicaoDTO(User usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail());
    }
}
