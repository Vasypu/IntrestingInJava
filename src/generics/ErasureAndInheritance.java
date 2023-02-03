package generics;

/**
 *  Проблемы стирания
 *  <p>
 *
 */
class GenericBase<T> {
    private T element;
    public void set(T arg) { arg = element; }
    public T get() { return element; }
}

class Derived1<T> extends GenericBase<T> {}
class Derived2 extends GenericBase {} // Без предупреждений

// class Derived3 extends GenericBase<?> {}
// Странная ошибка:
// найден непредвиденный тип : ?
// требуется : класс или интерфейс без ограничений

public class ErasureAndInheritance {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Derived2 d2 = new Derived2();
        Object obj = d2.get();
        d2.set(obj); // Предупреждение!
    }
}

class Foo<T> {
    T var;
}

// Может показаться, что при создании экземпляра Foo:
// Foo<Cat> f = new Foo<Cat>()j
// код класса Foo должен знать, что теперь он работает с Cat. Синтаксис создает впечатление,
// что тип т автоматически подставляется везде, где он используется в классе. Но это не так,
// и при написании кода класса вы должны постоянно напоминать себе: «Нет, это всего лишь Object».