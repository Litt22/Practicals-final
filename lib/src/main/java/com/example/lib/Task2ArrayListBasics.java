package com.example.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Task2ArrayListBasics {
    public static void main(String[] args) {
        System.out.println("Задание 2 --------------");

        ArrayList<String> programmingLanguages = new ArrayList<>();
        programmingLanguages.add("Java");
        programmingLanguages.add("Python");
        programmingLanguages.add("C++");
        programmingLanguages.add("JavaScript");
        programmingLanguages.add("C#");
        programmingLanguages.add("Go");
        programmingLanguages.add("Rust");
        programmingLanguages.add("Kotlin");
        programmingLanguages.add("Swift");
        programmingLanguages.add("Ruby");

        System.out.println(" 1способ:");
        for (int i = 0; i < programmingLanguages.size(); i++) {
            System.out.print(programmingLanguages.get(i) + " ");
        }

        System.out.println("\n 2способ:");
        for (String lang : programmingLanguages) {
            System.out.print(lang + " ");
        }

        System.out.println("\n 3способ:");
        Iterator<String> iterator = programmingLanguages.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        programmingLanguages.add(0, "PHP");
        int middleIndex = programmingLanguages.size() / 2;
        programmingLanguages.add(middleIndex, "TypeScript");
        programmingLanguages.add("Scala");
        System.out.println("После добавлений: " + programmingLanguages);

        if (programmingLanguages.size() > 3) {
            programmingLanguages.set(3, "Dart");
            System.out.println("после замены индекс 3: " + programmingLanguages);
        }

        String searchTarget = "Java";
        System.out.println("Содержит '" + searchTarget + "'? " + programmingLanguages.contains(searchTarget));
        System.out.println("Индекс первого вхождения '" + searchTarget + "': " + programmingLanguages.indexOf(searchTarget));

        if (!programmingLanguages.isEmpty()) {
            programmingLanguages.remove(0);
        }
        programmingLanguages.remove("Ruby");
        System.out.println("После удалений: " + programmingLanguages);

        System.out.println("Размер списка: " + programmingLanguages.size());
        System.out.println("список пуст? " + programmingLanguages.isEmpty());

        String[] languagesArray = programmingLanguages.toArray(new String[0]);
        System.out.println("Полученный массив: " + Arrays.toString(languagesArray));
    }
}

