package lessons;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение (например, 2 + 2):");
        String input = scanner.nextLine();
        input = input.trim();

        try {
            stringExam(input);
            String result = calc(input);
            System.out.println("Результат: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    static String calc(String input) {
        String[] elements = input.split(" ");
        int num1;
        int num2;

        if (isRoman(elements[0]) && isRoman(elements[2])) {
            num1 = RomanConverter.romanToDecimal(elements[0]);
            num2 = RomanConverter.romanToDecimal(elements[2]);
        } else if (isRoman(elements[0])) {
            throw new IllegalArgumentException("Нельзя использовать одновременно римские и арабские числа.");
        } else {
            num1 = Integer.parseInt(elements[0]);
            num2 = Integer.parseInt(elements[2]);
        }
        if ((num1 > 10) || (num2 > 10)) {
            throw new IllegalArgumentException("Допустимые числа от 1 до 10 включительно.");
        } else if ((num1 < 1) || (num2 < 1)) {
            throw new IllegalArgumentException("Вы ввели числа меньше единицы. Допустимые числа от 1 до 10 включительно.");
        }

        String operator = elements[1];
        int result = 0;

        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    throw new IllegalArgumentException("Деление на ноль");
                }
                break;
            default:
                throw new IllegalArgumentException("Неверный оператор");
        }

        if (isRoman(elements[0])) {
            if (result <= 0) {
                throw new IllegalArgumentException("Результат не может быть меньше единицы для римских чисел.");
            }
            return RomanConverter.decimalToRoman(result);
        } else {
            return Integer.toString(result);
        }
    }

    static void stringExam(String input) throws IllegalArgumentException {
        if (input.isEmpty() || input.length() == 0) {
            throw new IllegalArgumentException("Вы ввели пустую строку.");
        }
        String[] str = input.split("[+\\-*/]");
        if (str.length >= 3) {
            throw new IllegalArgumentException("Формат математической операции не удовлетворяет заданию.");
        }
    }

    static boolean isRoman(String str) {
        return str.matches("[IVXLCDMivxlcdm]+");
    }
}

class RomanConverter {
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};

    public static int romanToDecimal(String romanNumber) {
        for (int i = 0; i < romanArray.length; i++) {
            if (romanNumber.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String decimalToRoman(int number) {
        return romanArray[number];
    }
}
