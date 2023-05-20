package com.app.nutricao.service;

import com.app.nutricao.dto.PacienteDto;
import com.app.nutricao.model.Paciente;
import com.app.nutricao.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public Paciente salvarPaciente(PacienteDto dto){
        Paciente p = new Paciente();
        p.setNome(dto.nome());
        p.setPesoAtual(dto.pesoAtual());

        repository.save(p);
        return p;
    }

    public void deletarPaciente(Long id) {
        Optional<Paciente> paciente = repository.findById(id);
        if (paciente.isPresent()) {
            repository.deleteById(id);
        }

    }
}
