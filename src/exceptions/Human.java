package exceptions;

/**
 *  Отождествление исключений
 *
 *  Блок catch(Annoyance а) перехватит Annoyance или любой другой класс,
 *  унаследованный от него.
 *  Во втором примере с наследованием классов демонстрируется, что базовый
 *  класс (Annoyance) перехватит ошибку унаследованного класса (AfterSneeze).
 */

class Annoyance extends Exception {}
class Sneeze extends Annoyance {}
class AfterSneeze extends Sneeze {}

public class Human {
    public static void main(String[] args) {
        try {
            throw new Sneeze();
        } catch(Sneeze s) {
            System.out.println("перехвачено Sneeze");
        } catch(Annoyance a) {
            System.out.println("перехвачено Annoyance");
        }
        // Перехват базового типа:
        try {
            throw new Sneeze();
        } catch(Annoyance а) {
            System.out.println("перехвачено Annoyance");
        }
    }
}

class A {
    public void test1() throws Annoyance {
        throw new Annoyance();
    }
    public static void main(String[] args) throws Annoyance {
        A c = new C();
        c.test1();
    }
}

class B extends A {
    public void test1() throws Sneeze {
        throw new Sneeze();
    }
}

class C extends B {
    public void test1() throws AfterSneeze {
        throw new AfterSneeze();
    }
}