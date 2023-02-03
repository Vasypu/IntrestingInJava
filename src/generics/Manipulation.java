package generics;

/**
 *  Стирания
 *  <p>
 *  Обобщения приносят пользу только тогда, когда вы хотите использовать параметры-типы,
 *  более «универсальные», чем конкретный тип (и все его подтипы), — то есть если код
 *  должен работать в границах нескольких классов. В результате параметры-типы и их
 *  применение в практическом обобщенному коде обычно сложнее простой замены класса.
 *  Впрочем, это не значит, что любая конструкция вида <T extends HasF> бесполезна.
 *  Например, если класс содержит метод, который возвращает т, обобщения приносят пользу,
 *  потому что они возвращают точный тип.
 */

// Из-за стирания компилятор Java не может связать требование, согласно которому метод
// manipulate() должен быть способен вызвать f() для obj, с тем фактом, что класс HasF
// содержит метод f().
class HasF {
    public void f() { System.out.println("HasF.f()"); }
}

class Manipulator<T> {
    private T obj;
    public Manipulator(T x) { obj = x; }
//     Ошибка: не удается найти метод f():
//    public void manipulate() { obj.f(); }
}

public class Manipulation {
    public static void main(String[] args) {
        HasF hf = new HasF();
        Manipulator<HasF> manipulator = new Manipulator<HasF>(hf);
//        manipulator.manipulate();
    }
}

// Чтобы вызвать f(), необходимо помочь обобщенному классу — передать ему ограничение,
// которое сообщает компилятору, что он должен принимать только типы, удовлетворяющие
// этому ограничению
class Manipulator2<T extends HasF> {
    private T obj;
    public Manipulator2(T x) { obj = x; }
    public void manipulate() { obj.f(); }
}

// Компилятор заменяет параметр-тип его стиранием, так что в предыдущем примере T
// стирается до HasF; это то же самое, что замена T на HasF в теле класса. Возможно,
// вы заметили, что в Manipulation2.java обобщения ничего не дают. С таким же успехом
// вы могли провести стирание самостоятельно и создать класс без обобщения.
class Manipulator3 {
    private HasF obj;
    public Manipulator3(HasF x) { obj = x; }
    public void manipulate() { obj.f(); }
}

class ReturnGenericType<T extends HasF> {
    private T obj;
    public ReturnGenericType(T x) { obj = x; }
    public T get () { return obj; }
}

interface Some {
    void s();
    void d();
}

class RealiseSome implements Some {
    public void s() { System.out.println("Realise s()"); }
    public void d() { System.out.println("Realise d()"); }
    public void g() { System.out.println("Realise g()"); }
}

class AnotherSome {
    public <T extends Some> void m(T t) {  // здесь стирание происходит до Some
        t.s();
        t.d();
    }
    public static void main(String[] args) {
        AnotherSome some = new AnotherSome();
        some.m(new RealiseSome());
    }
}