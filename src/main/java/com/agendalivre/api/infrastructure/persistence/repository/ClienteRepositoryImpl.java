package com.agendalivre.api.infrastructure.persistence.repository;

import com.agendalivre.api.application.gateway.ClienteRepository;
import com.agendalivre.api.domain.entity.Cliente;
import com.agendalivre.api.infrastructure.persistence.entity.ClienteJpaEntity;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.UUID;

@Component
public class ClienteRepositoryImpl implements ClienteRepository {

    private final SpringDataClienteRepository springDataRepository;

    public ClienteRepositoryImpl(SpringDataClienteRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        ClienteJpaEntity entity = new ClienteJpaEntity();
        entity.setId(cliente.getId());
        entity.setNome(cliente.getNome());
        entity.setEmail(cliente.getEmail());
        entity.setTelefone(cliente.getTelefone());
        
        ClienteJpaEntity saved = springDataRepository.save(entity);
        return new Cliente(saved.getId(), saved.getNome(), saved.getEmail(), saved.getTelefone());
    }

    @Override
    public Optional<Cliente> buscarPorId(UUID id) {
        return springDataRepository.findById(id).map(e -> new Cliente(e.getId(), e.getNome(), e.getEmail(), e.getTelefone()));
    }
}
