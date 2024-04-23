package com.silvaerika.procontacts.controller;

import com.silvaerika.procontacts.model.contacts.Contact;
import com.silvaerika.procontacts.model.professional.Professional;
import com.silvaerika.procontacts.model.professionalContacts.ProfessionalContacts;
import com.silvaerika.procontacts.service.contacts.IContactService;
import com.silvaerika.procontacts.service.professional.IProfessionalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/profissionalContatos")
public class ProfessionalContactsController {

    @Autowired
    private IContactService contactService;

    @Autowired
    private IProfessionalService professionalService;
    @Operation(summary = "Salvar profissional com contatos",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(schema = @Schema(implementation = ProfessionalContacts.class))
            ))
    @PostMapping
    public ResponseEntity<String> saveProfessionalWithContacts(@RequestBody ProfessionalContacts professionalContacts) {
        try {
            Professional professional = professionalContacts.getProfessional();
            Professional savedProfessional = professionalService.save(professional);

            for (Contact contact : professionalContacts.getContacts()) {
                contact.setProfessional(savedProfessional);
                contactService.save(contact);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body("Profissional e contatos salvos com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Um erro ocorreu ao salvar o profissional/contatos");
        }
    }
}
