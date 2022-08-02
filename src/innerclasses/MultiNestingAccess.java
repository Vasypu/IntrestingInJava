package innerclasses;

/**
 *  Многократная вложенность классов.
 *
 *  Независимо от глубины вложенности, внутренний класс может напрямую
 *  обращаться ко всем членам всех классов, в которые он встроен.
 */

class MNA {
    private void f() {}
    class A {
        private void g() {}
        public class B {
            void h() {
                g();         // private метод класса A
                f();         // private метод класса MNA
            }
        }
    }
}

public class MultiNestingAccess {
    public static void main(String[] args) {
        MNA mna = new MNA();
        MNA.A mnaa = mna.new A();
        MNA.A.B mnaab = mnaa.new B();
        mnaab.h();
    }
}
