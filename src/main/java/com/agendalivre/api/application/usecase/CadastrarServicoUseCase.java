package com.agendalivre.api.application.usecase;

import com.agendalivre.api.application.gateway.ServicoRepository;
import com.agendalivre.api.application.gateway.ProfissionalRepository;
import com.agendalivre.api.domain.entity.Servico;
import com.agendalivre.api.domain.exception.RegraNegocioException;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.UUID;

@Service
public class CadastrarServicoUseCase {
    private final ServicoRepository servicoRepository;
    private final ProfissionalRepository profissionalRepository;

    public CadastrarServicoUseCase(ServicoRepository servicoRepository, ProfissionalRepository profissionalRepository) {
        this.servicoRepository = servicoRepository;
        this.profissionalRepository = profissionalRepository;
    }

    public Servico executar(UUID profissionalId, String nome, Integer duracaoMinutos, BigDecimal preco) {
        profissionalRepository.buscarPorId(profissionalId)
                .orElseThrow(() -> new RegraNegocioException("Profissional não encontrado para vincular o serviço."));

        Servico novo = new Servico(UUID.randomUUID(), profissionalId, nome, duracaoMinutos, preco);
        return servicoRepository.salvar(novo);
    }
}
