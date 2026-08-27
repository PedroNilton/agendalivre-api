package com.agendalivre.api.presentation.dto;

import com.agendalivre.api.domain.entity.Agendamento;
import java.time.LocalDateTime;
import java.util.UUID;

public record AgendamentoResponseDTO(
        UUID id,
        UUID profissionalId,
        UUID clienteId,
        UUID servicoId,
        LocalDateTime dataHoraInicio,
        LocalDateTime dataHoraFim,
        String status
) {
    public static AgendamentoResponseDTO fromEntity(Agendamento agendamento) {
        return new AgendamentoResponseDTO(
                agendamento.getId(),
                agendamento.getProfissionalId(),
                agendamento.getClienteId(),
                agendamento.getServicoId(),
                agendamento.getDataHoraInicio(),
                agendamento.getDataHoraFim(),
                agendamento.getStatus().name()
        );
    }
}
