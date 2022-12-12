package strings;

import java.io.PrintStream;
import java.util.Formatter;

/**
 * Форматирование вывода
 * Метод printf() не «собирает» строки так; он получает одну форматную строку и
 * вставляет в нее значения, форматируя их при подстановке.
 * <p>
 * Спецификатор %d в приведенном примере указывает, что x является целочисленным
 * значением, а %f — что y является вещественным значением (float или double).
 * Форматный спецификатор %s, обозначающий аргумент String.
 * <p>
 * Вызовы format() и printf() эквивалентны. В обоих случаях передается одна
 * форматная строка, за которой перечисляются аргументы — по одному для каждого
 * форматного спецификатора.
 */

public class SimpleFormat {
    public static void main(String[] args) {
        int x = 5;
        double у = 5.332542;
        // Старый способ:
        System.out.println("Row 1: [" + x + " " + у + "]");
        // Новый способ:
        System.out.format("Row 1: [%d %f]\n", x, у);
        // или
        System.out.printf("Row 1: [%d %f]\n", x, у);
    }
}

//  Класс Formatter можно рассматривать как преобразователь, приводящий
//  форматную строку и данные к нужному результату. При создании объекта
//  Formatter вы сообщаете ему, куда следует выдать результат.

class Turtle {
    private String name;
    private Formatter f;

    public Turtle(String name, Formatter f) {
        this.name = name;
        this.f = f;
    }

    public void move(int x, int y) {
        f.format("%s The Turtle is at (%d,%d)\n", name, x, y);
    }

    public static void main(String[] args) {
        PrintStream outAlias = System.out;
        Turtle tommy = new Turtle("Tommy", new Formatter(System.out));
        Turtle terry = new Turtle("Terry", new Formatter(outAlias));
        tommy.move(0, 0);
        terry.move(4, 8);
        tommy.move(3, 4);
        terry.move(2, 5);
        tommy.move(3, 3);
        terry.move(3, 3);
    }
}

//  Статический метод String.format() получает те же аргументы, что и метод format()
//  класса Formatter, но возвращает String. Он может пригодиться в ситуации, в которой
//  format() нужно вызвать всего один раз.
class DatabaseException extends Exception {
    public DatabaseException(int transactionID, int queryID, String message) {
        super(String.format("(t%d, q%d) %s", transactionID, queryID, message));
    }

    public static void main(String[] args) {
        try {
            throw new DatabaseException(3, 7, "Ошибка записи");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}