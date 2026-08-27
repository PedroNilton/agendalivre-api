package com.agendalivre.api.application.usecase;

import com.agendalivre.api.application.gateway.ClienteRepository;
import com.agendalivre.api.domain.entity.Cliente;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CadastrarClienteUseCase {
    private final ClienteRepository repository;

    public CadastrarClienteUseCase(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente executar(String nome, String email, String telefone) {
        Cliente novo = new Cliente(UUID.randomUUID(), nome, email, telefone);
        return repository.salvar(novo);
    }
}
