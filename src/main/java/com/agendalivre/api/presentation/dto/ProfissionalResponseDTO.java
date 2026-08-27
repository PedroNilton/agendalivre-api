package com.agendalivre.api.presentation.dto;

import com.agendalivre.api.domain.entity.Profissional;
import java.util.UUID;

public record ProfissionalResponseDTO(UUID id, String nome, String email, String telefone) {
    public static ProfissionalResponseDTO fromEntity(Profissional p) {
        return new ProfissionalResponseDTO(p.getId(), p.getNome(), p.getEmail(), p.getTelefone());
    }
}
