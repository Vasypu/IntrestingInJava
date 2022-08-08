package innerclasses;

/**
 *  Наследование от внутренних классов.
 *
 *  Так как внутренние классы имеют "скрытую ссылку" на окружающий внешний класс,
 *  то в дочернем классе она должна быть инициализирована, для этого используется
 *  специальный синтаксис.
 */

class WithInner {
    class Inner {}
}

public class InheritInner extends WithInner.Inner {
//    InheritInner() {}         // не компилируется
    InheritInner(WithInner wi) {
        wi.super();
    }
    public static void main(String[] args) {
        WithInner wi = new WithInner();
        InheritInner ii = new InheritInner(wi);
    }
}