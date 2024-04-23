package com.silvaerika.procontacts.service.contacts;

import com.silvaerika.procontacts.model.contacts.Contact;
import com.silvaerika.procontacts.model.professional.Professional;
import com.silvaerika.procontacts.repository.contacts.IContactRepository;
import com.silvaerika.procontacts.service.professional.IProfessionalService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.silvaerika.procontacts.service.utils.Utils.findByFields;

@Service
public class ContactServiceImpl implements IContactService {

    @Autowired
    private IContactRepository contactRepository;

    @Autowired
    private IProfessionalService professionalService;

    @Override
    public Contact save(Contact contact) {
        Long professionalId = contact.getProfessional() != null ? contact.getProfessional().getId() : null;

        if (professionalId == null) {
            throw new IllegalArgumentException("É necessário fornecer o ID do profissional para criar o contato vinculado.");
        }

        Professional existingProfessional = professionalService.findById(professionalId)
                .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado com o ID fornecido: " + professionalId));

        contact.setProfessional(existingProfessional);

        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public Optional<Contact> findById(Long id) {
        return contactRepository.findById(id);
    }

    @Override
    public Contact update(Contact contact) {
        Contact existingContact = contactRepository.findById(contact.getId())
                .orElseThrow(() -> new RuntimeException("Contato não encontrado com o ID: " + contact.getId()));

        BeanUtils.copyProperties(contact, existingContact, "id", "professional");
        return contactRepository.save(existingContact);
    }

    @Override
    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }

    @Override
    public List<Map<String, Object>> findByParams(String q, List<String> fields) {
        List<Contact> contacts = findContactByAttribute(q);

        if (fields == null || fields.isEmpty())
            return findByFields(contacts, null);

        return findByFields(contacts, fields);
    }

    public List<Contact> findContactByAttribute(String value) {
        List<Contact> allContacts = contactRepository.findAll();

        if (value == null || value.isEmpty())
            return allContacts;

        return allContacts.stream()
                .filter(contact ->
                        contact.getContact().contains(value) ||
                                contact.getContactType().getValue().contains(value))
                .collect(Collectors.toList());
    }
}
