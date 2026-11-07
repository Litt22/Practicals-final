package com.example.lib.model;

public enum FormRelease {
    TABLET("Таблетки"),
    CAPSULE("Капсулы"),
    LIQUID("Раствор / Сироп"),
    OINTMENT("Мазь");

    private final String name;

    FormRelease(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

