package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  Контравариантность
 *  <p>
 *  Установить ограничение по любому базовому классу некоторого класса конструкцией <? super МуClass> или даже
 *  параметру-типу:<? super т> (хотя вы не сможете задать ограничение супер типа для обобщенного параметра, то
 *  есть конструкция <T super MyClass> запрещена)
 */

// Аргумент apples содержит List типа, являющегося базовым для Apple; соответственно, мы знаем, что добавление
// Apple или подтипа Apple безопасно. Так как Apple определяет «нижнюю границу», мы не знаем, безопасно ли
// добавить Fruit в такой контейнер List, потому что это откроет List для добавления типов, отличных от Apple,
// и приведет к нарушению статической безопасности типов.
public class SuperTypeWildcards {
    static void writeTo(List<? super Apple> apples) {
        apples.add(new Apple());
        apples.add(new Jonathan());
// apples.add(new Fruit()); // Ошибка
    }
}

// В fl() мы видим, что вызов работает нормально — при условии, что в List<Apple> помещаются только объекты Apple.
// Однако метод writeExact() не позволяет поместить Apple в List<Fruit>, хотя вы и знаете, что это возможно.
// B методе writeWithWildcard() аргумент имеет тип List<? super T>, тaк чтo List coдepжит конкретный тип, производный от T;
// следовательно, в аргументе методов List можно безопасно передать т или любой тип, производный от т. В этом можно убедиться
// в f2(), где тип Apple, как и прежде, можно поместить в List<Apple>, но также появилась возможность поместить Apple в List<Fruit>.
class GenericWriting {
    static <T> void writeExact(List<T> list, T item) {
        list.add(item);
    }
    static List<Apple> apples = new ArrayList<>();
    static List<Fruit> fruit = new ArrayList<Fruit>();
    static void f1() { writeExact(apples, new Apple());
//        writeExact(fruit, new Apple()); // Ошибка:
        // Несовместимые типы: обнаружен Fruit, требуется Apple
    }
    static <T> void writeWithWildcard(List<? super T> list, T item) {
        list.add(item);
    }
    static void f2() {
        writeWithWildcard(apples, new Apple());
        writeWithWildcard(fruit, new Apple());
    }
    public static void main(String[] args) { f1(); f2(); }
}

// Первый метод readExact() использует точный тип. Итак, если используется точный тип без масок, вы сможете читать и
// записывать этот точный тип в List.
// Если вы работаете с обобщенным классом, параметр задается для класса при создании экземпляра этого класса. Как
// показано в f2(), экземпляр fruitReader может прочитать Fruit из List<Fruit>, поскольку это его точный тип. Но
// контейнер List<Apple> также должен производить объекты Fruit, а fruitReader этого не позволяет.
// Для решения проблемы метод CovariantReader.readCovariant() получает List<? extends T>, поэтому читать т из этого
// списка безопасно (все объекты в списке как минимум имеют тип т, а могут относиться к типу, производному от т). В
// методе f3() мы видим, что теперь тип Fruit можно прочитать из List<Apple>.
class GenericReading {
    static <T> T readExact(List<T> list) {
        return list.get(0);
    }
    static List<Apple> apples = Arrays.asList(new Apple());
    static List<Fruit> fruit = Arrays.asList(new Fruit());
    // Статический метод адаптируется к каждому вызову:
    static void f1() {
        Apple а = readExact(apples);
        Fruit f = readExact(fruit);
        f = readExact(apples);
    }
    // Но если речь идет о классе, тип устанавливается
    // при создании экземпляра класса:
    static class Reader<T> {
        T readExact (List <T> list) {
            return list.get(0);
        }
    }
    static void f2() {
        Reader<Fruit> fruitReader = new Reader<Fruit>();
        Fruit f = fruitReader.readExact(fruit);
//      Fruit а = fruitReader.readExact(apples); // Ошибка:
    // readExact(List<Fruit>) не может // применяться к (List<Apple>).
    }
    static class CovariantReader<T> {
        T readCovariant(List<? extends T> list) {
            return list.get(0);
        }
    }
    static void f3() {
        CovariantReader<Fruit> fruitReader = new CovariantReader<Fruit>();
        Fruit f = fruitReader.readCovariant(fruit);
        Fruit а = fruitReader.readCovariant(apples);
    }
    public static void main(String[] args){
        f1();
        f2();
        f3();
    }
}