package com.agendalivre.api.infrastructure.persistence.repository;

import com.agendalivre.api.infrastructure.persistence.entity.ClienteJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface SpringDataClienteRepository extends JpaRepository<ClienteJpaEntity, UUID> {
}
