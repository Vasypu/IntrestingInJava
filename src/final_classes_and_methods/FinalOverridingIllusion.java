package final_classes_and_methods;

/**
 *  private метод нельзя переопределить так как он недоступен и невидим для окуржающего мира.
 *  Поэтому если создать public или protected одноименный метод в дочернем классе, то он никак
 *  не будет связан с private методом родительского класса. Ключевое слово final можно добавть
 *  к методу но оно ни на что не влияет.
 */

class WithFinals {
    private final void f() { System.out.println("WithFinals f()"); }
    private void g() { System.out.println("WithFinals g()"); }
}

class OverridingPrivate extends WithFinals {
    private final void f() { System.out.println("OverridingPrivate f()"); }
    private void g() { System.out.println("OverridingPrivate g()"); }
}

class OverridingPrivate2 extends OverridingPrivate {
    public final void f() { System.out.println("OverridingPrivate2 f()"); }
    public void g() { System.out.println("OverridingPrivate2 g()"); }
}

public class FinalOverridingIllusion {
    public static void main(String[] args) {
        OverridingPrivate2 op2 = new OverridingPrivate2();
        op2.f();
        op2.g();
//      Можно провести восходящее преобразование
        OverridingPrivate op = op2;
//      Но методы при этом вызвать не возможно
//        op.f();
//        op.g();
//      То же самое здесь
        WithFinals wf = op2;
//        wf.f();
//        wf.g();
    }
}
