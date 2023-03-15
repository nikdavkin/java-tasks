package ru.mail.polis.homework.objects;

public class StringTasks {

    /**
     * Убрать все лишние символы из строки и вернуть получившееся число.
     * Разрешенные символы: цифры, '-', '.', 'e'
     * Если '.' и 'e' больше чем 1, возвращаем null
     * Правила на '-' является валидность числа. --3 не валидно. -3e-1 валидино
     * Любой класс-обертка StringTasksTest над примитивами наследуется от Number
     * Можно использовать функции типа Double.valueOf()
     * <p>
     * Работайте со строкой, НЕ надо ее переводить в массив байт (это можно использовать только для цикла)
     * У класса Character есть полезные методы, например Character.isDigit()
     * БЕЗ РЕГУЛЯРОК!
     * 6 тугриков
     */
    public static Number valueOf(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        StringBuilder validString = new StringBuilder();
        boolean dotPresent = false;
        boolean ePresent = false;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '-') {
                if (validString.length() == 0 || validString.charAt(validString.length() - 1) == 'e') {
                    validString.append(ch);
                } else {
                    return null;
                }
            }
            if (ch == 'e' || ch == '.') {
                if (validString.length() == 0 ||
                        !Character.isDigit(validString.charAt(validString.length() - 1)) ||
                        i == str.length() - 1) {
                    return null;
                }
                if (ch == 'e') {
                    if (ePresent) {
                        return null;
                    }
                    ePresent = true;
                } else {
                    if (dotPresent || ePresent) {
                        return null;
                    }
                    dotPresent = true;
                }
                validString.append(ch);
            }
            if (Character.isDigit(ch)) {
                validString.append(ch);
            }
        }
        if (dotPresent || ePresent) {
            return Double.valueOf(validString.toString());
        }
        if (isLong(validString)) {
            return Long.valueOf(validString.toString());
        }
        return Integer.valueOf(validString.toString());
    }

    static boolean isLong(StringBuilder validString) {
        long longNum = Long.parseLong(validString.toString());
        return (longNum != (int) longNum);
    }
}
