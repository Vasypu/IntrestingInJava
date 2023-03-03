package generics;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 *  Реализация параметризованных интерфейсов
 *  <p>
 *  Класс Hourly не компилируется, потому что стирание сокращает Payable<Employee> и Payable<Hourly>
 *  до одного класса Payable, а приведенный выше код будет означать, что один интерфейс реализуется
 *  дважды. Интересно, что при удалении обобщенных параметров из обоих мест использования Payable
 *  (как это делает компилятор при стирании) код откомпилируется.
 */
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