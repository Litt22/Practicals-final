package com.example.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

class Student {
    private final String name;
    private final int age;
    private final double averageGrade;

    public Student(String name, int age, double averageGrade) {
        this.name = name;
        this.age = age;
        this.averageGrade = averageGrade;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public double getAverageGrade() { return averageGrade; }

    public boolean isExcellent() {
        return this.averageGrade >= 4.5;
    }

    @Override
    public String toString() {
        return String.format("Студент{Имя='%s', Возраст=%d, Ср. балл=%.2f, Отличник=%b}", name, age, averageGrade, isExcellent());
    }
}

public class Task5StudentList {
    public static void main(String[] args) {
        System.out.println("задание 5-------------");

        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Иван", 19, 4.8));
        students.add(new Student("Анна", 17, 4.2));
        students.add(new Student("Сергей", 20, 4.6));
        students.add(new Student("Мария", 18, 3.9));
        students.add(new Student("Олег", 16, 4.9));
        students.add(new Student("Елена", 22, 4.5));
        students.add(new Student("Дмитрий", 21, 3.5));
        students.add(new Student("Наталья", 17, 4.7));

        System.out.println("Полный список студентов:");
        for (Student student : students) {
            System.out.println(student);
        }

        Iterator<Student> studentIterator = students.iterator();
        while (studentIterator.hasNext()) {
            if (studentIterator.next().getAge() < 18) {
                studentIterator.remove();
            }
        }
        System.out.println("\nСписок после удаления совершеннолетних (18+):");
        for (Student student : students) {
            System.out.println(student);
        }

        if (students.isEmpty()) {
            System.out.println("Список студентов пуст.");
            return;
        }

        Student bestStudent = students.get(0);
        for (Student student : students) {
            if (student.getAverageGrade() > bestStudent.getAverageGrade()) {
                bestStudent = student;
            }
        }
        System.out.println("\nСтудент с максимальным средним баллом: " + bestStudent);

        ArrayList<Student> excellentStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.isExcellent()) {
                excellentStudents.add(student);
            }
        }

        String[] excellentNames = new String[excellentStudents.size()];
        for (int i = 0; i < excellentStudents.size(); i++) {
            excellentNames[i] = excellentStudents.get(i).getName();
        }
        System.out.println("\nМассив имён отличников: " + Arrays.toString(excellentNames));

        students.sort(Comparator.comparingDouble(Student::getAverageGrade).reversed());

        System.out.println("\nИсходный список (18+), отсортированный по убыванию оценок:");
        for (Student student : students) {
            System.out.println(student);
        }
    }
}

