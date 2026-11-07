package com.example.lib;

public class PrescriptionMedicine extends Medicine {
    public PrescriptionMedicine(int id, String name, String activeSubstance, double price, String expiryDate, int stock) {
        super(id, name, activeSubstance, price, expiryDate, stock);
    }

    @Override
    public String getCategoryInfo() {
        return "Recepturnoe lekarstvo (Strogo po receptu)";
    }

    @Override
    public String toString() {
        return "ID: " + getId() + " | " + getName() + " [" + getActiveSubstance() + "] | Cena: " + getPrice() + " | Srok: " + getExpiryDate() + " | Ostatok: " + getStock() + " | (RECEPT)";
    }
}

