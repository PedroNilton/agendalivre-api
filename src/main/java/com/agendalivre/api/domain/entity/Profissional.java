package com.agendalivre.api.domain.entity;

import java.util.UUID;

public class Profissional {

    private UUID id;
    private String nome;
    private String email;
    private String telefone;

    public Profissional(UUID id, String nome, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        validar();
    }

    private void validar() {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do profissional é obrigatório.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email do profissional é obrigatório.");
        }
    }

    // Getters
    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
}
