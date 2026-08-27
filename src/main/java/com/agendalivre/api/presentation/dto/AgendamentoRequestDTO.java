package com.agendalivre.api.presentation.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public record AgendamentoRequestDTO(
        @NotNull(message = "ID do profissional é obrigatório")
        UUID profissionalId,

        @NotNull(message = "ID do cliente é obrigatório")
        UUID clienteId,

        @NotNull(message = "ID do serviço é obrigatório")
        UUID servicoId,

        @NotNull(message = "Data e hora de início são obrigatórias")
        @Future(message = "A data de agendamento deve estar no futuro")
        LocalDateTime dataHoraInicio
) {}
