package generics;

import java.util.HashMap;
import java.util.Map;

/**
 *  Компенсация стирания
 *  <p>
 *  Стирание приводит к невозможности выполнения некоторых операций в обобщенном коде.
 *  Иногда эти проблемы можно обойти на программном уровне, но в отдельных случаях стирание
 *  приходится компенсировать введением метки типа. Это означает явную передачу объекта Class
 *  для своего типа, чтобы его можно было использовать в выражениях типов.
 */

// Любые операции, требующие знания точного типа во время выполнения, работать не будут.
public class Erased<T> {
    private final int SIZE = 100;
    public static void f(Object arg) {
//        if(arg instanceof T) {}      // Ошибка
//            T var = new T();         // Ошибка
//            T[] array = new T[SIZE]; // Ошибка
//            T[] array = (T) new Object[SIZE]; // Неконтролируемое предупреждение
        }
}

// Например, попытка использования instanceof в предыдущей программе завершается неудачей,
// потому что информация типа была стерта. При введении метки типа можно использовать
// динамический вызов isInstance().
// Компилятор следит за тем, чтобы метка типа соответствовала обобщенному аргументу.
class Building {}
class House extends Building {}
class ClassTypeCapture<T> {
    Class<T> kind;
    Map<String, Class<?>> allTypes = new HashMap<>();
    public ClassTypeCapture(Class<T> kind) { this.kind = kind; }
    public boolean f(Object arg) { return kind.isInstance(arg); }
    public void addType(String typename, Class<?> kind) { allTypes.put(typename, kind); }
//    public T createNew(String typename) {

//        return new typename();
//    }

    public static void main(String[] args) {
        ClassTypeCapture<Building> ctt1 = new ClassTypeCapture<>(Building.class);
        System.out.println(ctt1.f(new Building()));
        System.out.println(ctt1.f(new House()));
        ClassTypeCapture<House> ctt2 = new ClassTypeCapture<>(House.class);
        System.out.println(ctt2.f(new Building()));
        System.out.println(ctt2.f(new House()));
    }
}