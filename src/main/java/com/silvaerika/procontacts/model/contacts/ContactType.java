package com.silvaerika.procontacts.model.contacts;

public enum ContactType {
    LANDLINE("Fixo"),
    HOME("Casa"),
    CELLPHONE("Celular"),
    OFFICE("Escritório");

    private final String value;

    ContactType(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
