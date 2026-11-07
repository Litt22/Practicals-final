package com.example.lib.model;

public class Supplement extends Medicine {
    private String naturalSource;

    public Supplement(String id, String name, String activeSubstance, double basePrice, FormRelease form, String naturalSource) {
        super(id, name, activeSubstance, basePrice, form);
        this.naturalSource = naturalSource;
    }

    @Override
    public MedicineType getType() { return MedicineType.SUPPLEMENT; }

    public String getNaturalSource() { return naturalSource; }
}
