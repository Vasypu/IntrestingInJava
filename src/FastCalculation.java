public class FastCalculation {

    static boolean test1(int val) {
        System.out.println("test1(" + val + ")");
        System.out.println("результат " + (val < 1));
        return val < 1;
    }
    static boolean test2(int val) {
        System.out.println("test2(" + val + ")");
        System.out.println("результат " + (val < 2));
        return val < 2;
    }
    static boolean test3(int val) {
        System.out.println("test3(" + val + ")");
        System.out.println("результат " + (val < 3));
        return val < 3;
    }

    public static void main(String[] args) {
        boolean b = test1(0) && test2(2) && test3(2);
        System.out.println("выражение: " + b);
    }
}

/**
 * Каждый из методов test() проводит сравнение своего аргумента и возвращает либо true, либо false.
 * Также они выводят информацию о факте своего вызова. Эти методы используются в выражении
 * test1(0) && test2(2) && test3(2)
 * Естественно было бы ожидать, что все три метода должны выполнятся, но результат программы показывает другое.
 * Первый метод возвращает результат true, поэтому вычисление продолжается. Однако второй метод выдает результат false.
 * Так как это автоматически означает, что все выражение будет равно false, зачем продолжать вычисления? Только
 * лишняя трата времени.
 */
