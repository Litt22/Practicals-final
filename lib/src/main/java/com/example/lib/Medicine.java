package com.example.lib;

public abstract class Medicine {
    private int id;
    private String name;
    private String activeSubstance;
    private double price;
    private String expiryDate;
    private int stock;

    public Medicine(int id, String name, String activeSubstance, double price, String expiryDate, int stock) {
        this.id = id;
        this.name = name;
        this.activeSubstance = activeSubstance;
        this.price = price;
        this.expiryDate = expiryDate;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getActiveSubstance() {
        return activeSubstance;
    }

    public double getPrice() {
        return price;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Medicine medicine = (Medicine) obj;
        return id == medicine.id;
    }

    public abstract String getCategoryInfo();
}