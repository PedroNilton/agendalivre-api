package com.agendalivre.api.infrastructure.persistence.repository;

import com.agendalivre.api.application.gateway.ProfissionalRepository;
import com.agendalivre.api.domain.entity.Profissional;
import com.agendalivre.api.infrastructure.persistence.entity.ProfissionalJpaEntity;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.UUID;

@Component
public class ProfissionalRepositoryImpl implements ProfissionalRepository {

    private final SpringDataProfissionalRepository springDataRepository;

    public ProfissionalRepositoryImpl(SpringDataProfissionalRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Profissional salvar(Profissional profissional) {
        ProfissionalJpaEntity entity = new ProfissionalJpaEntity();
        entity.setId(profissional.getId());
        entity.setNome(profissional.getNome());
        entity.setEmail(profissional.getEmail());
        entity.setTelefone(profissional.getTelefone());
        
        ProfissionalJpaEntity saved = springDataRepository.save(entity);
        return new Profissional(saved.getId(), saved.getNome(), saved.getEmail(), saved.getTelefone());
    }

    @Override
    public Optional<Profissional> buscarPorId(UUID id) {
        return springDataRepository.findById(id).map(e -> new Profissional(e.getId(), e.getNome(), e.getEmail(), e.getTelefone()));
    }
}
