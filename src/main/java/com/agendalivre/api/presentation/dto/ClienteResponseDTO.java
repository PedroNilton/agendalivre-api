package com.agendalivre.api.presentation.dto;

import com.agendalivre.api.domain.entity.Cliente;
import java.util.UUID;

public record ClienteResponseDTO(UUID id, String nome, String email, String telefone) {
    public static ClienteResponseDTO fromEntity(Cliente c) {
        return new ClienteResponseDTO(c.getId(), c.getNome(), c.getEmail(), c.getTelefone());
    }
}
