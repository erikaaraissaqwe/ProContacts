package com.silvaerika.procontacts.model.contacts;

import com.silvaerika.procontacts.model.professional.Professional;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "contato")
    private String contact;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "nome")
    private ContactType contactType;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Professional professional;

    public Contact() {
        this.createdDate = new Date();
    }
}
