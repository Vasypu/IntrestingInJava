package colections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 *  Foreach и Iterable
 *
 *  Интерфейс Iterable реализуется многими классами, прежде
 *  всего всеми реализациями Collection (но не Мар).
 */

public class IterableClass implements Iterable<String> {
    protected String[] words = ("And that is how " +
            "we know the Earth to be banana-shaped.").split(" ");
    public Iterator<String> iterator() {
        return new Iterator<>() {
            private int index = 0;
            public boolean hasNext() {
                return index < words.length;
            }
            public String next() { return words[index++]; }
            public void remove() { // Не реализован throw new UnsupportedOperationException();
            }
        };
    }
    public static void main(String[] args) {
        for(String s : new IterableClass())
            System.out.print(s + " ");
    }
}

//Метод System.getenv()1 возвращает Мар, метод entrySet() создает
// контейнер Set с элементами Map.Entry; контейнер Set реализует
// Iterable, поэтому он может использоваться в циклах foreach.
class EnvironmentVariables {
    public static void main(String[] args) {
        for (Map.Entry entry : System.getenv().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

//Команда foreach работает с массивом или любой другой реализацией
// Iterable, но это не означает, что массив автоматически реализует
// Iterable, и не подразумевает автоматической упаковки:
class ArrayIsNotIterable {
    static <T> void test(Iterable<T> ib) {
        for (T t : ib)
            System.out.print(t + " ");
    }
    public static void main(String[] args) {
        test(Arrays.asList(1, 2, 3));
        String[] strings = {"А", "B", "С" };
        // Массив работает в foreach, но не является Iterable:
        //! test(strings);
        // Его необходимо явно преобразовать в Iterable:
        test(Arrays.asList(strings));
    }
}