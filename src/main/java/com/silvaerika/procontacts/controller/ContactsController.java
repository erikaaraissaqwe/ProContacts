package com.silvaerika.procontacts.controller;

import com.silvaerika.procontacts.model.contacts.Contact;
import com.silvaerika.procontacts.service.contacts.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/contatos")
public class ContactsController {
    @Autowired
    private IContactService contactService;

    @GetMapping("/buscaTodos")
    public ResponseEntity<List<Contact>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(contactService.findAll());
    }

    @GetMapping
    public ResponseEntity<List<Contact>> findByParams(@RequestParam(required = false) String q,
                                                      @RequestParam(required = false) List<String> fields){
        return ResponseEntity.status(HttpStatus.OK).body(contactService.findByParams(q, fields));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Contact>> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(contactService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Contact> create(@RequestBody Contact contact){
        return ResponseEntity.status(HttpStatus.CREATED).body(contactService.save(contact));
    }

    @PutMapping
    public ResponseEntity<Contact> update(@RequestBody Contact contact){
        return ResponseEntity.status(HttpStatus.OK).body(contactService.update(contact));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        contactService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
