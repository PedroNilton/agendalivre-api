package com.agendalivre.api.application.gateway;

import com.agendalivre.api.domain.entity.Agendamento;
import java.util.Optional;
import java.util.UUID;
import java.time.LocalDateTime;

public interface AgendamentoRepository {
    Agendamento salvar(Agendamento agendamento);
    Optional<Agendamento> buscarPorId(UUID id);
    boolean existeConflitoHorario(UUID profissionalId, LocalDateTime inicio, LocalDateTime fim);
}
