package com.example.lib.model;

import com.example.lib.exception.InvalidInputException;

import java.time.LocalDate;

public class Batch {
    private String batchNumber;
    private String medicineId;
    private int quantity;
    private LocalDate expiryDate;

    public Batch(String batchNumber, String medicineId, int quantity, LocalDate expiryDate) {
        this.batchNumber = batchNumber;
        this.medicineId = medicineId;
        setQuantity(quantity);
        this.expiryDate = expiryDate;
    }

    public String getBatchNumber() { return batchNumber; }
    public String getMedicineId() { return medicineId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) {
        if (quantity < 0) throw new InvalidInputException("Количество не может быть меньше 0");
        this.quantity = quantity;
    }

    public LocalDate getExpiryDate() { return expiryDate; }

    @Override
    public String toString() {
        return String.format("Партия: %s | ID Препарата: %s | Кол-во: %d | Срок до: %s",
                batchNumber, medicineId, quantity, expiryDate);
    }
}

