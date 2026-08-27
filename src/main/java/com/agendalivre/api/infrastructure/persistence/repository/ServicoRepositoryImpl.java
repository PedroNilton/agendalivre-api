package com.agendalivre.api.infrastructure.persistence.repository;

import com.agendalivre.api.application.gateway.ServicoRepository;
import com.agendalivre.api.domain.entity.Servico;
import com.agendalivre.api.infrastructure.persistence.entity.ServicoJpaEntity;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.UUID;

@Component
public class ServicoRepositoryImpl implements ServicoRepository {

    private final SpringDataServicoRepository springDataRepository;

    public ServicoRepositoryImpl(SpringDataServicoRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Servico salvar(Servico servico) {
        ServicoJpaEntity entity = new ServicoJpaEntity();
        entity.setId(servico.getId());
        entity.setProfissionalId(servico.getProfissionalId());
        entity.setNome(servico.getNome());
        entity.setDuracaoMinutos(servico.getDuracaoMinutos());
        entity.setPreco(servico.getPreco());
        
        ServicoJpaEntity saved = springDataRepository.save(entity);
        return new Servico(saved.getId(), saved.getProfissionalId(), saved.getNome(), saved.getDuracaoMinutos(), saved.getPreco());
    }

    @Override
    public Optional<Servico> buscarPorId(UUID id) {
        return springDataRepository.findById(id).map(e -> new Servico(e.getId(), e.getProfissionalId(), e.getNome(), e.getDuracaoMinutos(), e.getPreco()));
    }
}
