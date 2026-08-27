package com.agendalivre.api.domain.entity;

import com.agendalivre.api.domain.exception.RegraNegocioException;

import java.time.LocalDateTime;
import java.util.UUID;

public class Agendamento {

    private UUID id;
    private UUID profissionalId;
    private UUID clienteId;
    private UUID servicoId;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private StatusAgendamento status;

    public Agendamento(UUID id, UUID profissionalId, UUID clienteId, UUID servicoId,
                       LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, StatusAgendamento status) {
        this.id = id;
        this.profissionalId = profissionalId;
        this.clienteId = clienteId;
        this.servicoId = servicoId;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.status = status != null ? status : StatusAgendamento.PENDENTE;
        validar();
    }

    private void validar() {
        if (profissionalId == null || clienteId == null || servicoId == null) {
            throw new IllegalArgumentException("IDs de profissional, cliente e serviço são obrigatórios.");
        }
        if (dataHoraInicio == null || dataHoraFim == null) {
            throw new IllegalArgumentException("Data e hora de início e fim são obrigatórias.");
        }
        if (dataHoraInicio.isBefore(LocalDateTime.now())) {
            throw new RegraNegocioException("Não é possível agendar em uma data/hora no passado.");
        }
        if (dataHoraFim.isBefore(dataHoraInicio) || dataHoraFim.isEqual(dataHoraInicio)) {
            throw new RegraNegocioException("Data/hora de fim deve ser posterior à de início.");
        }
    }

    public void confirmar() {
        if (this.status == StatusAgendamento.CANCELADO) {
            throw new RegraNegocioException("Agendamento cancelado não pode ser confirmado.");
        }
        this.status = StatusAgendamento.CONFIRMADO;
    }

    public void cancelar() {
        if (this.status == StatusAgendamento.CONCLUIDO) {
            throw new RegraNegocioException("Agendamento já concluído não pode ser cancelado.");
        }
        this.status = StatusAgendamento.CANCELADO;
    }

    public UUID getId() { return id; }
    public UUID getProfissionalId() { return profissionalId; }
    public UUID getClienteId() { return clienteId; }
    public UUID getServicoId() { return servicoId; }
    public LocalDateTime getDataHoraInicio() { return dataHoraInicio; }
    public LocalDateTime getDataHoraFim() { return dataHoraFim; }
    public StatusAgendamento getStatus() { return status; }
}
