package com.agendalivre.api.presentation.controller;

import com.agendalivre.api.application.usecase.CadastrarProfissionalUseCase;
import com.agendalivre.api.presentation.dto.ProfissionalRequestDTO;
import com.agendalivre.api.presentation.dto.ProfissionalResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profissionais")
public class ProfissionalController {

    private final CadastrarProfissionalUseCase useCase;

    public ProfissionalController(CadastrarProfissionalUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<ProfissionalResponseDTO> cadastrar(@Valid @RequestBody ProfissionalRequestDTO request) {
        var profissional = useCase.executar(request.nome(), request.email(), request.telefone());
        return ResponseEntity.status(HttpStatus.CREATED).body(ProfissionalResponseDTO.fromEntity(profissional));
    }
}
