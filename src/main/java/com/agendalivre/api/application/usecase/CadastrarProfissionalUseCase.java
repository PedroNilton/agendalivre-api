package com.agendalivre.api.application.usecase;

import com.agendalivre.api.application.gateway.ProfissionalRepository;
import com.agendalivre.api.domain.entity.Profissional;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CadastrarProfissionalUseCase {
    private final ProfissionalRepository repository;

    public CadastrarProfissionalUseCase(ProfissionalRepository repository) {
        this.repository = repository;
    }

    public Profissional executar(String nome, String email, String telefone) {
        Profissional novo = new Profissional(UUID.randomUUID(), nome, email, telefone);
        return repository.salvar(novo);
    }
}
