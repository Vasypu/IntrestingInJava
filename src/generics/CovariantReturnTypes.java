package generics;

/**
 *  Ковариантность аргументов
 *  <p>
 *  При использовании самоограничений можно вернуть базовый тип как в примере GenericsAndReturnTypes.
 *  Но передать базовый тип производный самоограниченый тип нельзя, как в примере SelfBoundingAndCovariantArguments.
 */

// Метод get() в DerivedGetter переопределяет get() из OrdinaryGetter и возвращает тип,
// производный от типа, возвращаемого OrdinaryGetter.get().
class Base {}
class Derived extends Base {}

interface OrdinaryGetter { Base get(); }

interface DerivedGetter extends OrdinaryGetter {
    // Возвращаемый тип переопределенного метода может изменяться:
    Derived get();
}

public class CovariantReturnTypes {
    void test(DerivedGetter d) {
        Derived d2 = d.get();
    }
}

// Самоограничиваемое обобщение производит для возвращаемого значения точный производный тип.
// Как в примере выше.
interface GenericGetter<T extends GenericGetter<T>> {
    T get();
}

interface Getter extends GenericGetter<Getter> {}

class GenericsAndReturnTypes {
    void test(Getter g) {
        Getter result = g.get();
        GenericGetter gg = g.get(); // Также базовый тип
    }
}

// Наследование с переопределением метода set().
class OrdinarySetter {
    void set(Base base) { System.out.println("OrdinarySetter.set(Base)"); }
}

class DerivedSetter extends OrdinarySetter {
    void set(Derived derived) { System.out.println("DerivedSetter.set(Derived)"); }
}

class OrdinaryArguments {
    public static void main(String[] args) {
        Base base = new Base();
        Derived derived = new Derived();
        DerivedSetter ds = new DerivedSetter();
        ds.set(derived);
        ds.set(base); // Компилируется: перегрузка, не переопределение!
    }
}

// C самоограничиваемыми типами в производном классе содержится только один метод, в аргументе
// которого передается производный, а не базовый тип. Компилятор не распознает попытку передачи
// в аргументе set() базового типа, потому что метода с такой сигнатурой не существует. Фактически
// аргумент был переопределен.
interface SelfBoundSetter<T extends SelfBoundSetter<T>> {
    void set(T arg);
}

interface Setter extends SelfBoundSetter<Setter> {}

class SelfBoundingAndCovariantArguments {
    void testA(Setter s1, Setter s2, SelfBoundSetter sbs) {
        s1.set(s2);
//        s1.set(sbs); // Ошибка:
// set(Setter) в SelfBoundSetter<Setter>
// не может применяться к (SelfBoundSetter)
    }
}

// Без самоограничения вступает в силу обычный механизм наследования, и происходит перегрузка, как
// и в случае с необобщенным случаем.
class GenericSetter<T> { // Без самоограничения
     void set(T arg) { System.out.println("GenericSetter.set(Base)"); }
}

class DerivedGS extends GenericSetter<Base> {
    void set(Derived derived) { System.out.println ("DerivedGS.set(Derived)"); }
}

class PlainGenericInheritance {
    public static void main(String[] args) {
        Base base = new Base();
        Derived derived = new Derived();
        DerivedGS dgs = new DerivedGS();
        dgs.set(derived);
        dgs.set(base); // Компилируется: перегрузка, не переопределение!
    }
}