package com.agendalivre.api.infrastructure.persistence.repository;

import com.agendalivre.api.infrastructure.persistence.entity.AgendamentoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface SpringDataAgendamentoRepository extends JpaRepository<AgendamentoJpaEntity, UUID> {

    @Query("SELECT COUNT(a) > 0 FROM AgendamentoJpaEntity a WHERE a.profissionalId = :profissionalId " +
           "AND a.status != 'CANCELADO' " +
           "AND ((a.dataHoraInicio < :fim AND a.dataHoraFim > :inicio))")
    boolean existsConflitoHorario(@Param("profissionalId") UUID profissionalId,
                                  @Param("inicio") LocalDateTime inicio,
                                  @Param("fim") LocalDateTime fim);
}
