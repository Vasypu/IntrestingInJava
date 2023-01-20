package RTTI.packageaccess;

import RTTI.A;

// Единственная открытая (public) часть этого пакета HiddenC при вызове создает интерфейс А.
// Здесь интересно то, что даже если makeA() будет возвращать с, за пределами пакета не
// удастся использовать ничего, кроме А, так как имя С вне пакета будет недоступно
class C implements A {
    public void f() { System.out.println("public C.f()"); }
    public void g() { System.out.println("public C.g()"); }
    void u() { System.out.println("package C.u()"); }
    protected void v() { System.out.println("protected C.v()"); }
    private void w() { System.out.println("private C.w()"); }
}

public class HiddenC {
    public static A makeA() { return new C(); }
}