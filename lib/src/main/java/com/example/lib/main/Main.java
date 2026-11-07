package com.example.lib.main;

import com.example.lib.model.FormRelease;
import com.example.lib.model.OtcMedicine;
import com.example.lib.model.Prescription;
import com.example.lib.model.PrescriptionMedicine;
import com.example.lib.model.Supplement;
import com.example.lib.service.PharmacyService;
import com.example.lib.ui.ConsoleUI;

import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {

        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
            System.setErr(new PrintStream(System.err, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            System.err.println("Ошибка настройки кодировки консоли");
        }

        PharmacyService pharmacy = new PharmacyService();

        pharmacy.getMedicines().loadMedicinesFromFile("medicines.csv");

        if (pharmacy.getMedicines().size() == 0) {
            System.out.println("Локальная база пуста. Генерация демонстрационных данных...");

            pharmacy.getMedicines().add(new PrescriptionMedicine("M1", "Амоксициллин", "Амоксициллин", 350.0, FormRelease.CAPSULE, "ANTIBIOTIC"));
            pharmacy.getMedicines().add(new PrescriptionMedicine("M2", "Каптоприл", "Каптоприл", 120.5, FormRelease.TABLET, "HEART"));
            pharmacy.getMedicines().add(new PrescriptionMedicine("M3", "Ибупрофен Форте", "Ибупрофен", 240.0, FormRelease.TABLET, "COMMON"));

            pharmacy.getMedicines().add(new OtcMedicine("M4", "Парацетамол Детский", "Парацетамол", 80.0, FormRelease.LIQUID, 3));
            pharmacy.getMedicines().add(new OtcMedicine("M5", "Анальгин", "Метамизол натрия", 50.0, FormRelease.TABLET, 12));
            pharmacy.getMedicines().add(new OtcMedicine("M6", "Ксилометазолин Спрей", "Ксилометазолин", 180.0, FormRelease.LIQUID, 6));

            pharmacy.getMedicines().add(new Supplement("M7", "Витамин С 1000", "Аскорбиновая кислота", 450.0, FormRelease.TABLET, "Цитрусовые"));
            pharmacy.getMedicines().add(new Supplement("M8", "Омега-3 премиум", "Рыбий жир", 1200.0, FormRelease.CAPSULE, "Морская рыба"));
            pharmacy.getMedicines().add(new Supplement("M9", "Валериана Экстракт", "Валериана", 95.0, FormRelease.TABLET, "Травяной"));

            try {
                pharmacy.getMedicines().saveMedicinesToFile("medicines.csv");
            } catch (IOException e) {
                System.err.println("Не удалось записать демо-данные в файл: " + e.getMessage());
            }
        }

        pharmacy.addBatch("B26-01", "M1", 50, LocalDate.of(2027, 5, 20));
        pharmacy.addBatch("B26-02", "M2", 100, LocalDate.of(2028, 11, 1));
        pharmacy.addBatch("B25-OLD", "M3", 10, LocalDate.of(2025, 1, 1));
        pharmacy.addBatch("B26-04", "M4", 30, LocalDate.of(2027, 8, 15));
        pharmacy.addBatch("B26-07", "M7", 40, LocalDate.of(2027, 3, 10));

        pharmacy.getPrescriptions().add(new Prescription("REC-777", "Иванов И.И.", "M1", LocalDate.of(2026, 9, 15), false)); // Действителен
        pharmacy.getPrescriptions().add(new Prescription("REC-OLD", "Петров П.П.", "M2", LocalDate.of(2025, 5, 10), false));  // Просрочен (>60 дней)

        ConsoleUI ui = new ConsoleUI(pharmacy);
        ui.start();
    }
}
