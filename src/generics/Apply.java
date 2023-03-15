package generics;

import java.lang.reflect.Method;
import java.util.*;

/**
 *  Компенсация отсутствия латентной типизации часть 2.
 *  <p>
 *  Применение метода к последовательности
 *  <p>
 *  Рефлексия открывает интересные возможности, но передает всю проверку типов на стадию выполнения,
 *  что нежелательно во многих ситуациях. Если проверку типов можно обеспечить на стадии компиляции,
 *  обычно этот вариант более желателен. Но возможно ли совместить проверку типов во время компиляции
 *  с латентной типизацией?
 *  Рассмотрим пример, поясняющий суть проблемы. Допустим, требуется создать метод apply(), который
 *  будет применять произвольный метод к каждому объекту в последовательности. Похоже, в этой ситуации
 *  интерфейсы не подходят: интерфейсы слишком сильно ограничивают возможности описания «произвольного
 *  метода»
 */

// В методе Apply нам повезло, потому что в Java имеется встроенный интерфейс Iterable, который используется
// библиотекой контейнеров Java. По этой причине метод apply() может принять любой объект, реализующий
// интерфейс Iterable; к этой категории относятся все классы Collection (такие, как List). Но он также может
// принять любой другой объект при условии, что он будет реализовывать Iterable, — например, класс SimpleQueue,
public class Apply {
    public static <T, S extends Iterable<? extends T>> void apply(S seq, Method f, Object... args) {
        try {
            for (T t : seq)
                f.invoke(t, args);
        } catch(Exception e) {
            // Сбои являются ошибками программиста
            throw new RuntimeException(e);
        }
    }
}

class Shape {
    public void rotate() { System.out.println(this + " rotate"); }
    public void resize(int newSize) { System.out.println(this + " resize " + newSize); }
}

class Square extends Shape {}

class FilledList<T> extends ArrayList<T> {
    public FilledList(Class<? extends T> type, int size) {
        try {
            for (int i = 0; i < size; i++)
                // Предполагается конструктор по умолчанию:
                add(type.newInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class ApplyTest {
    public static void main(String[] args) throws Exception {
        List<Shape> shapes = new ArrayList<Shape>();
        for (int i = 0; i < 10; i++)
            shapes.add(new Shape());
        Apply.apply(shapes, Shape.class.getMethod("rotate"));
        Apply.apply(shapes, Shape.class.getMethod("resize", int.class), 5);
        List<Square> squares = new ArrayList<Square>();
        for (int i = 0; i < 10; i++)
            squares.add(new Square());
        Apply.apply(squares, Shape.class.getMethod("rotate"));
        Apply.apply(squares, Shape.class.getMethod("resize", int.class), 5);
        Apply.apply(new FilledList<Shape>(Shape.class, 10), Shape.class.getMethod("rotate"));
        Apply.apply(new FilledList<Shape>(Square.class, 10), Shape.class.getMethod("rotate"));
        SimpleQueue<Shape> shapeQ = new SimpleQueue<Shape>();
        for (int i = 0; i < 5; i++) {
            shapeQ.add(new Shape());
            shapeQ.add(new Square());
        }
        Apply.apply(shapeQ, Shape.class.getMethod("rotate"));
    }
}

class SimpleQueue<T> implements Iterable<T> {
    private LinkedList<T> storage = new LinkedList<T>();
    public void add(T t) { storage.offer(t); }
    public T get() { return storage.poll(); }
    public Iterator<T> iterator() { return storage.iterator(); }
}

// Для примера мы обобщим идею Filledlist и создадим параметризованный метод fill(), который получает последовательность
// и заполняет ее при помощи Generator. Попытка запрограммировать это решение HaJava сталкивается с проблемой: не существует
// удобного интерфейса Addable (сходного с интерфейсом Iterable из предыдущего примера). Итак, вместо формулировки «любой
// объект, для которого можно вызвать add()» необходимо использовать формулировку «подтип Collection». Полученный код не
// является полностью обобщенным, потому что он должен ограничиваться для работы с реализациями Collection. При попытке
// использования класса, не реализующего Collection, наш обобщенный код работать не будет.

// Не работает с "любым объектом, содержащим add()". Интерфейса
// "Addable" не существует, так что приходится использовать
// Collection. В данном случае полноценное обобщение невозможно.
class Fill {
    public static <T> void fill(Collection<T> collection, Class<? extends T> classToken, int size) {
        for(int i = 0; i < size; i++)
            // Предполагается наличие конструктора по умолчанию:
            try {
                collection.add(classToken.newInstance());
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
    }
}

class Contract {
    private static long counter = 0;
    private final long id = counter++;
    public String toString() { return getClass().getName() + " " + id; }
}

class TitleTransfer extends Contract {}

// В приведенном выше случае, поскольку проектировщики Java (по понятным причинам) не видели необходимости в интерфейсе Addable,
// мы ограничиваемся иерархией Collection, а класс SimpleQueue, несмотря на наличие у него метода add(), не подойдет. Код,
// ограниченный использованием collection, вряд лй можно считать полностью обобщенным. С латентной типизацией такой проблемы не будет
class FillTest {
    public static void main(String[] args) {
        List<Contract> contracts = new ArrayList<Contract>();
        Fill.fill(contracts, Contract.class, 3);
        Fill.fill(contracts, TitleTransfer.class, 2);
        for (Contract c : contracts)
            System.out.println(c);
        SimpleQueue<Contract> contractQueue = new SimpleQueue<Contract>();
        // Не сработает. Метод fill() недостаточно обобщен:
        // Fill.fill(contractQueue, Contract.class, 3);
    }
}