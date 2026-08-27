package com.agendalivre.api.presentation.controller;

import com.agendalivre.api.application.usecase.CadastrarServicoUseCase;
import com.agendalivre.api.presentation.dto.ServicoRequestDTO;
import com.agendalivre.api.presentation.dto.ServicoResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/servicos")
public class ServicoController {

    private final CadastrarServicoUseCase useCase;

    public ServicoController(CadastrarServicoUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<ServicoResponseDTO> cadastrar(@Valid @RequestBody ServicoRequestDTO request) {
        var servico = useCase.executar(request.profissionalId(), request.nome(), request.duracaoMinutos(), request.preco());
        return ResponseEntity.status(HttpStatus.CREATED).body(ServicoResponseDTO.fromEntity(servico));
    }
}
