package RTTI;

/**
 * Все классы загружаются в JVM динамически, при первом использовании класса.
 * Это происходит при первом обращении к статическому члену класса. Оказывается,
 * конструктор тоже является статическим членом класса, хотя ключевое слово static
 * для конструктора не используется. Таким образом, создание нового объекта класса
 * оператором new также считается обращением к статическому члену класса.
 * Программа Java не загружается полностью в самом начале; ее фрагменты загружаются
 * по мере необходимости
 */

class Candy {
    static { System.out.println("Загрузка класса Candy"); }
}

class Gum {
    static { System.out.println("Загрузка класса Gum"); }
}

class Cookie {
    static { System.out.println("Загрузка класса Cookie"); }
}

public class SweetShop {
    public static void main(String[] args) {
        System.out.println("B методе main");
        new Candy();
        System.out.println("после создания объекта Candy");
        try {
            Class.forName("RTTI.Gum");
        } catch (ClassNotFoundException e) {
            System.out.println("He удалось найти Gum");
        }
        System.out.println("после вызова метода Class.forName(\"Gum\")");
        new Cookie();
        System.out.println("после создания объекта Cookie");
    }
}
