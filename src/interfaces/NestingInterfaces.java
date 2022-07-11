package interfaces;

/**
 *  Вложенные интерфейсы.
 *
 *  Вложенные интерфейсы могут быть со всеми модификаторами доступа.
 *  Вложенные интерфейсы с модификатором доступа protected могут быть реализованы за пределами
 *  вложенного класса.
 *  Вложенные интерфейсы с модификатором доступа public так же могут быть реализованы за пределами
 *  вложенного класса.
 *  Вложенные интерфейсы с модификатором доступа private можно реализовать только внутри класса которого
 *  он объявлен. private интерфейс нельзя объявить в другом интерфейсе. Даже если интерфейс будет реализован
 *  с помощью public класса, использовать его не получится. Даже public метод getD() который возвращает DImp2
 *
 *  При реализации интерфейса необязательно реализовывать вложенные в него интерфейсы.
 */

class A {
     interface B {
        void f();
    }

    public class BImp implements B {
        public void f() { }
    }

    private class BImp2 implements B {
        public void f() { }
    }

    public interface C {
        void f();
    }

    class CImp implements C {
        public void f() { }
    }

    private class CImp2 implements C {
        public void f() { }
    }

    private interface D {
        void f();
    }

    private class DImp implements D {
        public void f() { }
    }

    public class DImp2 implements D {
        public void f() {
            System.out.println("Что-нибудь");
        }
    }

    public D getD() { return new DImp2(); }

    private D dRef;

    public void receiveD(D d) {
        dRef = d;
        dRef.f();
    }
}

interface E {
    interface G {
        void f();
    }

    public interface H {
        void f();
    }

    void g();
//    не может быть private внутри интерфейса
//    private interface I {}
}

public class NestingInterfaces {
    public class BImp implements A.B {
        public void f() { }
    }

    class CImp implements A.C {
        public void f() { }
    }

//    Private интерфейс не может быть реализован нигде,
//    кроме как внутри класса, где он был определен.
//    class DImp implements A.D {
//        public void f() {}
//    }
    class EImp implements E {
        public void g() { }
    }

    class EGImp implements E.G {
        public void f() { }
    }

    class EImp2 implements E {
        public void g() { }

        class EG implements E.G {
            public void f() { }
        }
    }

    public static void main(String[] args) {
        A a = new A();
//        Нет доступа к A.D
//            A.D ad = a.getD();
//        Не возвращает ничего кроме A.D
//            A.DImp2 di2 = a.getD();
//        можно сделать нисходящее преобразование и реализация private интерфейса будет работать
            A.DImp2 imp2 = (A.DImp2) a.getD();
            imp2.f();
//        Член интерфейса не доступен
//            a.getD().f();
//         но если сделать нисходящее преобразование
            ((A.DImp2) a.getD()).f();
//        Только другой объект класса A может использовать getD()          //устаревшая информация
            A a2 = new A();
            a2.receiveD(a.getD());
    }
}
