package com.example.lib;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class PracticalWork3 {

    public static void main(String[] args) {
        task1(-5);
        task2("Hello Java");
        task3("Hello Java");
        task4(24);
        task5("Apple", "apricot");
        task6(-12345);
        task7(-12345);
        task8(-12345);
        task9(-12345);
        task10(456, 498);
        task11();
        task12("Java");
        task13(10, 3);
        task14((byte) 1);
        task15(new int[]{5, 12, 10, 23, 0, -5});
        task16("abcdef");
        task17('A');
        task18(new int[]{1, 2, 3, 4, 5, 6});
        task19(new int[]{1, 2, 3, 0, 4, 5});
        task20("123-456-789");
        task21(new String[]{"http://google.com", "https://yandex.ru", "http://example.com", "ftp://file"});
        task22("1203405");
        task23("abcdef");
        task24(new int[]{1, 2, 3, 4, 5, 6});
        task25("abc def jhi");

        task27(133);
        task28(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        task29();
        task30("12345");
        task31("123.45");

        System.out.println("Второй по величине элемент: " + method32(new int[]{3, 7, 1, 9, 7, 9, 5}));
        System.out.println("Диапазон массива: " + Arrays.toString(method33(8, 3)));

        char[] randomChars = new char[5];
        method34(randomChars);
        System.out.println("Случайные символы: " + Arrays.toString(randomChars));

        System.out.println("Сумма Фибоначчи (5): " + method35(5));
        System.out.println("Дата существует: " + method36("2026-02-29"));
        System.out.println("Дней в секундах: " + method37(172800));
        System.out.println("Сгенерированная строка: " + method38(8));
        System.out.println("Первые буквы заглавные: " + method39("java development kit"));

        System.out.println("Массив делителей: " + Arrays.deepToString(method40(new int[]{6, 11})));
        System.out.println("Отсортированные слова: " + method41("banana apple orange pear"));
        System.out.println("Общие элементы: " + Arrays.toString(method42(new int[]{1, 2, 3, 4}, new int[]{3, 4, 5, 6})));

        System.out.println("Неповторяющаяся случайная последовательность:");
        for(int i = 0; i < 5; i++) System.out.print(method43() + " ");
        System.out.println();

        System.out.println("Удаленные дубликаты (>3): " + Arrays.toString(method44(new Integer[]{1, 1, 1, 1, 2, 2, 2, 3, 3})));
        System.out.println("Самое длинное слово: " + method45("Java is a high-level class-based object-oriented language"));

        System.out.println("Даты с четырьмя двойками в XXI веке:");
        method46();

        System.out.println("Пятницы 13-е в 2026 году: " + method47(2026));

        task48();

        System.out.println("Можно ли составить счастливый билет: " + task49(new int[]{1, 6, 5, 2, 4, 6}));
        System.out.println("Случайное слово на букву 'b': " + task50(new String[]{"apple", "banana", "blueberry", "cherry"}, 'b'));
        System.out.println("Множественное число для 'cat': " + method51("cat") + ", 'box': " + method51("box") + ", 'cherry': " + method51("cherry"));

        System.out.println("Дружественные числа в диапазоне 1-3000:");
        method52(1, 3000);

        System.out.print("Разложение числа 120 на простые множители: ");
        method53(120);

        task54(new int[][]{
                {11, 12, 13, 14, 15},
                {21, 22, 23, 24, 25},
                {31, 32, 33, 34, 35},
                {41, 42, 43, 44, 45},
                {51, 52, 53, 54, 55}
        });



        System.out.println(func55(1, "яблоко", "яблока", "яблок"));
        System.out.println(func55(3, "яблоко", "яблока", "яблок"));
        System.out.println(func55(11, "яблоко", "яблока", "яблок"));
        System.out.println(func55(1223421, "яблоко", "яблока", "яблок"));

        task26();
    }

    public static void task1(int num) {
        if (num < 0) {
            System.out.println("Число отрицательное");
        } else if (num > 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число равно нулю");
        }
    }

    public static void task2(String str) {
        System.out.println(str.length());
    }

    public static void task3(String str) {
        if (str != null && !str.isEmpty()) {
            System.out.println(str.charAt(str.length() - 1));
        }
    }

    public static void task4(int num) {
        System.out.println(num % 2 == 0 ? "Четное" : "Нечетное");
    }

    public static void task5(String word1, String word2) {
        if (word1 != null && word2 != null && !word1.isEmpty() && !word2.isEmpty()) {
            System.out.println(Character.toLowerCase(word1.charAt(0)) == Character.toLowerCase(word2.charAt(0)));
        }
    }

    public static void task6(int num) {
        String s = String.valueOf(Math.abs(num));
        System.out.println(s.charAt(0));
    }

    public static void task7(int num) {
        System.out.println(Math.abs(num % 10));
    }

    public static void task8(int num) {
        String s = String.valueOf(Math.abs(num));
        int first = Character.getNumericValue(s.charAt(0));
        int last = Math.abs(num % 10);
        System.out.println(first + last);
    }

    public static void task9(int num) {
        System.out.println(String.valueOf(Math.abs(num)).length());
    }

    public static void task10(int num1, int num2) {
        char first1 = String.valueOf(Math.abs(num1)).charAt(0);
        char first2 = String.valueOf(Math.abs(num2)).charAt(0);
        System.out.println(first1 == first2);
    }

    public static void task11() {
        for (int i = 1; i <= 100; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void task12(String str) {
        if (str != null && str.length() > 1) {
            System.out.println(str.charAt(str.length() - 2));
        }
    }

    public static void task13(int a, int b) {
        System.out.println(a % b);
    }

    public static void task14(byte num) {
        switch (num) {
            case 12: case 1: case 2: System.out.println("Зима"); break;
            case 3: case 4: case 5: System.out.println("Весна"); break;
            case 6: case 7: case 8: System.out.println("Лето"); break;
            case 9: case 10: case 11: System.out.println("Осень"); break;
            default: System.out.println("Неверный номер месяца");
        }
    }

    public static void task15(int[] arr) {
        for (int x : arr) {
            if (x % 5 == 0) {
                System.out.print(x + " ");
            }
        }
        System.out.println();
    }

    public static void task16(String str) {
        if (str != null && str.length() >= 3) {
            System.out.println(str.substring(0, 3));
        }
    }

    public static void task17(char chr) {
        if (Character.isUpperCase(chr)) {
            System.out.println("Верхний регистр");
        } else if (Character.isLowerCase(chr)) {
            System.out.println("Нижний регистр");
        } else {
            System.out.println("Не является буквой");
        }
    }

    public static void task18(int[] arr) {
        if (arr.length >= 3) {
            int[] slice = Arrays.copyOfRange(arr, 0, 3);
            System.out.println(Arrays.toString(slice));
        }
    }

    public static void task19(int[] arr) {
        int pos = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                pos = i;
                break;
            }
        }
        System.out.println(pos);
    }

    public static void task20(String str) {
        System.out.println(str.replace('-', ' '));
    }

    public static void task21(String[] arr) {
        for (String s : arr) {
            if (s != null && s.startsWith("http://")) {
                System.out.println(s);
            }
        }
    }

    public static void task22(String str) {
        System.out.println(str.indexOf('0'));
    }

    public static void task23(String str) {
        if (str != null && str.length() >= 3) {
            System.out.println(str.substring(str.length() - 3));
        }
    }

    public static void task24(int[] arr) {
        if (arr.length >= 5) {
            int[] slice = Arrays.copyOfRange(arr, 2, 5);
            System.out.println(Arrays.toString(slice));
        }
    }

    public static void task25(String str) {
        String[] words = str.split(" ");
        System.out.println(Arrays.toString(words));
    }

    public static void task26() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите первое число: ");
        double a = scanner.nextDouble();
        System.out.print("Введите второе число: ");
        double b = scanner.nextDouble();
        System.out.println("Сумма: " + (a + b));
    }

    public static void task27(int num) {
        String s = String.valueOf(Math.abs(num));
        boolean hasDuplicate = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.indexOf(s.charAt(i)) != s.lastIndexOf(s.charAt(i))) {
                hasDuplicate = true;
                break;
            }
        }
        System.out.println(hasDuplicate);
    }

    public static void task28(int[][] arr) {
        for (int[] row : arr) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static void task29() {
        int maxRows = 5;
        for (int i = maxRows; i >= 1; i--) {
            for (int j = 0; j < maxRows - i; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < (2 * i - 1); k++) {
                System.out.print("x");
            }
            System.out.println();
        }
    }

    public static void task30(String str) {
        System.out.println(str.matches("[0-9]+"));
    }

    public static void task31(String str) {
        System.out.println(str.matches("[0-9]+\\.[0-9]+"));
    }

    public static int method32(int[] arr) {
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        for (int x : arr) {
            if (x > first) {
                second = first;
                first = x;
            } else if (x > second && x < first) {
                second = x;
            }
        }
        return second;
    }
    public static int[] method33(int a, int b) {
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        int[] result = new int[max - min + 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = min + i;
        }
        return result;
    }
    public static void method34(char[] arr) {
        Random rnd = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char) ('a' + rnd.nextInt(26));
        }
    }
    public static long method35(int n) {
        if (n <= 0) return 0;
        long sum = 0;
        long a = 0, b = 1;
        for (int i = 1; i <= n; i++) {
            sum += b;
            long next = a + b;
            a = b;
            b = next;
        }
        return sum;
    }
    public static boolean method36(String dateStr) {
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    public static double method37(long seconds) {
        return (double) seconds / 86400.0;
    }
    public static String method38(int length) {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + rnd.nextInt(26)));
        }
        return sb.toString();
    }
    public static String method39(String str) {
        if (str == null || str.trim().isEmpty()) return "";
        String[] words = str.split(" +");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (!w.isEmpty()) {
                sb.append(Character.toUpperCase(w.charAt(0)));
            }
        }
        return sb.toString();
    }
    public static Integer[][] method40(int[] arr) {
        Integer[][] result = new Integer[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            int num = Math.abs(arr[i]);
            List divisors = new ArrayList();
            for (int j = 1; j <= num; j++) {
                if (num % j == 0) divisors.add(Integer.valueOf(j));
            }
            Integer[] divArray = new Integer[divisors.size()];
            for (int k = 0; k < divisors.size(); k++) {
                divArray[k] = (Integer) divisors.get(k);
            }
            result[i] = divArray;
        }
        return result;
    }
    public static String method41(String text) {
        if (text == null || text.trim().isEmpty()) return "";
        String[] words = text.split(" +");
        Arrays.sort(words);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            sb.append(words[i]);
            if (i < words.length - 1) sb.append(" ");
        }
        return sb.toString();
    }
    public static int[] method42(int[] arr1, int[] arr2) {
        Set set1 = new HashSet();
        for (int x : arr1) set1.add(Integer.valueOf(x));
        List common = new ArrayList();
        for (int x : arr2) {
            if (set1.contains(Integer.valueOf(x)) && !common.contains(Integer.valueOf(x))) {
                common.add(Integer.valueOf(x));
            }
        }
        int[] res = new int[common.size()];
        for (int i = 0; i < common.size(); i++) {
            res[i] = ((Integer) common.get(i)).intValue();
        }
        return res;
    }
    private static int lastRandomValue = -1;
    private static final Random internalRnd = new Random();
    public static int method43() {
        int next;
        do {
            next = internalRnd.nextInt(100);
        } while (next == lastRandomValue);
        lastRandomValue = next;
        return next;
    }
    public static Integer[] method44(Integer[] arr) {
        Map counts = new HashMap();
        for (int i = 0; i < arr.length; i++) {
            Integer x = arr[i];
            Integer currentCount = (Integer) counts.get(x);
            int countVal = (currentCount == null) ? 0 : currentCount.intValue();
            counts.put(x, Integer.valueOf(countVal + 1));
        }
        List result = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            Integer x = arr[i];
            Integer count = (Integer) counts.get(x);
            if (count != null && count.intValue() <= 3) {
                result.add(x);
            }
        }
        Integer[] resArr = new Integer[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resArr[i] = (Integer) result.get(i);
        }
        return resArr;
    }
    public static String method45(String str) {
        if (str == null || str.trim().isEmpty()) return "";
        String[] words = str.split(" +");
        String longest = "";
        for (String w : words) {
            if (w.length() > longest.length()) {
                longest = w;
            }
        }
        return longest;
    }
    public static void method46() {
        for (int year = 2000; year <= 2099; year++) {
            for (int month = 1; month <= 12; month++) {
                int days = LocalDate.of(year, month, 1).lengthOfMonth();
                for (int day = 1; day <= days; day++) {
                    String dateStr = String.format("%02d.%02d.%04d", day, month, year);
                    long count = 0;
                    for (int i = 0; i < dateStr.length(); i++) {
                        if (dateStr.charAt(i) == '2') count++;
                    }
                    if (count == 4) {
                        System.out.println(dateStr);
                    }
                }
            }
        }
    }
    public static int method47(int year) {
        int count = 0;
        for (int month = 1; month <= 12; month++) {
            LocalDate dt = LocalDate.of(year, month, 13);
            if (dt.getDayOfWeek() == java.time.DayOfWeek.FRIDAY) {
                count++;
            }
        }
        return count;
    }
    public static void task48() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            for (int j = 0; j < i; j++) {
                sb.append(i);
            }
        }
        System.out.println(sb.toString());
    }
    public static boolean task49(int[] arr) {
        if (arr.length != 6) return false;
        List digits = new ArrayList();
        for (int x : arr) digits.add(Integer.valueOf(x));
        return checkPermutationsForLucky(digits, 0);
    }
    private static boolean checkPermutationsForLucky(List list, int index) {
        if (index == list.size()) {
            int sum1 = ((Integer) list.get(0)).intValue() + ((Integer) list.get(1)).intValue() + ((Integer) list.get(2)).intValue();
            int sum2 = ((Integer) list.get(3)).intValue() + ((Integer) list.get(4)).intValue() + ((Integer) list.get(5)).intValue();
            return sum1 == sum2;
        }
        for (int i = index; i < list.size(); i++) {
            Collections.swap(list, index, i);
            if (checkPermutationsForLucky(list, index + 1)) return true;
            Collections.swap(list, index, i);
        }
        return false;
    }
    public static String task50(String[] arr, char letter) {
        List filtered = new ArrayList();
        for (String w : arr) {
            if (w != null && !w.isEmpty() && Character.toLowerCase(w.charAt(0)) == Character.toLowerCase(letter)) {
                filtered.add(w);
            }
        }
        if (filtered.isEmpty()) return null;
        return (String) filtered.get(new Random().nextInt(filtered.size()));
    }
    public static String method51(String noun) {
        if (noun == null || noun.isEmpty()) return noun;
        String lower = noun.toLowerCase();
        if (lower.endsWith("s") || lower.endsWith("x") || lower.endsWith("z") || lower.endsWith("ch") || lower.endsWith("sh")) {
            return noun + "es";
        }
        if (lower.endsWith("y") && lower.length() > 1 && !"aeiou".contains(String.valueOf(lower.charAt(lower.length() - 2)))) {
            return noun.substring(0, noun.length() - 1) + "ies";
        }
        return noun + "s";
    }
    public static void method52(int start, int end) {
        Map sumMap = new HashMap();
        for (int i = start; i <= end; i++) {
            sumMap.put(Integer.valueOf(i), Integer.valueOf(getDivisorsSum(i)));
        }
        for (int i = start; i <= end; i++) {
            Integer sumI = (Integer) sumMap.get(Integer.valueOf(i));
            if (sumI != null && sumI.intValue() > i && sumI.intValue() <= end) {
                Integer sumJ = (Integer) sumMap.get(sumI);
                if (sumJ != null && sumJ.intValue() == i) {
                    System.out.println(i + " и " + sumI);
                }
            }
        }
    }
    private static int getDivisorsSum(int num) {
        int sum = 1;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                sum += i;
                if (i != num / i) {
                    sum += num / i;
                }
            }
        }
        return sum;
    }
    public static void method53(int num) {
        int d = 2;
        while (num > 1) {
            if (num % d == 0) {
                System.out.print(d);
                num /= d;
                if (num > 1) System.out.print(" * ");
            } else {
                d++;
            }
        }
        System.out.println();
    }
    public static void task54(int[][] arr) {
        int minLen = arr.length;
        for (int i = 0; i < minLen; i++) {
            if (arr[i].length > i) {
                arr[i][i] = 0;
            }
        }
        System.out.println(Arrays.deepToString(arr));
    }
    public static String func55(long number, String form1, String form2, String form5) {
        long absNumber = Math.abs(number);
        long n100 = absNumber % 100;
        long n10 = absNumber % 10;
        if (n100 >= 11 && n100 <= 19) {
            return number + " " + form5;
        }
        if (n10 == 1) {
            return number + " " + form1;
        }
        if (n10 >= 2 && n10 <= 4) {
            return number + " " + form2;
        }
        return number + " " + form5;
    }
}


