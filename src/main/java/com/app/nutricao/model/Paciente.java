package com.app.nutricao.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Paciente {

    private Long id;
    private String nome;
    private String pesoAtual;
}
