package com.example.lib.ui;

import com.example.lib.exception.ExpiredBatchException;
import com.example.lib.exception.PrescriptionRequiredException;
import com.example.lib.model.CustomComparator;
import com.example.lib.model.Medicine;
import com.example.lib.service.PharmacyService;

import java.time.LocalDate;
import java.util.Scanner;


public class ConsoleUI {
    private PharmacyService service;
    private Scanner scanner;
    private LocalDate currentSystemDate;

    public ConsoleUI(PharmacyService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
        this.currentSystemDate = LocalDate.of(2026, 10, 19); // Текущая системная дата
    }

    public void start() {
        while (true) {
            System.out.println("\n=== ГЛАВНОЕ МЕНЮ АПТЕКИ ===");
            System.out.println("1. Показать весь каталог лекарств");
            System.out.println("2. Сортировать каталог");
            System.out.println("3. Оформить продажу лекарства");
            System.out.println("4. Проверить остатки партий на складе");
            System.out.println("5. Аналитические отчеты");
            System.out.println("0. Выход");
            System.out.print("Выберите опцию: ");

            String choice = scanner.nextLine();
            try {
                switch (choice) {
                    case "1":
                        showCatalog();
                        break;
                    case "2":
                        sortCatalogMenu();
                        break;
                    case "3":
                        handleSale();
                        break;
                    case "4":
                        showBatches();
                        break;
                    case "5":
                        reportsMenu();
                        break;
                    case "0":
                        System.out.println("Завершение работы программы.");
                        return;
                    default:
                        System.out.println("Неверный ввод. Попробуйте еще раз.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("\n[Критическая ошибка бизнес-логики]: " + e.getMessage());
            }
        }
    }

    private void showCatalog() {
        System.out.println("\n--- Каталог медикаментов ---");
        for (int i = 0; i < service.getMedicines().size(); i++) {
            System.out.println(service.getMedicines().get(i));
        }
    }

    private void sortCatalogMenu() {
        System.out.println("\nВарианты сортировки:");
        System.out.println("1. Сортировка по Базовой Цене (по возрастанию)");
        System.out.println("2. Сортировка по Алфавиту Названия");
        System.out.print("Выберите вариант: ");
        String sortChoice = scanner.nextLine();

        if (sortChoice.equals("1")) {
            service.getMedicines().sort(new CustomComparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    return Double.compare(((Medicine) o1).getBasePrice(), ((Medicine) o2).getBasePrice());
                }
            });
            System.out.println("Каталог успешно отсортирован по цене!");
        } else if (sortChoice.equals("2")) {
            service.getMedicines().sort(new CustomComparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    return ((Medicine) o1).getName().compareTo(((Medicine) o2).getName());
                }
            });
            System.out.println("Каталог успешно отсортирован по названию!");
        } else {
            System.out.println("Неверный выбор.");
        }
    }

    private void handleSale() {
        System.out.print("Введите ID лекарства для покупки: ");
        String medId = scanner.nextLine();
        System.out.print("Введите количество: ");
        int qty;
        try {
            qty = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Введено некорректное число.");
            return;
        }

        System.out.print("Введите ID рецепта (если требуется, иначе нажмите Enter): ");
        String prescId = scanner.nextLine();

        try {
            service.processSale(medId, qty, prescId, currentSystemDate);
            System.out.println("Операция успешно проведена!");
        } catch (PrescriptionRequiredException e) {
            System.out.println("Ошибка рецепта: " + e.getMessage());
        } catch (ExpiredBatchException e) {
            System.out.println("Блокировка партии: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void showBatches() {
        System.out.println("\n--- Наличие партий на складах аптеки ---");
        for (int i = 0; i < service.getBatches().size(); i++) {
            System.out.println(service.getBatches().get(i));
        }
    }

    private void reportsMenu() {
        System.out.println("\n--- Меню аналитики ---");
        System.out.println("1. Финансовый отчет по категориям товаров");
        System.out.println("2. Сводка просроченных партий складов");
        System.out.println("3. Рейтинг востребованности (Топ продаж)");
        System.out.print("Выберите отчет: ");
        String reportChoice = scanner.nextLine();

        switch (reportChoice) {
            case "1":
                service.printSalesByCategoryReport();
                break;
            case "2":
                service.printExpiredBatchesReport(currentSystemDate);
                break;
            case "3":
                service.printTopSellingReport();
                break;
            default:
                System.out.println("Неверный вариант.");
                break;
        }
    }
}

