package com.agendalivre.api.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "agendamentos")
@Data
@NoArgsConstructor
public class AgendamentoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "profissional_id", nullable = false)
    private UUID profissionalId;

    @Column(name = "cliente_id", nullable = false)
    private UUID clienteId;

    @Column(name = "servico_id", nullable = false)
    private UUID servicoId;

    @Column(name = "data_hora_inicio", nullable = false)
    private LocalDateTime dataHoraInicio;

    @Column(name = "data_hora_fim", nullable = false)
    private LocalDateTime dataHoraFim;

    @Column(nullable = false)
    private String status; // PENDENTE, CONFIRMADO, CANCELADO, CONCLUIDO
}
