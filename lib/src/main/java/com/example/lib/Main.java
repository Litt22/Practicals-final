package com.example.lib;

import java.util.Scanner;

public class Main {
    private static Pharmacy pharmacy = new Pharmacy();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initTestData();

        while (true) {
            printMenu();
            int choice = readIntChoice();

            try {
                switch (choice) {
                    case 1:
                        showAllMedicinesPolymorphic();
                        break;
                    case 2:
                        sellMedicineMenu();
                        break;
                    case 3:
                        searchAnalogsMenu();
                        break;
                    case 4:
                        checkExpiredMenu();
                        break;
                    case 5:
                        addNewMedicineMenu();
                        break;
                    case 0:
                        System.out.println("Vyhod iz programmy. Do svidanija!");
                        return;
                    default:
                        System.out.println("Nevernyj punkt menu. Poprobujte snova.");
                }
            } catch (PharmacyException e) {
                System.out.println("\n[OSHIBKA APTEKI]: " + e.getMessage());
            }
            System.out.println("\nNazhmite Enter dlja prodolzhenija...");
            scanner.nextLine();
        }
    }

    private static void initTestData() {
        try {
            // Норм лекрства
            pharmacy.addMedicine(new OverTheCounterMedicine(1, "Paracetamol", "Paracetamol", 50.0, "2028-12-01", 100));
            pharmacy.addMedicine(new PrescriptionMedicine(2, "Amoxicillin", "Amoxicillin", 250.0, "2027-05-10", 30));
            pharmacy.addMedicine(new OverTheCounterMedicine(3, "Panadol", "Paracetamol", 120.0, "2029-01-01", 20));

            // просроченые
            pharmacy.addMedicine(new OverTheCounterMedicine(4, "Aspirin-Old", "Aspirin", 30.0, "2025-01-01", 5));


            pharmacy.addPrescription(new Prescription(101, "Ivanov I.I.", 2));
        } catch (PharmacyException e) {
            System.out.println("Oshibka init: " + e.getMessage());
        }
    }

    private static void printMenu() {
        System.out.println("\n------APTEKA UPRAVLENIE");
        System.out.println("1. Pokazat ves assortiment");
        System.out.println("2. Prodat lekarstvo");
        System.out.println("3. Pojisk analogov po dejstvujushemu veshestvu");
        System.out.println("4. Kontrol sroka godnosti");
        System.out.println("5. Dobavit novoe lekarstvo");
        System.out.println("0. Vyhod");
        System.out.print("Vyberite dejstvie: ");
    }

    private static int readIntChoice() {
        while (true) {
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Oshibka! Vvedite korrektnoe chislo: ");
            }
        }
    }

    private static double readDoubleInput() {
        while (true) {
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Oshibka! Vvedite chislo s tochkoj: ");
            }
        }
    }

    private static void showAllMedicinesPolymorphic() {
        System.out.println("\nAssortiment apteki");
        Medicine[] meds = pharmacy.getAllMedicines();
        for (Medicine m : meds) {
            System.out.println(m.toString() + " | Kat: " + m.getCategoryInfo());
        }
    }

    private static void sellMedicineMenu() throws PharmacyException {
        System.out.print("Vvedite ID lekarstva: ");
        int medId = readIntChoice();
        System.out.print("Vvedite kolichestvo: ");
        int qty = readIntChoice();

        Medicine med = pharmacy.findMedicineById(medId);
        int prescId = -1;
        if (med instanceof PrescriptionMedicine) {
            System.out.println("Vnimanie: Eto lekarstvo otpuskaetsja tolko po receptu!");
            System.out.print("Vvedite ID recepta: ");
            prescId = readIntChoice();
        }

        pharmacy.sellMedicine(medId, qty, prescId);
        System.out.println("Prodazha uspeshno sovershena!");
    }

    private static void searchAnalogsMenu() {
        System.out.print("Vvedite dejstvujushee veshestvo (naprimer, Paracetamol): ");
        String substance = scanner.nextLine();
        Medicine[] analogs = pharmacy.findAnalogs(substance);

        System.out.println("\nNajdennye analogi");
        if (analogs.length == 0) {
            System.out.println("Analogov ne najdeno.");
        } else {
            for (Medicine m : analogs) System.out.println(m);
        }
    }

    private static void checkExpiredMenu() {
        String currentDate = "2026-10-13";
        Medicine[] expired = pharmacy.getExpiredMedicines(currentDate);

        System.out.println("\nVnimanie! Prosrochennye tovary");
        if (expired.length == 0) {
            System.out.println("Prosrochennyh tovarov net. Vse v porjadke.");
        } else {
            for (Medicine m : expired) {
                System.out.println(m.toString() + " -> SROK ISTEK!");
            }
        }
    }

    private static void addNewMedicineMenu() throws PharmacyException {
        System.out.print("Vvedite ID: ");
        int id = readIntChoice();
        System.out.print("Vvedite nazvanie: ");
        String name = scanner.nextLine();
        System.out.print("Vvedite dejstvujushee veshestvo: ");
        String sub = scanner.nextLine();
        System.out.print("Vvedite cenu: ");
        double price = readDoubleInput();
        System.out.print("Vvedite srok godnosti (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Vvedite kolichestvo na sklade: ");
        int stock = readIntChoice();

        System.out.println("Tip lekarstva: 1 - Bezrecepturnoe, 2 - Recepturnoe");
        int type = readIntChoice();

        if (type == 1) {
            pharmacy.addMedicine(new OverTheCounterMedicine(id, name, sub, price, date, stock));
        } else {
            pharmacy.addMedicine(new PrescriptionMedicine(id, name, sub, price, date, stock));
        }
        System.out.println("Lekarstvo uspeshno dobavleno!");
    }
}

