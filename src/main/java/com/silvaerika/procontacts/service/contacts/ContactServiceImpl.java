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

    /**
     * Salva um novo contato.
     *
     * @param contact O contato a ser salvo.
     * @return O contato salvo.
     * @throws IllegalArgumentException Se o ID do profissional fornecido não for válido.
     */
    @Override
    public Contact save(Contact contact) {
        Long professionalId = contact.getProfessional() != null ? contact.getProfessional().getId() : null;

        if (professionalId != null) {
            Professional existingProfessional = professionalService.findById(professionalId)
                    .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado com o ID fornecido: " + professionalId));
            contact.setProfessional(existingProfessional);
        }

        return contactRepository.save(contact);
    }

    /**
     * Retorna todos os contatos.
     *
     * @return Uma lista de todos os contatos.
     */
    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    /**
     * Encontra um contato pelo ID.
     *
     * @param id O ID do contato.
     * @return Um Optional contendo o contato, se encontrado.
     */
    @Override
    public Optional<Contact> findById(Long id) {
        return contactRepository.findById(id);
    }

    /**
     * Atualiza um contato existente.
     *
     * @param contact O contato com os novos dados.
     * @return O contato atualizado.
     * @throws RuntimeException Se o contato não for encontrado com o ID fornecido.
     */
    @Override
    public Contact update(Contact contact) {
        Contact existingContact = contactRepository.findById(contact.getId())
                .orElseThrow(() -> new RuntimeException("Contato não encontrado com o ID: " + contact.getId()));

        Long professionalId = contact.getProfessional() != null &&  existingContact.getProfessional() == null ? contact.getProfessional().getId() : null;

        if (professionalId != null) {
            Professional existingProfessional = professionalService.findById(professionalId)
                    .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado com o ID fornecido: " + professionalId));
            existingContact.setProfessional(existingProfessional);
        }


        BeanUtils.copyProperties(contact, existingContact, "id", "professional");
        return contactRepository.save(existingContact);
    }

    /**
     * Exclui um contato pelo ID.
     *
     * @param id O ID do contato a ser excluído.
     */
    @Override
    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }

    /**
     * Retorna campos dos contatos com base nos parâmetros fornecidos e filtrados pelo valor dos atributos.
     *
     * @param q      Valor que deverá ser filtrado nos atributos de contato e tipo do contato.
     * @param fields Os campos pelos quais a busca retornará, ex: createdDate, id.
     * @return Uma lista de map contendo os contatos encontrados somente com os campos escolhidos, se escolhidos.
     */
    @Override
    public List<Map<String, Object>> findByParams(String q, List<String> fields) {
        List<Contact> contacts = findContactByAttribute(q);

        if (fields == null || fields.isEmpty())
            return findByFields(contacts, null);

        return findByFields(contacts, fields);
    }

    /**
     * Encontra contatos pelo valor dos atributos de contato e tipo do contato.
     *
     * @param value O valor a ser usado na busca.
     * @return Uma lista de contatos encontrados.
     */
    private List<Contact> findContactByAttribute(String value) {
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
