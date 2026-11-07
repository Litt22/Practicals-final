package com.example.lib.model;

import com.example.lib.exception.InvalidInputException;

public abstract class Medicine {
    private String id;
    private String name;
    private String activeSubstance;
    private double basePrice;
    private FormRelease form;

    public Medicine(String id, String name, String activeSubstance, double basePrice, FormRelease form) {
        setId(id);
        setName(name);
        setActiveSubstance(activeSubstance);
        setBasePrice(basePrice);
        this.form = form;
    }

    public abstract MedicineType getType();

    public String getId() { return id; }
    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) throw new InvalidInputException("ID не может быть пустым");
        this.id = id;
    }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new InvalidInputException("Имя не может быть пустым");
        this.name = name;
    }

    public String getActiveSubstance() { return activeSubstance; }
    public void setActiveSubstance(String activeSubstance) {
        if (activeSubstance == null || activeSubstance.trim().isEmpty()) this.activeSubstance = "Нет";
        else this.activeSubstance = activeSubstance;
    }

    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) {
        if (basePrice < 0) throw new InvalidInputException("Цена не может быть отрицательной");
        this.basePrice = basePrice;
    }

    public FormRelease getForm() { return form; }
    public void setForm(FormRelease form) { this.form = form; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Medicine medicine = (Medicine) obj;
        return id.equals(medicine.id);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (ДВ: %s) - Форма: %s, Цена: %.2f руб.",
                getType().getDescription(), name, activeSubstance, form.getName(), basePrice);
    }
}

