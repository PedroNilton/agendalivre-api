package com.agendalivre.api.application.usecase;

import com.agendalivre.api.application.gateway.AgendamentoRepository;
import com.agendalivre.api.application.gateway.ClienteRepository;
import com.agendalivre.api.application.gateway.ProfissionalRepository;
import com.agendalivre.api.application.gateway.ServicoRepository;
import com.agendalivre.api.domain.entity.Agendamento;
import com.agendalivre.api.domain.entity.Servico;
import com.agendalivre.api.domain.entity.StatusAgendamento;
import com.agendalivre.api.domain.exception.RegraNegocioException;

import java.time.LocalDateTime;
import java.util.UUID;

public class AgendarHorarioUseCase {

    private final AgendamentoRepository agendamentoRepository;
    private final ProfissionalRepository profissionalRepository;
    private final ClienteRepository clienteRepository;
    private final ServicoRepository servicoRepository;

    public AgendarHorarioUseCase(AgendamentoRepository agendamentoRepository,
                                 ProfissionalRepository profissionalRepository,
                                 ClienteRepository clienteRepository,
                                 ServicoRepository servicoRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.profissionalRepository = profissionalRepository;
        this.clienteRepository = clienteRepository;
        this.servicoRepository = servicoRepository;
    }

    public Agendamento executar(UUID profissionalId, UUID clienteId, UUID servicoId, LocalDateTime dataHoraInicio) {
        
        // 1. Validar se entidades existem (fail-fast)
        profissionalRepository.buscarPorId(profissionalId)
                .orElseThrow(() -> new RegraNegocioException("Profissional não encontrado."));
        
        clienteRepository.buscarPorId(clienteId)
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado."));
        
        Servico servico = servicoRepository.buscarPorId(servicoId)
                .orElseThrow(() -> new RegraNegocioException("Serviço não encontrado."));

        // 2. Calcular hora de término baseado na duração do serviço
        LocalDateTime dataHoraFim = dataHoraInicio.plusMinutes(servico.getDuracaoMinutos());

        // 3. Verificar conflito de horários (Concorrência/Business Rule)
        boolean temConflito = agendamentoRepository.existeConflitoHorario(profissionalId, dataHoraInicio, dataHoraFim);
        if (temConflito) {
            throw new RegraNegocioException("O profissional já possui um agendamento neste horário.");
        }

        // 4. Criar a entidade de domínio
        Agendamento novoAgendamento = new Agendamento(
                UUID.randomUUID(),
                profissionalId,
                clienteId,
                servicoId,
                dataHoraInicio,
                dataHoraFim,
                StatusAgendamento.PENDENTE
        );

        // 5. Persistir usando o gateway
        return agendamentoRepository.salvar(novoAgendamento);
    }
}
