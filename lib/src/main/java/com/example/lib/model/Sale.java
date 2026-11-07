package com.example.lib.model;

import java.time.LocalDateTime;

public class Sale {
    private String saleId;
    private String medicineId;
    private int quantity;
    private double totalPrice;
    private LocalDateTime dateTime;

    public Sale(String saleId, String medicineId, int quantity, double totalPrice, LocalDateTime dateTime) {
        this.saleId = saleId;
        this.medicineId = medicineId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.dateTime = dateTime;
    }

    public String getSaleId() { return saleId; }
    public String getMedicineId() { return medicineId; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { return totalPrice; }
    public LocalDateTime getDateTime() { return dateTime; }
}

