package com.agendalivre.api.presentation.controller;

import com.agendalivre.api.application.usecase.CadastrarClienteUseCase;
import com.agendalivre.api.presentation.dto.ClienteRequestDTO;
import com.agendalivre.api.presentation.dto.ClienteResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final CadastrarClienteUseCase useCase;

    public ClienteController(CadastrarClienteUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> cadastrar(@Valid @RequestBody ClienteRequestDTO request) {
        var cliente = useCase.executar(request.nome(), request.email(), request.telefone());
        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteResponseDTO.fromEntity(cliente));
    }
}
