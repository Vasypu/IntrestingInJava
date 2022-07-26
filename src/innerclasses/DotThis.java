package innerclasses;

/**
 *  .this и .new
 *  Если вам нужна ссылка объект внешнего класса, используйте
 *  название_внешнего_класса.this.
 *  Невозможно создать объект внутреннего класса, не имея ссылки
 *  на внешний класс.
 */

public class DotThis {
    void f() { System.out.println("DotThis.f()"); }
    public class Inner {
        public DotThis outer() { return DotThis.this; }
    }
    public Inner inner() { return new Inner(); }
    public static void main(String[] args) {
        DotThis dt = new DotThis();
        DotThis.Inner dt1 = dt.inner();
        dt1.outer().f();
    }
}

class DotNew {
    public class Inner {}
    public static void main(String[] args) {
        DotNew dn = new DotNew();
        DotNew.Inner dn1 = dn.new Inner();     // создаем объект внутреннего класса
    }
}
