package com.agendalivre.api.infrastructure.persistence.repository;

import com.agendalivre.api.application.gateway.AgendamentoRepository;
import com.agendalivre.api.domain.entity.Agendamento;
import com.agendalivre.api.domain.entity.StatusAgendamento;
import com.agendalivre.api.infrastructure.persistence.entity.AgendamentoJpaEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
public class AgendamentoRepositoryImpl implements AgendamentoRepository {

    private final SpringDataAgendamentoRepository springDataRepository;

    public AgendamentoRepositoryImpl(SpringDataAgendamentoRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Agendamento salvar(Agendamento agendamento) {
        AgendamentoJpaEntity entity = new AgendamentoJpaEntity();
        entity.setId(agendamento.getId());
        entity.setProfissionalId(agendamento.getProfissionalId());
        entity.setClienteId(agendamento.getClienteId());
        entity.setServicoId(agendamento.getServicoId());
        entity.setDataHoraInicio(agendamento.getDataHoraInicio());
        entity.setDataHoraFim(agendamento.getDataHoraFim());
        entity.setStatus(agendamento.getStatus().name());

        AgendamentoJpaEntity savedEntity = springDataRepository.save(entity);

        return new Agendamento(
                savedEntity.getId(),
                savedEntity.getProfissionalId(),
                savedEntity.getClienteId(),
                savedEntity.getServicoId(),
                savedEntity.getDataHoraInicio(),
                savedEntity.getDataHoraFim(),
                StatusAgendamento.valueOf(savedEntity.getStatus())
        );
    }

    @Override
    public Optional<Agendamento> buscarPorId(UUID id) {
        // Implementação omitida por brevidade
        return Optional.empty();
    }

    @Override
    public boolean existeConflitoHorario(UUID profissionalId, LocalDateTime inicio, LocalDateTime fim) {
        return springDataRepository.existsConflitoHorario(profissionalId, inicio, fim);
    }
}
