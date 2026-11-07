package com.example.lib.storage;

import com.example.lib.model.CustomComparator;
import com.example.lib.model.FormRelease;
import com.example.lib.model.Medicine;
import com.example.lib.model.MedicineType;
import com.example.lib.model.OtcMedicine;
import com.example.lib.model.PrescriptionMedicine;
import com.example.lib.model.Supplement;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;


public class CustomStorage {
    private Object[] elements;
    private int size;

    public CustomStorage() {
        elements = new Object[10];
        size = 0;
    }

    public void add(Object element) {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
        elements[size++] = element;
    }

    public int size() { return size; }

    public Object get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return elements[index];
    }

    public void sort(CustomComparator comparator) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (comparator.compare(elements[j], elements[j + 1]) > 0) {
                    Object temp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                }
            }
        }
    }

    public void saveMedicinesToFile(String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (int i = 0; i < size; i++) {
                if (elements[i] instanceof Medicine) {
                    Medicine m = (Medicine) elements[i];
                    String extra = "";
                    if (m instanceof PrescriptionMedicine) extra = ((PrescriptionMedicine) m).getDangerousCode();
                    else if (m instanceof OtcMedicine) extra = String.valueOf(((OtcMedicine) m).getMinAge());
                    else if (m instanceof Supplement) extra = ((Supplement) m).getNaturalSource();

                    writer.printf("%s;%s;%s;%s;%.2f;%s;%s\n",
                            m.getType().name(), m.getId(), m.getName(), m.getActiveSubstance(), m.getBasePrice(), m.getForm().name(), extra);
                }
            }
        }
    }

    public void loadMedicinesFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] parts = line.split(";");
                    if (parts.length < 6) continue; // Поврежденная строка

                    MedicineType type = MedicineType.valueOf(parts[0]);
                    String id = parts[1];
                    String name = parts[2];
                    String substance = parts[3];
                    double price = Double.parseDouble(parts[4].replace(",", "."));
                    FormRelease form = FormRelease.valueOf(parts[5]);

                    if (type == MedicineType.PRESCRIPTION) {
                        add(new PrescriptionMedicine(id, name, substance, price, form, parts.length > 6 ? parts[6] : "COMMON"));
                    } else if (type == MedicineType.OTC) {
                        int age = parts.length > 6 ? Integer.parseInt(parts[6]) : 0;
                        add(new OtcMedicine(id, name, substance, price, form, age));
                    } else if (type == MedicineType.SUPPLEMENT) {
                        add(new Supplement(id, name, substance, price, form, parts.length > 6 ? parts[6] : "Organic"));
                    }
                } catch (Exception e) {
                    System.err.println("[Предупреждение] Найдена поврежденная строка в базе: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла данных: " + e.getMessage());
        }
    }
}

