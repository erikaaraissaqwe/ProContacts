package com.silvaerika.procontacts.model.professional;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Schema(description = "Detalhes sobre a entidade Professional.")
public class Professional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professional_id")
    @Schema(description = "Identificador único do profissional.", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(name = "nome")
    @Schema(description = "Nome do profissional.")
    private String name;

    @Column(name = "cargo")
    @Schema(description = "Posição profissional do profissional.")
    private ProfessionalPosition professionalPosition;

    @Column(name = "nascimento")
    @Schema(description = "Data de nascimento do profissional.")
    private Date dateOfBirth;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    @Schema(description = "Data de criação do registro do profissional.", accessMode = Schema.AccessMode.READ_ONLY)
    private Date createdDate;

    public Professional() {
        this.createdDate = new Date();
    }
}
