package com.silvaerika.procontacts.controller;

import com.silvaerika.procontacts.model.professional.Professional;
import com.silvaerika.procontacts.service.professional.IProfessionalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/profissionais")
public class ProfessionalController {
    @Autowired
    private IProfessionalService professionalService;

    @Operation(summary = "Obter todos os profissionais")
    @GetMapping("/all")
    public ResponseEntity<List<Professional>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(professionalService.findAll());
    }

    @Operation(summary = "Buscar profissionais por parâmetros")
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> findProfessionalsByCriteria(
            @Parameter(description = "Texto para busca genérica nos atributos de nome e cargo")
            @RequestParam(required = false) String searchQuery,
            @Parameter(description = "Lista de campos específicos a serem retornados no resultado")
            @RequestParam(required = false) List<String> selectedFields){
        return ResponseEntity.status(HttpStatus.OK).body(professionalService.findByParams(searchQuery, selectedFields));
    }

    @Operation(summary = "Buscar profissional por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Professional>> findById(
            @Parameter(description = "ID do profissional", required = true) @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(professionalService.findById(id));
    }

    @Operation(summary = "Criar um novo profissional",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(schema = @Schema(implementation = Professional.class))
            ))
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Professional professional){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(professionalService.save(professional));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Atualizar um profissional existente",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(schema = @Schema(implementation = Professional.class))
            ))
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Professional professional){
        try {
            professional.setId(id);
            return ResponseEntity.status(HttpStatus.OK).body(professionalService.update(professional));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Excluir um profissional por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @Parameter(description = "ID do profissional", required = true) @PathVariable Long id){
        try {
            professionalService.deleteById(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Profissional deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Um erro ocorreu ao deletar profissional");
        }
    }
}
