package com.agendalivre.api.presentation.dto;

import com.agendalivre.api.domain.entity.Servico;
import java.math.BigDecimal;
import java.util.UUID;

public record ServicoResponseDTO(UUID id, UUID profissionalId, String nome, Integer duracaoMinutos, BigDecimal preco) {
    public static ServicoResponseDTO fromEntity(Servico s) {
        return new ServicoResponseDTO(s.getId(), s.getProfissionalId(), s.getNome(), s.getDuracaoMinutos(), s.getPreco());
    }
}
