package com.silvaerika.procontacts.repository.contacts;

import com.silvaerika.procontacts.model.contacts.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContactRepository extends JpaRepository<Contact, Long> { }
