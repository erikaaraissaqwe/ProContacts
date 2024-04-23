package com.silvaerika.procontacts.model.professionalContacts;

import com.silvaerika.procontacts.model.contacts.Contact;
import com.silvaerika.procontacts.model.professional.Professional;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Detalhes sobre a entidade ProfessionalContacts.")
public class ProfessionalContacts {
    @Schema(description = "Lista de contatos a serem criados e vinculados com o profissional.")
    private List<Contact> contacts;
    @Schema(description = "Profissional a ser criado.")
    private Professional professional;
}
