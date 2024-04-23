package com.silvaerika.procontacts.controller;

import com.silvaerika.procontacts.model.contacts.Contact;
import com.silvaerika.procontacts.service.contacts.IContactService;
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
@RequestMapping("api/v1/contatos")
public class ContactsController {
    @Autowired
    private IContactService contactService;

    @Operation(summary = "Obter todos os contatos")
    @GetMapping("/all")
    public ResponseEntity<List<Contact>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(contactService.findAll());
    }

    @Operation(summary = "Buscar contatos por parâmetros")
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> findContactsByCriteria(
            @Parameter(description = "Texto para busca genérica nos atributos de contato e tipo do contato")
            @RequestParam(required = false) String searchQuery,
            @Parameter(description = "Lista de campos específicos a serem retornados no resultado")
            @RequestParam(required = false) List<String> selectedFields){
        return ResponseEntity.status(HttpStatus.OK).body(contactService.findByParams(searchQuery, selectedFields));
    }

    @Operation(summary = "Buscar contato por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Contact>> findById(
            @Parameter(description = "ID do contato", required = true) @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(contactService.findById(id));
    }

    @Operation(summary = "Criar um novo contato",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(schema = @Schema(implementation = Contact.class))
            ))
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Contact contact){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(contactService.save(contact));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Atualizar um contato existente",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(schema = @Schema(implementation = Contact.class))
            ))
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Contact contact){
        try {
            contact.setId(id);
            return ResponseEntity.status(HttpStatus.OK).body(contactService.update(contact));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Excluir um contato por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @Parameter(description = "ID do contato", required = true) @PathVariable Long id){
        try {
            contactService.deleteById(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Contato deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Um erro ocorreu ao deletar contato");
        }
    }
}