package strings;

import java.util.Random;

/**
 *  Постоянство строк
 *
 *  В методе implicit() в строке 29 каждый раз создается объект StringBuilder в байт-коде.
 *  А в методе explicit() один раз создается объект StringBuilder, а цикле идет добавление.
 *
 *  Если при создании метода toString() выполняются простые операции, в которых компилятор
 *  может разобраться самостоятельно, можно доверить построение результата компилятору.
 *  Но если в вычислениях задействован цикл, лучше явно использовать StringBuilder в toString().
 *  См пример UsingStringBuilder.
 */

public class Immutable {
    public static String upcase(String s) {
        return s.toUpperCase();
    }

    public static void main(String[] args) {
        String q = "howdy";
        System.out.println(q); // howdy
        String qq = upcase(q);
        System.out.println(qq); // HOWDY
        System.out.println(q); // howdy
    }
}

class WhitherStringBuilder {
    public String implicit(String[] fields) {
        String result = "";
        for (int i = 0; i < fields.length; i++)
            result += fields[i];
        return result;
    }

    public String explicit(String[] fields) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < fields.length; i++)
            result.append(fields[i]);
        return result.toString();
    }
}

class UsingStringBuilder {
    public static Random rand = new Random(47);

    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < 25; i++) {
            result.append(rand.nextInt(100));
            result.append(", ");
        }
        result.delete(result.length() - 2, result.length());
        result.append("]");
        return result.toString();
    }

    public static void main(String[] args) {
        UsingStringBuilder usb = new UsingStringBuilder();
        System.out.println(usb);
    }
}