package com.example.lib;

public class OverTheCounterMedicine extends Medicine {
    public OverTheCounterMedicine(int id, String name, String activeSubstance, double price, String expiryDate, int stock) {
        super(id, name, activeSubstance, price, expiryDate, stock);
    }

    @Override
    public String getCategoryInfo() {
        return "Bezrecepturnoe lekarstvo";
    }

    @Override
    public String toString() {
        return "ID: " + getId() + " | " + getName() + " [" + getActiveSubstance() + "] | Cena: " + getPrice() + " | Srok: " + getExpiryDate() + " | Ostatok: " + getStock() + " | (BEZ RECEPTA)";
    }
}

