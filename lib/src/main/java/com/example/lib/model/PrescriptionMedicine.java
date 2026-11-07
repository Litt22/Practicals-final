package com.example.lib.model;

public class PrescriptionMedicine extends Medicine {
    private String dangerousCode;

    public PrescriptionMedicine(String id, String name, String activeSubstance, double basePrice, FormRelease form, String dangerousCode) {
        super(id, name, activeSubstance, basePrice, form);
        this.dangerousCode = dangerousCode;
    }

    @Override
    public MedicineType getType() { return MedicineType.PRESCRIPTION; }

    public String getDangerousCode() { return dangerousCode; }
}

