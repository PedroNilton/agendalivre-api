package com.agendalivre.api.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class Servico {

    private UUID id;
    private UUID profissionalId;
    private String nome;
    private Integer duracaoMinutos;
    private BigDecimal preco;

    public Servico(UUID id, UUID profissionalId, String nome, Integer duracaoMinutos, BigDecimal preco) {
        this.id = id;
        this.profissionalId = profissionalId;
        this.nome = nome;
        this.duracaoMinutos = duracaoMinutos;
        this.preco = preco;
        validar();
    }

    private void validar() {
        if (profissionalId == null) {
            throw new IllegalArgumentException("ID do profissional é obrigatório.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do serviço é obrigatório.");
        }
        if (duracaoMinutos == null || duracaoMinutos <= 0) {
            throw new IllegalArgumentException("Duração do serviço deve ser maior que zero.");
        }
        if (preco == null || preco.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo.");
        }
    }

    public UUID getId() { return id; }
    public UUID getProfissionalId() { return profissionalId; }
    public String getNome() { return nome; }
    public Integer getDuracaoMinutos() { return duracaoMinutos; }
    public BigDecimal getPreco() { return preco; }
}
