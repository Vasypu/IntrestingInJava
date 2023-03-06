package generics;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 *  Проблемы
 */

// Класс Hourly не компилируется, потому что стирание сокращает Payable<Employee> и Payable<Hourly>
// до одного класса Payable, а приведенный выше код будет означать, что один интерфейс реализуется
// дважды. Интересно, что при удалении обобщенных параметров из обоих мест использования Payable
// (как это делает компилятор при стирании) код откомпилируется.
public interface Payable<T> {}

class Employee2 implements Payable<Employee2> {}
//class Hourly extends Employee2 implements Payable<Hourly> {}

// Без аннотации @SuppressWarnings компилятор выдает для pop() предупреждение «неконтролируемое приведение типа».
// Из-за стирания он не знает, безопасно ли приведение типа, и метод pop() не выполняет никакого реального приведения,
// T стирается по первому ограничению, которым по умолчанию является Object, так что pop() фактически просто преобразует
// Object в Object.
class FixedSizeStack<T> {
    private int index = 0;
    private Object[] storage;
    public FixedSizeStack(int size) { storage = new Object[size]; }
    public void push(T item) { storage[index++] = item; }
    @SuppressWarnings("unchecked")
    public T pop() { return (T)storage[--index]; }
}

class GenericCast {
    public static final int SIZE = 10;
    public static void main(String[] args) {
        FixedSizeStack<String> strings = new FixedSizeStack<String>(SIZE);
        for(String s : "A B C D E F G H I J".split(" "))
            strings.push(s);
        for(int i = 0; i < SIZE; i++) {
            String s = strings.pop();
            System.out.print(s + " ");
        }
    }
}

// Метод readObject() не может знать, что он читает, поэтому возвращает объект, к которому должно быть примерено преобразование
// типа. Но если закомментировать аннотацию @SuppressWarnings и откомпилировать программу, вы получите предупреждение.
class NeedCasting {
    @SuppressWarnings("unchecked")
    public void f(String[] args) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(args[0]));
        List<Widget> shapes = (List<Widget>) in.readObject();
    }
}

// Вы обязаны выполнить приведение типа, но при этом вам подсказывают, что делать этого не следует. Для решения следует применить
// новую форму приведения типа, появившуюся B java SE5, — приведение через обобщенный класс. Однако приведение к фактическому типу
// (List<Widget>) невозможно. Иначе говоря, вы не можете использовать вызов List<Widget>.class.cast(in.readObject()) и даже при
// добавлении дополнительного приведения типа: (List<Widget>)List.class.cast(in.readObject()) все равно будет выдано предупреждение.
class ClassCasting {
    @SuppressWarnings("unchecked")
    public void f(String[] args) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(args[0]));
        // Не компилируется:
//        List<Widget> lwl = List<Widget>.class.cast(in.readObject());
        List<Widget> lw2 = List.class.cast(in.readObject());
    }
}

// Перегрузка метода порождает идентичную сигнатуру типа из-за стирания.
// Вместо этого необходимо явно указать имена методов, если стертые аргументы не
// позволяют получить уникальный список аргументов:
class UseList<W,T> {
//    void f(List<T> v) {}
    void f(List<W> v) {}
}

class UseList2<W,T> {
    void fl(List<T> v) {}
    void f2(List<W> v) {}
}

// Перехват интерфейса базовым классом
class ComparablePet implements Comparable<ComparablePet> {
    public int compareTo(ComparablePet arg) { return 0; }
}

// Есть смысл сузить тип, с которым может сравниваться субкласс ComparablePet.
// Например, Cat может сравниваться только с другими объектами Cat.
// К сожалению, это решение не сработает. Когда для Comparable устанавливается
// аргумент ComparablePet, ни один реализующий класс не может сравниваться ни с
// чем, кроме ComparablePet:
//class Cat extends ComparablePet implements Comparable<Cat> {
    // Ошибка: Comparable не может наследоваться
    // с разными аргументами: <Cat> and <Pet>
//    public int compareTo(Cat arg) { return 0; }
//}

// Hamster показывает, что возможно повторно реализовать интерфейс, присутствующий в
// ComparablePet, — при условии его точного совпадения, включая параметры-типы.
class Hamster extends ComparablePet implements Comparable<ComparablePet> {
    public int compareTo(ComparablePet arg) { return 0; }
}

// Однако это ничем не отличается от переопределения методов базового класса, как
// видно на примере Gecko.
class Gecko extends ComparablePet {
    public int compareTo(ComparablePet arg) { return 0; }
}