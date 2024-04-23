package com.silvaerika.procontacts.controller;

import com.silvaerika.procontacts.model.professional.Professional;
import com.silvaerika.procontacts.service.professional.IProfessionalService;
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

    @GetMapping("/buscaTodos")
    public ResponseEntity<List<Professional>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(professionalService.findAll());
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> findByParams(@RequestParam(required = false) String q,
                                                                  @RequestParam(required = false) List<String> fields){
        return ResponseEntity.status(HttpStatus.OK).body(professionalService.findByParams(q, fields));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Professional>> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(professionalService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Professional> create(@RequestBody Professional professional){
        return ResponseEntity.status(HttpStatus.CREATED).body(professionalService.save(professional));
    }

    @PutMapping
    public ResponseEntity<Professional> update(@RequestBody Professional professional){
        return ResponseEntity.status(HttpStatus.OK).body(professionalService.update(professional));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        professionalService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
