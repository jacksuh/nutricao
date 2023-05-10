package com.app.nutricao.controller;

import com.app.nutricao.dto.PacienteDetalheDto;
import com.app.nutricao.dto.PacienteDto;
import com.app.nutricao.model.Paciente;
import com.app.nutricao.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("paciente")
public class PacienteController {


    @Autowired
    private PacienteService service;

    @PostMapping
    @CacheEvict(value = "ticket", allEntries = true)
    public ResponseEntity createdTicket(@RequestBody @Valid PacienteDto dto, UriComponentsBuilder uriBuilder) {
        Paciente p = service.savePaciente(dto);
        var uri = uriBuilder.path("/paciente/{id}").buildAndExpand(p).toUri();
        return ResponseEntity.created(uri).body(new PacienteDetalheDto());
    }

}