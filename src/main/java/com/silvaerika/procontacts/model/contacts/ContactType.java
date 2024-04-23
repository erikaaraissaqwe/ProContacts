package com.silvaerika.procontacts.model.contacts;

import lombok.Getter;

@Getter
public enum ContactType {
    LANDLINE("Fixo"),
    HOME("Casa"),
    CELLPHONE("Celular"),
    OFFICE("Escritório");

    private final String value;

    ContactType(String value) {
        this.value = value;
    }
}
