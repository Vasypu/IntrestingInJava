package strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

/**
 * Сканирование ввода
 * <p>
 * С классом Scanner ввод, разбивка на лексемы и разбор удобно распределяются по разным видам
 * методов next(). Простой метод next() возвращает следующую лексему String; также существуют
 * методы next() для всех примитивных типов (кроме char), BigDecimal и BigInteger. Все методы
 * next выполняются в блокирующем режиме; это означает, что они возвращают управление только
 * после появления следующей завершенной входной лексемы. Также существуют соответствующие
 * методы hasNext, которые возвращают true, если следующая входная лексема относится к
 * правильному типу.
 */

public class SimpleRead {
    public static BufferedReader input = new BufferedReader(
            new StringReader("Sir Robin of Camelot\n22 1.61803"));

    public static void main(String[] args) {
        try {
            System.out.println("What is your name?");
            String name = input.readLine();
            System.out.println(name);
            System.out.println(
                    "How old are you? What is your favorite double?");
            System.out.println("(input: <age> <double>)");
            String numbers = input.readLine();
            System.out.println(numbers);
            String[] numArray = numbers.split(" ");
            int age = Integer.parseInt(numArray[0]);
            double favorite = Double.parseDouble(numArray[1]);
            System.out.format("Hi %s.\n", name);
            System.out.format("In 5 years you will be %d.\n", age + 5);
            System.out.format("My favorite double is %f.", favorite / 2);
        } catch (IOException e) {
            System.err.println("I/O exception");
        }
    }
}

class BetterRead {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(SimpleRead.input);
        System.out.println("What is your name?");
        String name = stdin.nextLine();
        System.out.println(name);
        System.out.println(
                "How old are you? What is your favorite double?");
        System.out.println("(input: <age> <double>)");
        int age = stdin.nextInt();
        double favorite = Double.parseDouble(stdin.next());
        System.out.println(age);
        System.out.println(favorite);
        System.out.format("Hi %s.\n", name);
        System.out.format("In 5 years you will be %d.\n", age + 5);
        System.out.format("My favorite double is %f.", favorite / 2);
    }
}

// В качестве разделителей при чтении из String используются запятые (окруженные
// произвольным количеством пропусков). Тот же способ может использоваться для
// чтения из файлов, разделенных запятыми. Кроме метода useDelimiter(), назначающего
// шаблон разделителя, также существует метод delimiter(), который возвращает текущий
// объект Pattern, используемый в качестве разделителя
class ScannerDelimiter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner("12, 42, 78, 99, 42");
        scanner.useDelimiter("\\s*,\\s*");
        while (scanner.hasNextInt())
            System.out.println(scanner.nextInt());
    }
}