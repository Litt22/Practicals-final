package com.example.lib.service;

import com.example.lib.exception.ExpiredBatchException;
import com.example.lib.exception.InvalidInputException;
import com.example.lib.exception.PrescriptionRequiredException;
import com.example.lib.model.Batch;
import com.example.lib.model.Medicine;
import com.example.lib.model.MedicineType;
import com.example.lib.model.Prescription;
import com.example.lib.model.Sale;
import com.example.lib.storage.CustomStorage;

import java.time.LocalDate;
import java.time.LocalDateTime;



public class PharmacyService {
    private CustomStorage medicines = new CustomStorage();
    private CustomStorage batches = new CustomStorage();
    private CustomStorage prescriptions = new CustomStorage();
    private CustomStorage sales = new CustomStorage();

    public CustomStorage getMedicines() { return medicines; }
    public CustomStorage getBatches() { return batches; }
    public CustomStorage getPrescriptions() { return prescriptions; }
    public CustomStorage getSales() { return sales; }


    public void addBatch(String batchNumber, String medicineId, int quantity, LocalDate expiryDate) {
        if (findMedicineById(medicineId) == null) {
            throw new InvalidInputException("Препарат с ID " + medicineId + " не зарегистрирован в каталоге!");
        }
        Batch batch = new Batch(batchNumber, medicineId, quantity, expiryDate);
        batches.add(batch);
        Logger.log("Принята партия: " + batchNumber + " для лекарства ID: " + medicineId);
    }

    public Medicine findMedicineById(String id) {
        for (int i = 0; i < medicines.size(); i++) {
            Medicine m = (Medicine) medicines.get(i);
            if (m.getId().equals(id)) return m;
        }
        return null;
    }


    public void processSale(String medicineId, int qty, String prescriptionId, LocalDate currentDate) throws PrescriptionRequiredException {
        Medicine med = findMedicineById(medicineId);
        if (med == null) throw new InvalidInputException("Лекарство не найдено.");

        if (med.getType() == MedicineType.PRESCRIPTION) {
            if (prescriptionId == null || prescriptionId.trim().isEmpty()) {
                throw new PrescriptionRequiredException("Для продажи " + med.getName() + " строго требуется рецепт!");
            }
            Prescription presc = findPrescriptionById(prescriptionId);
            if (presc == null || !presc.getMedicineId().equals(medicineId) || !presc.isValid(currentDate)) {
                throw new PrescriptionRequiredException("Рецепт не найден, не подходит к препарату или истекли 60 дней!");
            }
            presc.setUsed(true);
        }

        if (qty > 10) {
            throw new InvalidInputException("Ограничение: нельзя продать более 10 единиц в одни руки!");
        }

        for (int i = 0; i < batches.size(); i++) {
            Batch b = (Batch) batches.get(i);
            if (b.getMedicineId().equals(medicineId) && b.getQuantity() > 0) {
                if (b.getExpiryDate().isBefore(currentDate)) {
                    throw new ExpiredBatchException("Обнаружена просроченная партия " + b.getBatchNumber() + "! Продажа заблокирована.");
                }
            }
        }

        int remainingToDecrease = qty;
        for (int i = 0; i < batches.size(); i++) {
            Batch b = (Batch) batches.get(i);
            if (b.getMedicineId().equals(medicineId) && b.getQuantity() > 0) {
                int available = b.getQuantity();
                if (available >= remainingToDecrease) {
                    b.setQuantity(available - remainingToDecrease);
                    remainingToDecrease = 0;
                    break;
                } else {
                    remainingToDecrease -= available;
                    b.setQuantity(0);
                }
            }
        }

        if (remainingToDecrease > 0) {
            Logger.log("Нехватка препарата " + med.getName() + ". Запуск поиска аналогов.");
            Medicine alternative = findCheaperAlternative(med.getActiveSubstance(), med.getBasePrice());
            if (alternative != null) {
                throw new InvalidInputException("Недостаточно товара на складе! Предложите клиенту дешевый аналог: " +
                        alternative.getName() + " (Цена: " + alternative.getBasePrice() + " руб.)");
            }
            throw new InvalidInputException("Товара недостаточно на складе, и аналогов нет!");
        }

        double total = med.getBasePrice() * qty;
        Sale sale = new Sale("S" + (sales.size() + 1), medicineId, qty, total, LocalDateTime.now());
        sales.add(sale);
        Logger.log("Успешная продажа: " + med.getName() + " x" + qty + " на сумму " + total + " руб.");
    }

    private Prescription findPrescriptionById(String id) {
        for (int i = 0; i < prescriptions.size(); i++) {
            Prescription p = (Prescription) prescriptions.get(i);
            if (p.getPrescriptionId().equals(id)) return p;
        }
        return null;
    }

    public Medicine findCheaperAlternative(String activeSubstance, double currentPrice) {
        Medicine bestAlternative = null;
        for (int i = 0; i < medicines.size(); i++) {
            Medicine m = (Medicine) medicines.get(i);
            if (m.getActiveSubstance().equalsIgnoreCase(activeSubstance) && m.getBasePrice() < currentPrice) {
                if (bestAlternative == null || m.getBasePrice() < bestAlternative.getBasePrice()) {
                    bestAlternative = m;
                }
            }
        }
        return bestAlternative;
    }

    public void printSalesByCategoryReport() {
        double rxTotal = 0, otcTotal = 0, suppTotal = 0;
        for (int i = 0; i < sales.size(); i++) {
            Sale s = (Sale) sales.get(i);
            Medicine m = findMedicineById(s.getMedicineId());
            if (m != null) {
                switch (m.getType()) {
                    case PRESCRIPTION:
                        rxTotal += s.getTotalPrice();
                        break;
                    case OTC:
                        otcTotal += s.getTotalPrice();
                        break;
                    case SUPPLEMENT:
                        suppTotal += s.getTotalPrice();
                        break;
                }
            }
        }
        System.out.println("\n=== ОТЧЕТ ПО ПРОДАЖАМ ПО КАТЕГОРИЯМ ===");
        System.out.println("Рецептурные препараты: " + rxTotal + " руб.");
        System.out.println("Безрецептурные препараты: " + otcTotal + " руб.");
        System.out.println("БАДы: " + suppTotal + " руб.");
        System.out.println("=======================================");
    }

    public void printExpiredBatchesReport(LocalDate currentDate) {
        System.out.println("\n=== СПИСОК ПРОСРОЧЕННЫХ ПАРТИЙ ===");
        int count = 0;
        for (int i = 0; i < batches.size(); i++) {
            Batch b = (Batch) batches.get(i);
            if (b.getExpiryDate().isBefore(currentDate)) {
                System.out.println("Партия № " + b.getBatchNumber() + " (Препарат ID: " + b.getMedicineId() + ") - Срок истек: " + b.getExpiryDate());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Просроченных партий на складе нет.");
        }
        System.out.println("==================================");
    }

    public void printTopSellingReport() {
        System.out.println("\n=== РЕЙТИНГ ПРОДАЖ ПРЕПАРАТОВ ===");
        int count = 0;
        for (int i = 0; i < medicines.size(); i++) {
            Medicine m = (Medicine) medicines.get(i);
            int totalQty = 0;
            for (int j = 0; j < sales.size(); j++) {
                Sale s = (Sale) sales.get(j);
                if (s.getMedicineId().equals(m.getId())) {
                    totalQty += s.getQuantity();
                }
            }
            if (totalQty > 0) {
                System.out.println("Препарат: " + m.getName() + " | Продано: " + totalQty + " шт.");
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Продаж пока не было.");
        }
        System.out.println("=================================");
    }
}

