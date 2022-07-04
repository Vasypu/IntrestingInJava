package polymorphism;

/**
 * Нисходящее преобразование и динамическое определение типов.
 */

class Useful {
    public void f() {};
    public void g() {};
}

class MoreUseful extends Useful {
    public void f() {};
    public void g() {};
    public void u() {};
    public void v() {};
    public void w() {};
}

public class RTTI {
    public static void main(String[] args) {
        Useful[] x = {
                new Useful(),
                new MoreUseful()
        };
        x[0].f();
        x[1].g();
        // после восходящего преобразования, не удается найти метод у класса Useful
//        x[1].u();

        ((MoreUseful)x[1]).u(); // нисходящее преобразование
        ((MoreUseful)x[0]).u(); // нисходящее преобразование не работает, так как неправильно указан тип
    }
}
