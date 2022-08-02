package innerclasses;

/**
 *  Применение внутренних классов.
 *
 *  Реализация нескольких интерфейсов.
 *  Под реализацию интерфейса можно выделить отдельный одиночный класс или
 *  внутренний класс.
 *
 *  Реализация реальных и абстрактных классов.
 *  Для реализации абстрактного и реального класса в одном классе следует
 *  использовать внутренние классы.
 *
 *  С помощью внутренних классов можно добиться нескольких реализаций одного
 *  интерфейса в одном внешнем классе.
 */

interface A {}
interface B {}

class X implements A, B {}
class Y implements A {
    B makeB() { return new B() {}; }            //  одна реализации с помощью анонимного класса
    B makeB_2() { return new B() {}; }          // вторая реализации с помощью анонимного класса
}

public class MultiInterfaces {
    static void takeA(A a) {}
    static void takeB(B b) {}
    public static void main(String[] args) {
        X x = new X();
        Y y = new Y();
        takeA(x);
        takeA(y);
        takeB(x);
        takeB(y.makeB());
    }
}

class D {}
abstract class E {}

class Z extends D {
    E makeE() { return new E() {}; } // Анонимный класс реализует абстрактный класс E
}

class MultiImplementation {
    static void takesD(D d) {}
    static void takesE(E e) {}
    public static void main(String[] args) {
        Z z = new Z();
        takesD(z);
        takesE(z.makeE());
    }
}
