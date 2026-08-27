package com.agendalivre.api.application.gateway;

import com.agendalivre.api.domain.entity.Profissional;
import java.util.Optional;
import java.util.UUID;

public interface ProfissionalRepository {
    Optional<Profissional> buscarPorId(UUID id);
}
