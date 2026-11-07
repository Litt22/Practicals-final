package com.example.lib;

import java.util.Arrays;

public class Pharmacy {
    private Medicine[] medicines;
    private int medCount;

    private Prescription[] prescriptions;
    private int prescCount;

    public Pharmacy() {
        medicines = new Medicine[4];
        prescriptions = new Prescription[4];
        medCount = 0;
        prescCount = 0;
    }

    private void ensureMedCapacity() {
        if (medCount == medicines.length) {
            medicines = Arrays.copyOf(medicines, medicines.length * 2);
        }
    }

    private void ensurePrescCapacity() {
        if (prescCount == prescriptions.length) {
            prescriptions = Arrays.copyOf(prescriptions, prescriptions.length * 2);
        }
    }

    public void addMedicine(Medicine med) throws PharmacyException {
        if (findMedicineById(med.getId()) != null) {
            throw new PharmacyException("Lekarstvo s ID " + med.getId() + " uzhe sushestvuet.");
        }
        ensureMedCapacity();
        medicines[medCount++] = med;
    }

    public void addPrescription(Prescription presc) throws PharmacyException {
        for (int i = 0; i < prescCount; i++) {
            if (prescriptions[i].getId() == presc.getId()) {
                throw new PharmacyException("Recept s ID " + presc.getId() + " uzhe sushestvuet.");
            }
        }
        ensurePrescCapacity();
        prescriptions[prescCount++] = presc;
    }

    public Medicine findMedicineById(int id) {
        for (int i = 0; i < medCount; i++) {
            if (medicines[i].getId() == id) return medicines[i];
        }
        return null;
    }

    public Prescription findPrescriptionById(int id) {
        for (int i = 0; i < prescCount; i++) {
            if (prescriptions[i].getId() == id) return prescriptions[i];
        }
        return null;
    }

    // продажа с проверкой рецепта
    public void sellMedicine(int medId, int qty, int prescId) throws PharmacyException {
        Medicine med = findMedicineById(medId);
        if (med == null) throw new PharmacyException("Lekarstvo ne najdeno.");
        if (med.getStock() < qty) throw new PharmacyException("Nedostatochno tovara na sklade. Ostatok: " + med.getStock());

        if (med.getExpiryDate().compareTo("2026-10-13") < 0) {
            throw new PharmacyException("PRODAZHA ZAPRESHENA: Srok godnosti lekarstva istek (" + med.getExpiryDate() + ")!");
        }

        if (med instanceof PrescriptionMedicine) {
            Prescription presc = findPrescriptionById(prescId);
            if (presc == null) throw new PharmacyException("Dlaj etogo lekarstva nuzhen recept! Recept s ID " + prescId + " ne najden.");
            if (presc.getMedicineId() != medId) throw new PharmacyException("Etot recept vypisan dlja drugogo lekarstva.");
            if (presc.isUsed()) throw new PharmacyException("Etot recept uzhe byl ispolzovan ranee.");

            presc.setUsed(true);
        }

        med.setStock(med.getStock() - qty);
    }

    // поиск аналогов
    public Medicine[] findAnalogs(String substance) {
        int count = 0;
        for (int i = 0; i < medCount; i++) {
            if (medicines[i].getActiveSubstance().equalsIgnoreCase(substance)) count++;
        }

        Medicine[] analogs = new Medicine[count];
        int idx = 0;
        for (int i = 0; i < medCount; i++) {
            if (medicines[i].getActiveSubstance().equalsIgnoreCase(substance)) {
                analogs[idx++] = medicines[i];
            }
        }
        return analogs;
    }

    public Medicine[] getExpiredMedicines(String currentDate) {
        int count = 0;
        for (int i = 0; i < medCount; i++) {
            if (medicines[i].getExpiryDate().compareTo(currentDate) < 0) count++;
        }

        Medicine[] expired = new Medicine[count];
        int idx = 0;
        for (int i = 0; i < medCount; i++) {
            if (medicines[i].getExpiryDate().compareTo(currentDate) < 0) {
                expired[idx++] = medicines[i];
            }
        }
        return expired;
    }

    public Medicine[] getAllMedicines() {
        return Arrays.copyOf(medicines, medCount);
    }
}

