package final_class_and_methods;

/**
 * Инициализируются при наследовании родительские классы. Сначала инициализируются статические члены класса родителя,
 * потом инициализируются статические члены дочернего класса, именно такая последовательности используется, потому что
 * статические члены дочернего класса могут зависеть от статических членов родителя. Позже инициализируются остальные члены
 * родителя, потом выполняется конструктор, после происходят те же действия с дочерним классом.
 */

class Insect {
    private int i = 9;
    protected int j;
    Insect() {
        System.out.println("i= " + i + " j= " + j);
        j = 39;
    }
    int m = 100;
    private static int x1 = printInt("Поле static Insect x1 инициализировано");
    static int printInt(String s) {
        System.out.println(s);
        return 47;
    }
}

public class Beetle extends Insect {
    private int v = 10;
    private int y;
    private int k = printInt("Поле Beetle k инициализировано");
    public Beetle() {
        y = 25;
        System.out.println("v= " + v);
        System.out.println("y= " + y);
        System.out.println("k= " + k);
        System.out.println("j= " + j);
    }
    private int m = 100;
    private static int x2 = printInt("Поле Beetle x2 инициализировано");

    public static void main(String[] args) {
        System.out.println("Конструктор Beetle");
        Beetle b = new Beetle();
    }
}

class Some {
    public static void main(String[] args) {
        System.out.println("Конструктор Beetle");
        Beetle b = new Beetle();
    }
}
