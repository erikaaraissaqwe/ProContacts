package com.silvaerika.procontacts.repository.contacts;

import com.silvaerika.procontacts.model.contacts.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IContactRepository extends JpaRepository<Contact, Long> {

    @Query("SELECT p FROM Contact p WHERE LOWER(p.contact) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Contact> findByNomeContainingIgnoreCase(@Param("nome") String nome);
}
