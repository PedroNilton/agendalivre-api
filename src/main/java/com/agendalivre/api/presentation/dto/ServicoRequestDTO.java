package com.agendalivre.api.presentation.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public record ServicoRequestDTO(
        @NotNull(message = "O ID do profissional é obrigatório") UUID profissionalId,
        @NotBlank(message = "O nome é obrigatório") String nome,
        @NotNull(message = "A duração é obrigatória") @Min(value = 1, message = "A duração deve ser de no mínimo 1 minuto") Integer duracaoMinutos,
        @NotNull(message = "O preço é obrigatório") @DecimalMin(value = "0.0", message = "O preço não pode ser negativo") BigDecimal preco
) {}
