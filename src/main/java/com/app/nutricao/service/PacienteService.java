package com.app.nutricao.service;

import com.app.nutricao.dto.PacienteDto;
import com.app.nutricao.model.Paciente;
import com.app.nutricao.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;


    public Page<Paciente> getAllPaciente(Pageable page) {
        return repository.findAll(page);
    }

    public Paciente salvarPaciente(PacienteDto dto){
        Paciente p = new Paciente();
        p.setNome(dto.nome());
        p.setPesoAtual(dto.pesoAtual());

        repository.save(p);
        return p;
    }

    public Paciente atualizarPaciente(PacienteDto dto, Long id) {
        Optional<Paciente> optional = repository.findById(id);
        Paciente p = optional.get();
        p.setNome(dto.nome());
        p.setPesoAtual(dto.pesoAtual());

        return repository.save(p);
    }


    public void deletarPaciente(Long id) {
        Optional<Paciente> paciente = repository.findById(id);
        if (paciente.isPresent()) {
            repository.deleteById(id);
        }

    }
}
