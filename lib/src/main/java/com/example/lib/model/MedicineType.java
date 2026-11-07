package com.example.lib.model;

public enum MedicineType {
    PRESCRIPTION("Рецептурный препарат"),
    OTC("Безрецептурный препарат"),
    SUPPLEMENT("БАД");

    private final String description;

    MedicineType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

