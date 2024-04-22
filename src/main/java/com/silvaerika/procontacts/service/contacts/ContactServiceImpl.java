package com.silvaerika.procontacts.service.contacts;

import com.silvaerika.procontacts.model.contacts.Contact;
import com.silvaerika.procontacts.repository.contacts.IContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements IContactService {

    @Autowired
    private IContactRepository contactRepository;

    @Override
    public Contact save(Contact contact) {
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
        return contactRepository.save(contact);
    }

    @Override
    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }

    @Override
    public List<Contact> findByParams(String q, List<String> fields) {
        List<Contact> contacts;
        if (q != null && !q.isEmpty()) {
            contacts = contactRepository.findByNomeContainingIgnoreCase(q);
        } else {
            contacts = contactRepository.findAll();
        }

        if (fields != null && !fields.isEmpty()) {
            // Se os campos a serem retornados forem especificados, filtramos os resultados
            List<Contact> filteredProfissionais = new ArrayList<>();
            for (Contact contact : contacts) {
                Contact filteredContact = new Contact();
                for (String field : fields) {
                    switch (field) {
                        case "nome":
                            filteredContact.setContactType(contact.getContactType());
                            break;
                        case "contato":
                            filteredContact.setContact(contact.getContact());
                            break;
                    }
                }
                filteredProfissionais.add(filteredContact);
            }
            return filteredProfissionais;
        } else {
            return contacts;
        }
    }
}
