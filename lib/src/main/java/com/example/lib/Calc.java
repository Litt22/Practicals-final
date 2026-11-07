package com.example.lib;

import java.util.Scanner;

public class Calc {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nVvedite operaciyu (+, -, *, /, sq, q ):");
            String operator = scanner.next();

            if (operator.equals("q")) {
                System.out.println("Zavershenie raboty.");
                break;
            }

            if (operator.equals("sq")) {
                System.out.println("Vvedite chislo:");
                double a = scanner.nextDouble();
                printResult(square(a));
                continue;
            }

            System.out.println("Vvedite pervoe chislo:");
            double a = scanner.nextDouble();

            System.out.println("Vvedite vtoroe chislo:");
            double b = scanner.nextDouble();

            calculate(a, b, operator);
        }

        scanner.close();
    }



    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Oshibka: Delenie na 0 nel'zya!");
            return Double.NaN;
        }
        return a / b;
    }

    public static double square(double a) {
        return a * a;
    }

    public static void calculate(double a, double b, String operator) {
        double result = 0;
        boolean hasError = false;

        switch (operator) {
            case "+":
                result = add(a, b);
                break;
            case "-":
                result = subtract(a, b);
                break;
            case "*":
                result = multiply(a, b);
                break;
            case "/":
                result = divide(a, b);
                if (Double.isNaN(result)) {
                    hasError = true;
                }
                break;
            default:
                System.out.println("Oshibka: Neizvestnaya operaciya!");
                hasError = true;
                break;
        }

        if (!hasError) {
            printResult(result);
        }
    }

    public static void printResult(double result) {
        System.out.printf("Rezultat: %.2f\n", result);
    }
}

