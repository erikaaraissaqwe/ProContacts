package com.silvaerika.procontacts.model.professional;

public enum ProfessionalPosition {
    DEV("Desenvolvedor"),
    DESIGNER("Designer"),
    SUPPORT("Suporte"),
    QA("Tester");

    private final String value;

    ProfessionalPosition(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
