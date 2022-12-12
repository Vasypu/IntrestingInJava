package RTTI;

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
