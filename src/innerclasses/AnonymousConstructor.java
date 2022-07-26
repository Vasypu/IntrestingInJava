package innerclasses;

/**
 *  Конструктор для анонимного класса.
 *
 *  В анонимном классе нет конструктора, так как у него нет имени,
 *  но добиться поведения конструктора для анонимного класса можно.
 *  Переменная i не обязана быть final переменной. Несмотря на то,
 *  что i передается конструктору базового класса, она никогда не
 *  используется напрямую внутри анонимного класса.
 */

abstract class Base {
    public Base(int i) {
        System.out.println("Конструктор Base i = " + i);
    }
    public abstract void f();
}

public class AnonymousConstructor {
    public static Base getBase(int i) {
        return new Base(i) {
            { System.out.println("Инициализация экземпляра"); }
            public void f() {
                System.out.println("Анонимный f()");
            }
        };
    }
    public static void main(String[] args) {
        Base base = getBase(47);
        base.f();
    }
}
