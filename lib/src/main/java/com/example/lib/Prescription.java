package com.example.lib;

public class Prescription {
    private int id;
    private String patientName;
    private int medicineId;
    private boolean isUsed;

    public Prescription(int id, String patientName, int medicineId) {
        this.id = id;
        this.patientName = patientName;
        this.medicineId = medicineId;
        this.isUsed = false;
    }

    public int getId() { return id; }
    public String getPatientName() { return patientName; }
    public int getMedicineId() { return medicineId; }
    public boolean isUsed() { return isUsed; }

    public void setUsed(boolean used) { isUsed = used; }

    @Override
    public String toString() {
        return "Recept #" + id + " | Pacient: " + patientName + " | ID Lekarstva: " + medicineId + " | Status: " + (isUsed ? "ISPOLZOVAN" : "AKTIVEN");
    }
}
