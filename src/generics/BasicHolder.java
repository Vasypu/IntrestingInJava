package generics;

/**
 *  Самоограничиваемые типы
 *  <p>
 *  Самоограничение служит только для принудительного соблюдения отношений наследования. Используя
 *  самоограничение, вы знаете, что параметр-тип, используемый классом, будет относиться к тому же
 *  базовому типу, что и класс, использующий этот параметр. Оно заставляет всех пользователей этого
 *  класса следовать указанному образцу.
 */

// Необычные рекурсивные обобщения.
public class BasicHolder<T> {
    T element;
    void set(T arg) { element = arg; }
    T get() { return element; }
    void f() { System.out.println(element.getClass().getSimpleName()); }
}

// Данный класс наследует BasicHolder, функционал которого сохранять элемента переданный ему.
// Так как мы передает в качестве параметра T класс Subtype, то при реализации такой конструкции
// BasicHolder<Subtype> в классе будут храниться объекты Subtype.
class Subtype extends BasicHolder<Subtype> {}

class CRGWithBasicHolder {
    public static void main(String[] args) {
        Subtype st1 = new Subtype(), st2 = new Subtype();
        st1.set(st2);
        Subtype st3 = st1.get();
        st1.f();
    }
}

// BasicHolder может использовать в своем обобщенном параметре любой тип.
class Other {}
class BasicOther extends BasicHolder<Other> {}

class Unconstrained {
    public static void main(String[] args) {
        BasicOther b = new BasicOther(), b2 = new BasicOther();
        b.set(new Other());
        Other other = b.get();
        b.f();
    }
}

// Самоограничение заставляет обобщение использовать самого себя в качестве аргумента ограничения.
class SelfBounded<T extends SelfBounded<T>> {
    T element;

    SelfBounded<T> set(T arg) {
        element = arg;
        return this;
    }
    T get() { return element; }
}

class A2 extends SelfBounded<A2> {}
class B extends SelfBounded<A2> {} // Также допустимо
class C extends SelfBounded<C> {
    C setAndGet(C arg) { set(arg); return get(); }
}

class D {}
// Невозможно:
//class E extends SelfBounded<D> {}
// Ошибка компиляции: параметр-тип D не соответствует ограничению

// Увы, это возможно, так что принудить к выполнению идиомы не удастся:
class F extends SelfBounded {}

class SelfBounding {
    public static void main(String[] args) {
        A2 a = new A2();
        a.set(new A2());
        a = a.set(new A2()).get();
        a = a.get();
        C c = new C();
        c = c.setAndGet(new C());
    }
}

// Ограничение можно снять, и все по-прежнему будут компилироваться, но тогда также откомпилируется и класс E.
class NotSelfBounded<T> {
    T element;
    NotSelfBounded<T> set(T arg) {
        element = arg;
        return this;
    }
    T get() { return element; }
}

class A3 extends NotSelfBounded<A3> {}
class B2 extends NotSelfBounded<A2> {}

class C2 extends NotSelfBounded<C2> {
    C2 setAndGet(C2 arg) { set(arg); return get(); }
}
class D2 {}
// Теперь возможно:
class E2 extends NotSelfBounded<D2> {}

// Самоограничение также можно использовать для обобщенных методов.
class SelfBoundingMethods {
    static <T extends SelfBounded<T>> T f(T arg) {
        return arg.set(arg).get();
    }
    public static void main(String[] args){
        A2 а = f(new A2());
    }
}