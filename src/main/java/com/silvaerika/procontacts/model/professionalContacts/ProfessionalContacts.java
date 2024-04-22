package com.silvaerika.procontacts.model.professionalContacts;

import com.silvaerika.procontacts.model.contacts.Contact;
import com.silvaerika.procontacts.model.professional.Professional;
import lombok.Data;

import java.util.List;

@Data
public class ProfessionalContacts {
    private Professional professional;
    private List<Contact> contacts;
}
