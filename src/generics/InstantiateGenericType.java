package generics;

/**
 *  Создание экземпляров типов
 *  <p>
 *  Попытка создания экземпляра new т() в Erased.java завершилась неудачей — отчасти из-за стирания,
 *  отчасти из-за того, что компилятор не может убедиться в наличии у т конструктора по умолчанию
 *  (без аргументов).
 */

// В качестве фабрики удобно использовать объект Class, так что при использовании метки типа для
// создания нового объекта этого типа можно воспользоваться методом newInstance():
class ClassAsFactory<T> {
    T x;
    public ClassAsFactory(Class<T> kind) {
        try {
            x = kind.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class Employee {}

// Программа компилируется, но при выполнении ClassAsFactory<Integer> происходит ошибка, потому что
// Integer не имеет конструктора по умолчанию.
public class InstantiateGenericType {
    public static void main(String[] args) {
        ClassAsFactory<Employee> fe = new ClassAsFactory<>(Employee.class);
        System.out.println("ClassAsFactory<Employee> succeeded");
        try {
            ClassAsFactory<Integer> fi = new ClassAsFactory<>(Integer.class);
        } catch(Exception e) {
            System.out.println("ClassAsFactory<Integer> failed");
        }
    }
}

// В этом случае вы можете пользоваться преимуществами проверки на стадии компиляции.
interface FactoryI<T> { T create(); }

class Foo2<T> {
    private T x;
    public <F extends FactoryI<T>> Foo2(F factory) {
        x = factory.create();
    }
}

class IntegerFactory implements FactoryI<Integer> {
    public Integer create() { return new Integer(0); }
}

class Widget {
    public static class Factory implements FactoryI<Widget> {
        public Widget create() { return new Widget(); }
    }
}

class FactoryConstraint {
    public static void main(String[] args) {
        new Foo2<Integer>(new IntegerFactory());
        new Foo2<Widget>(new Widget.Factory());
    }
}

// Следующее решение основано на паттерне проектирования «Шаблонный метод». В следующем примере
// get() является шаблонным методом, а create() определяется в субклассе для создания объекта
// соответствующего типа.
abstract class GenericWithCreate<T> {
    final T element;
    GenericWithCreate() { element = create(); }
    abstract T create();
}

class X {}

class Creator extends GenericWithCreate<X> {
    X create() { return new X(); }
    void f() { System.out.println(element.getClass().getSimpleName()); }
}

class CreatorGeneric {
    public static void main(String[] args) {
        Creator c = new Creator();
        c.f();
    }
}