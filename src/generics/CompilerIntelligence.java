package generics;

import java.util.Arrays;
import java.util.List;

/**
 *  Насколько умен компилятор?
 *  <p>
 *  У contains() и indexOf() аргументы относятся к типу Object, так что маски не задействованы и компиляторы
 *  разрешают вызов. Это означает, что проектировщик обобщенного класса сам решает, какие вызовы «безопасны»,
 *  и использует типы Object для их аргументов. Чтобы запретить вызов при использовании типа с масками, используйте
 *  параметр-тип в списке аргументов.
 *  <p>
 *  Компилятор только обращает внимание на типы передаваемых и возвращаемых объектов. Он не анализирует код, чтобы
 *  проверить, выполняются ли в нем фактические операции чтения или записи.
 */

// В примере вызываются методы contains() и indexOf(), получающие объекты Apple в аргументах, и все
// проходит нормально.
// Если add() получает аргумент с параметром-типом обобщения, contains() и indexOf() получают аргументы
// типа Object. Таким образом, с объявлением ArrayList<? extends Fruit> аргумент add() превращается в
// ? extends Fruit. По этому описанию компилятор не может узнать, какой конкретный подтип Fruit здесь нужен,
// и поэтому не принимает никакие типы Fruit. Даже если вы предварительно выполнили восходящее преобразование
// Apple к Fruit, компилятор просто отказывается вызывать метод (такой, как addQ), если в списке аргументов
// присутствует маска.
public class CompilerIntelligence {
    public static void main(String[] args) {
        List<? extends Fruit> flist = Arrays.asList(new Apple());
        Apple a = (Apple)flist.get(0);  // Без предупреждений
        flist.contains(new Apple());    // Аргумент 'Object'
        flist.indexOf(new Apple());     // Аргумент 'Object'
    }
}

// при создании объекта Holder<Apple> вы не сможете выполнить его восходящее преобразование в Holder<Fruit>, но
// можно повысить до Holder<? extends Fruit>. Если вызвать get(), метод вернет Fruit — это максимальная информация,
// которой он располагает при наличии ограничения «любой класс, расширяющий Fruit». Если у вас больше информации,
// вы можете преобразовать его к конкретному подтипу Fruit без выдачи предупреждений, но возникает исключение
// ClassCastException. Метод set() не будет работать с Apple или Fruit, потому что аргумент set() тоже объявлен как
// «? Extends Fruit»; это означает, что им может быть что угодно, а компилятор не может проверить безопасность типов
// для «чего угодно».
class Holder<T> {
    private T value;
    public Holder() {}
    public Holder(T val) { value = val; }
    public void set(T val) { value = val; }
    public T get() { return value; }
    public boolean equals(Object obj) {
        return value.equals(obj);
    }
    public static void main(String[] args) {
        Holder<Apple> apple = new Holder<Apple>(new Apple());
        Apple d = apple.get();
        apple.set(d);
        // Holder<Fruit> Fruit = apple; // Не может выполнить восходящее преобразование
        Holder<? extends Fruit> fruit = apple; // 0K
        Fruit p = fruit.get();
        d = (Apple)fruit.get(); // Возвращает 'Object'
        try {
            Orange с = (Orange)fruit.get(); // Без предупреждений
        } catch(Exception e) { System.out.println(e); }
//         fruit.set(new Apple()); // Вызов set() невозможен
//         fruit.set(new Fruit()); // Вызов set() невозможен
        System.out.println(fruit.equals(d)); // 0K
    }
}
