package com.agendalivre.api.application.gateway;

import com.agendalivre.api.domain.entity.Cliente;
import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository {
    Cliente salvar(Cliente cliente);
    Optional<Cliente> buscarPorId(UUID id);
}
