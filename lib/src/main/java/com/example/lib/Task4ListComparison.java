package com.example.lib;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Task4ListComparison {
    public static void main(String[] args) {
        System.out.println("задание 4---------");

        int totalElements = 100_000;
        int operationsCount = 5_000;
        Random random = new Random();

        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < totalElements; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        long startTime = System.nanoTime();
        for (int i = 0; i < operationsCount; i++) {
            int randomIndex = random.nextInt(totalElements);
            arrayList.get(randomIndex);
        }
        long durationArrayListGet = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < operationsCount; i++) {
            int randomIndex = random.nextInt(totalElements);
            linkedList.get(randomIndex);
        }
        long durationLinkedListGet = System.nanoTime() - startTime;

        System.out.printf("5 000 операций get(i): ArrayList = %.3f мс | LinkedList = %.3f мс\n",
                durationArrayListGet / 1_000_000.0, durationLinkedListGet / 1_000_000.0);

        startTime = System.nanoTime();
        for (int i = 0; i < operationsCount; i++) {
            arrayList.add(0, i);
        }
        long durationArrayListAdd = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < operationsCount; i++) {
            linkedList.add(0, i);
        }
        long durationLinkedListAdd = System.nanoTime() - startTime;

        System.out.printf("5 000 вставок в начало: ArrayList = %.3f мс | LinkedList = %.3f мс\n",
                durationArrayListAdd / 1_000_000.0, durationLinkedListAdd / 1_000_000.0);

        startTime = System.nanoTime();
        for (int i = 0; i < operationsCount; i++) {
            if (!arrayList.isEmpty()) arrayList.remove(0);
        }
        long durationArrayListRemove = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < operationsCount; i++) {
            if (!linkedList.isEmpty()) linkedList.remove(0);
        }
        long durationLinkedListRemove = System.nanoTime() - startTime;

        System.out.printf("5 000 удалений с начала: ArrayList = %.3f мс | LinkedList = %.3f мс\n",
                durationArrayListRemove / 1_000_000.0, durationLinkedListRemove / 1_000_000.0);

        /*
          Вывод:
          1. get(i) : ArrayList работает за O(1), так как основан на массиве.
             LinkedList работает за O(N), так как для поиска элемента ему приходится перебирать с начала или конца.
             Следовательно, ArrayList значительно быстрее на чтение.
          2. Вставка/удаление в начало: LinkedList делает это за O(1), просто меняя ссылки у соседних узлов.
             ArrayList длеает это за O(N), так как при каждом добавлении/удалении на нулевой индекс ему приходится
             сдвигать все остальные элементы в памяти. Поэтому LinkedList здесь в разы эффективнее.
        */

        System.out.println("\nтестирование кастомного стека");
        SimpleStack<String> stack = new SimpleStack<>();
        stack.push("Первый");
        stack.push("второй");
        stack.push("Третий");

        System.out.println("Элемент на вершине: " + stack.peek());
        System.out.println("извлечение: " + stack.pop());
        System.out.println("элемент на вершине после извлечения: " + stack.peek());
        System.out.println("Стек пуст? " + stack.isEmpty());
    }
}


class SimpleStack<T> {
    private final LinkedList<T> list = new LinkedList<>();

    public void push(T value) {
        list.addFirst(value);
    }

    public T pop() {
        if (list.isEmpty()) {
            return null;
        }
        return list.removeFirst();
    }

    public T peek() {
        return list.peekFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}

