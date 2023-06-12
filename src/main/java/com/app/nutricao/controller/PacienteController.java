package com.app.nutricao.controller;

import com.app.nutricao.dto.PacienteDetalheDto;
import com.app.nutricao.dto.PacienteDto;
import com.app.nutricao.model.Paciente;
import com.app.nutricao.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("paciente")
public class PacienteController {
    @Autowired
    private PacienteService service;

    @PostMapping
    @CacheEvict(value = "paciente", allEntries = true)
    public ResponseEntity criarPaciente(@RequestBody @Valid PacienteDto dto, UriComponentsBuilder uriBuilder) {
        Paciente p = service.salvarPaciente(dto);
        var uri = uriBuilder.path("/paciente/{id}").buildAndExpand(p.getNome()).toUri();
        return ResponseEntity.created(uri).body(new PacienteDetalheDto(p));
    }

    @GetMapping
    @Cacheable(value = "paciente")
    public ResponseEntity<Page<Paciente>> getAllPacientes(@PageableDefault(sort = "id", page = 0, size = 10) Pageable page){
        Page<Paciente> listPaciente = service.getAllPaciente(page);
        return ResponseEntity.ok(listPaciente);
    }


    @PutMapping("/{id}")
    @CacheEvict(value = "paciente", allEntries = true)
    public ResponseEntity updatPaciente(@PathVariable("id") Long id, @RequestBody PacienteDto dto){
        Paciente t = service.atualizarPaciente(dto,id);

        return t != null?
                ResponseEntity.ok(t) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "paciente", allEntries = true)
    public ResponseEntity deletarCadastroPaciente(@PathVariable Long id){
        service.deletarPaciente(id);
        return ResponseEntity.ok().build();
    }
}
