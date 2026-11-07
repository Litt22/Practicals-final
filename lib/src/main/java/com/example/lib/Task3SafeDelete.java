package com.example.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class Task3SafeDelete {
    public static void main(String[] args) {
        System.out.println("зданаие 3-----------------");

        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            numbers.add(i);
        }

        Iterator<Integer> numIterator = numbers.iterator();
        while (numIterator.hasNext()) {
            if (numIterator.next() % 2 == 0) {
                numIterator.remove();
            }
        }
        System.out.println("Список после удаления чётных чисел: " + numbers);

        ArrayList<String> names = new ArrayList<>(Arrays.asList("Anna", "Alex", "Bob", "Max", "Jan", "Eliza"));

        Iterator<String> nameIterator = names.iterator();
        while (nameIterator.hasNext()) {
            if (nameIterator.next().length() <= 4) {
                nameIterator.remove();
            }
        }
        System.out.println("Список после удаления коротких имён: " + names);

        System.out.println("\nошибка - ConcurrentModificationException");
        ArrayList<String> testList = new ArrayList<>(Arrays.asList("One", "Two", "Three"));
        try {
            for (String str : testList) {
                if (str.equals("Two")) {
                    testList.remove(str);
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Исключение");
            /*
              Цикл for-each под капотом использует скрытый итератор. Когда мы напрямую вызываем
              testList.remove(), внутренний счётчик модификаций списка изменяется,
              а итератор об этом не знает. На следующем шаге итератор видит нестыковку
              и выбрасывает ConcurrentModificationException.
            */
        }

        ArrayList<String> modernNamesList = new ArrayList<>(Arrays.asList("Anna", "Alex", "Bob", "Max", "Jan", "Eliza"));
        modernNamesList.removeIf(name -> name.length() <= 4);
        System.out.println("Результат modernNamesList.removeIf: " + modernNamesList);
    }
}

