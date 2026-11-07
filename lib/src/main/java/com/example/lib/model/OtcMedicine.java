package com.example.lib.model;

public class OtcMedicine extends Medicine {
    private int minAge;

    public OtcMedicine(String id, String name, String activeSubstance, double basePrice, FormRelease form, int minAge) {
        super(id, name, activeSubstance, basePrice, form);
        this.minAge = minAge;
    }

    @Override
    public MedicineType getType() { return MedicineType.OTC; }

    public int getMinAge() { return minAge; }
}

