package com.silvaerika.procontacts.service.contacts;

import com.silvaerika.procontacts.model.contacts.Contact;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IContactService {
    Contact save(Contact contact);

    List<Contact> findAll();

    Optional<Contact> findById(Long id);

    Contact update(Contact contact);

    void deleteById(Long id);

    List<Map<String, Object>> findByParams(String q, List<String> fields);
}
