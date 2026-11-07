package com.example.lib;

import java.util.Arrays;
import java.util.Random;

public class Task1Arrays {
    public static void main(String[] args) {
        System.out.println("задание 1------------------------");
        Random random = new Random();

        int[] numbers = new int[15];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(101) - 50; // [-50, 50]
        }

        System.out.println("исходный массив: " + Arrays.toString(numbers));

        if (numbers.length == 0) {
            System.out.println("массив пуст.");
            return;
        }

        int min = numbers[0];
        int max = numbers[0];
        int sum = 0;

        int positiveCount = 0;
        int negativeCount = 0;
        int zeroCount = 0;

        for (int num : numbers) {
            if (num < min) min = num;
            if (num > max) max = num;
            sum += num;

            if (num > 0) positiveCount++;
            else if (num < 0) negativeCount++;
            else zeroCount++;
        }

        double average = (double) sum / numbers.length;

        System.out.println("минимум: " + min);
        System.out.println("максимум: " + max);
        System.out.println("сумма: " + sum);
        System.out.println("среднее арифметическое: " + average);
        System.out.println("положительных: " + positiveCount + ", отрицательных: " + negativeCount + ", нулей: " + zeroCount);

        int[] positiveNumbers = new int[positiveCount];
        int index = 0;
        for (int num : numbers) {
            if (num > 0) {
                positiveNumbers[index++] = num;
            }
        }
        System.out.println("массив только с положительными: " + Arrays.toString(positiveNumbers));

        Arrays.sort(numbers);
        System.out.println("отсортированный исходный массив: " + Arrays.toString(numbers));

        System.out.println("\nдвумерный массив 4x5:");
        int[][] matrix = new int[4][5];
        for (int i = 0; i < matrix.length; i++) {
            int rowSum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = random.nextInt(10); // 0-9
                System.out.print(matrix[i][j] + "\t");
                rowSum += matrix[i][j];
            }
            System.out.println(" сумма строки: " + rowSum);
        }
    }
}

