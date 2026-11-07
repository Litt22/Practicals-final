package com.example.lib.model;

import java.time.LocalDate;

public class Prescription {
    private String prescriptionId;
    private String patientName;
    private String medicineId;
    private LocalDate issueDate;
    private boolean isUsed;

    public Prescription(String prescriptionId, String patientName, String medicineId, LocalDate issueDate, boolean isUsed) {
        this.prescriptionId = prescriptionId;
        this.patientName = patientName;
        this.medicineId = medicineId;
        this.issueDate = issueDate;
        this.isUsed = isUsed;
    }

    public String getPrescriptionId() { return prescriptionId; }
    public String getPatientName() { return patientName; }
    public String getMedicineId() { return medicineId; }
    public LocalDate getIssueDate() { return issueDate; }

    public boolean isUsed() { return isUsed; }
    public void setUsed(boolean used) { isUsed = used; }

    public boolean isValid(LocalDate currentDate) {
        if (isUsed) return false;
        return !issueDate.plusDays(60).isBefore(currentDate);
    }
}

