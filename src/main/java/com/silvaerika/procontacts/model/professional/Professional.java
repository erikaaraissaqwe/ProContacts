package com.silvaerika.procontacts.model.professional;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Professional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professional_id")
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "cargo")
    private ProfessionalPosition professionalPosition;

    @Column(name = "nascimento")
    private Date dateOfBirth;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    public Professional() {
        this.createdDate = new Date();
    }
}
