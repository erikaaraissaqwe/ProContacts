package com.silvaerika.procontacts;

import com.silvaerika.procontacts.model.contacts.Contact;
import com.silvaerika.procontacts.repository.contacts.IContactRepository;
import com.silvaerika.procontacts.service.contacts.ContactServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ProcontactsApplication.class, properties = {"spring.profiles.active=test"})
public class ContactServiceTests {

    @Autowired
    private ContactServiceImpl contactService;

    @MockBean
    private IContactRepository contactRepository;

    @Test
    public void testFindAll() {
        when(contactRepository.findAll()).thenReturn(Arrays.asList(new Contact(), new Contact()));
        assertEquals(2, contactService.findAll().size());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Contact contact = new Contact();
        contact.setId(id);
        when(contactRepository.findById(id)).thenReturn(Optional.of(contact));
        assertEquals(contact, contactService.findById(id).get());
    }

    @Test
    public void testSave() {
        Contact contact = new Contact();
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);
        assertEquals(contact, contactService.save(new Contact()));
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        doNothing().when(contactRepository).deleteById(id);
        contactService.deleteById(id);
        verify(contactRepository, times(1)).deleteById(id);
    }
}
