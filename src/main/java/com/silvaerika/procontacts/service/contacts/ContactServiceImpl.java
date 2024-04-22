package com.silvaerika.procontacts.service.contacts;

import com.silvaerika.procontacts.model.contacts.Contact;
import com.silvaerika.procontacts.repository.contacts.IContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
