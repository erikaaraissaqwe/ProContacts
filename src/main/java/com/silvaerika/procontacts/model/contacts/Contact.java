package com.silvaerika.procontacts.model.contacts;

import com.silvaerika.procontacts.model.professional.Professional;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Schema(description = "Detalhes sobre a entidade Contact.")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    @Schema(description = "Identificador único do contato.", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(name = "contato")
    @Schema(description = "Informações de contato.")
    private String contact;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    @Schema(description = "Data de criação do contato.", accessMode = Schema.AccessMode.READ_ONLY)
    private Date createdDate;

    @Column(name = "nome")
    @Schema(description = "Tipo de contato.")
    private ContactType contactType;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    @Schema(description = "Profissional associado ao contato. Necessário enviar somente ID dentro do objeto.", accessMode = Schema.AccessMode.READ_ONLY)
    private Professional professional;

    public Contact() {
        this.createdDate = new Date();
    }
}
