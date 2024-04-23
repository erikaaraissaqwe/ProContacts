package com.silvaerika.procontacts.model.professional;

import lombok.Getter;

@Getter
public enum ProfessionalPosition {
    DEV("Desenvolvedor"),
    DESIGNER("Designer"),
    SUPPORT("Suporte"),
    QA("Tester");

    private final String value;

    ProfessionalPosition(String value) {
        this.value = value;
    }
}
