package com.agendalivre.api.application.gateway;

import com.agendalivre.api.domain.entity.Servico;
import java.util.Optional;
import java.util.UUID;

public interface ServicoRepository {
    Servico salvar(Servico servico);
    Optional<Servico> buscarPorId(UUID id);
}
