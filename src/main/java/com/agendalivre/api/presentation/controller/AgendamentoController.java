package com.agendalivre.api.presentation.controller;

import com.agendalivre.api.application.usecase.AgendarHorarioUseCase;
import com.agendalivre.api.domain.entity.Agendamento;
import com.agendalivre.api.presentation.dto.AgendamentoRequestDTO;
import com.agendalivre.api.presentation.dto.AgendamentoResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/agendamentos")
public class AgendamentoController {

    private final AgendarHorarioUseCase agendarHorarioUseCase;

    public AgendamentoController(AgendarHorarioUseCase agendarHorarioUseCase) {
        this.agendarHorarioUseCase = agendarHorarioUseCase;
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> agendar(@Valid @RequestBody AgendamentoRequestDTO request) {
        Agendamento agendamento = agendarHorarioUseCase.executar(
                request.profissionalId(),
                request.clienteId(),
                request.servicoId(),
                request.dataHoraInicio()
        );

        AgendamentoResponseDTO response = AgendamentoResponseDTO.fromEntity(agendamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
