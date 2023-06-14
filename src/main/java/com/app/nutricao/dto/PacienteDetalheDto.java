package com.app.nutricao.dto;

import com.app.nutricao.model.Paciente;

public record PacienteDetalheDto(Long id, String nome, String pesoAtual, String imc) {

    public PacienteDetalheDto(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getPesoAtual(), paciente.getImc());
    }
}
