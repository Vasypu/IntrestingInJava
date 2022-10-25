package strings;

import java.math.BigInteger;
import java.util.Formatter;

/**
 * Форматные спецификаторы
 * <p>
 * %[аргумент_индекс$][флаги][ширина][.точность]преобразование
 * Спецификатор ширина управляет минимальным размером поля. Объект Formatter гарантирует,
 * что выходное поле занимает не менее указанного количества символов; при необходимости
 * данные дополняются пробелами. По умолчанию данные выравниваются по правому краю, но
 * тип выравнивания можно переопределить, включив символ - в секцию флаги.
 * В отличие от ширины поле точность задает максимальное значение. И если ширина относится
 * ко всем типам преобразований данных и работает одинаково для всех типов, точность имеет
 * разный смысл для разных типов. Для объектов String точность задает максимальное
 * количество выводимых символов. Для вещественных чисел точность задает количество
 * выводимых знаков (шесть по умолчанию), с округлением или добавлением завершающих нулей
 * в случае необходимости. Так как целые числа не имеют дробной части, точность на них не
 * распространяется, и при попытке применения этого спецификатора с целочисленным
 * преобразованием будет возбуждено исключение.
 */

public class Receipt {
    private double total = 0;
    private Formatter f = new Formatter(System.out);

    public void printTitle() {
        f.format("%-15s %5s %10s\n", "Item", "Qty", "Price");
        f.format("%-15s %5s %10s\n", "---", "---", "-----");
    }

    public void print(String name, int qty, double price) {
        f.format("%-15.15s %5d %10.2f\n", name, qty, price);
        total += price;
    }

    public void printTotal() {
        f.format("%-15s %5s %10.2f\n", "Tax", "", total * 0.06);
        f.format("%-15s %5s %10s\n", "", "", "----");
        f.format("%-15s %5s %10.2f\n", "Total", "", total * 1.06);
    }

    public static void main(String[] args) {
        Receipt receipt = new Receipt();
        receipt.printTitle();
        receipt.print("Jack's Magic Beans", 4, 4.25);
        receipt.print("Princess Peas", 3, 5.1);
        receipt.print("Three Bears Porridge", 1, 14.29);
        receipt.printTotal();
    }
}

//  Преобразования Formatter
//  преобразование b работает для всех переменных. Хотя оно действительно для всех типов аргументов,
//  оно может работать не так, как вы ожидаете. Для примитивов boolean или объектов Boolean
//  результат будет равен true или false в зависимости от значения, но для любого другого аргумента,
//  отличного от null, результат всегда равен true. Даже для числового значения 0, которое является
//  синонимом false, будет получено значение true;
class Conversion {
    public static void main(String[] args) {
        Formatter f = new Formatter(System.out);

        char u = 'a';
        System.out.println("u = 'a'");
        f.format("s: %s\n", u);
        // f.format("d: %d\n'\ u);
        f.format("c: %c\n", u);
        f.format("b: %b\n", u);
        // f.format("f: %f\n", u);
        // f.format("e: %e\n", u);
        // f.format("x: %x\n", u);
        f.format("h: %h\n", u);

        int v = 121;
        System.out.println("v = 121");
        f.format("d: %d\n", v);
        f.format("c: %c\n", v);
        f.format("b: %b\n", v);
        f.format("s: %s\n", v);
        // f.format("e: %e\n"j v);
        // f.format("f: %f\n", v);
        f.format("x: %x\n", v);
        f.format("h: %h\n", v);

        BigInteger w = new BigInteger("50000000000000");
        System.out.println("w = new BigInteger(\"50000000000000\")");
        f.format("d: %d\n", w);
        // f.format("c: %c\n", w);
        f.format("b: %b\n", w);
        f.format("s: %s\n", w);
        // f.format("f: %f\n", w);
        // f.format("e: %e\n", w);
        f.format("x: %x\n", w);
        f.format("h: %h\n", w);

        double x = 179.543;
        System.out.println("x = 179.543");
        // f.format("d: %d\n", x);
        // f.format("c: %c\n", x);
        f.format("b: %b\n", x);
        f.format("s: %s\n", x);
        f.format("f: %f\n", x);
        f.format("e: %e\n", x);
        // f.format("x: %x\n", x);
        f.format("h: %h\n", x);
        Conversion у = new Conversion();
        System.out.println("y = new Conversion()");
        // f.format("d: %d\n", у);
        // f.format("c: %c\n\ у);
        f.format("b: %b\n", у);
        f.format("s: %s\n", у);
        // f.format("f: %f\n", у);
        // f.format("e: %e\n% у);
        // f.format("x: %x\n", у);
        f.format("h: %h\n", у);

        boolean z = false;
        System.out.println("z = false");
        // f.format("d: %d\n", z);
        // f.format("c: %c\n", z);
        f.format("b: %b\n", z);
        f.format("s: %s\n", z);
        // f.format("f: %f\n", z);
        // f.format("e: %e\n", z);
        // f.format("x: %x\n"j z);
        f.format("h: %h\n", z);
    }
}
