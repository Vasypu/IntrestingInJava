package generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *  Моделирование латентной типизации с использованием адаптеров
 *  <p>
 *  Латентная типизация в некотором смысле создает неявный интерфейс, содержащий нужные методы. Получается,
 *  что если мы напишем необходимый интерфейс вручную (поскольку Java не сделает это за нас), это должно
 *  решить проблему отсутствия латентной типизации в java.
 *  Адаптеры приспосабливают существующие классы для предоставления нужного интерфейса при относительно
 *  небольшом объеме кода.
 *  <p>
 *  В Fill2Test.main() продемонстрировано использование разных типов адаптеров. Сначала тип Collection
 *  адаптируется классом AddableCollectionAdapter. Вторая версия использует обобщенный вспомогательный метод;
 *  вы видите, как обобщенный метод фиксирует тип, чтобы его не приходилось записывать явно, — удобный трюк,
 *  который делает код более элегантным.
 */
interface Addable<T> { void add(T t); }

// Класс Fill2 не требует Collection, в отличие от Fill. Вместо этого ему требуется любой объект, реализующий
// Addable, а интерфейс Addable был написан только для Fill — это проявление латентного типа.
public class Fill2 {
    // Версия с маркером:
    public static <T> void fill(Addable<T> addable, Class<? extends T> classToken, int size) {
        for(int i = 0; i < size; i++)
            try {
                addable.add(classToken.newInstance());
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
    }
    // Версия с генератором:
    public static <T> void fill(Addable<T> addable, Generator<T> generator, int size) {
        for (int i = 0; i < size; i++)
            addable.add(generator.next());
    }
}

// Для адаптации базового типа необходимо использовать композицию.
// Включение поддержки Addable в произвольный контейнер Collection
// с использованием композиции:
class AddableCollectionAdapter<T> implements Addable<T> {
    private Collection<T> c;
    public AddableCollectionAdapter(Collection<T> с) { this.c = с; }
    public void add(T item) { c.add(item); }
}

// Вспомогательный класс для автоматической фиксации типа:
class Adapter {
    public static <T> Addable<T> collectionAdapter(Collection<T> c) {
        return new AddableCollectionAdapter<T>(c);
    }
}

// Для адаптации конкретного типа можно применить наследование.
// Включение Addable в SimpleQueue посредством наследования:
class AddableSimpleQueue<T> extends SimpleQueue<T> implements Addable<T> {
    public void add(T item) { super.add(item); }
}

class Fill2Test {
    public static void main(String[] args) {
        // Адаптация Collection:
        List<Coffee> carrier = new ArrayList<>();
        Fill2.fill(new AddableCollectionAdapter<Coffee>(carrier), Coffee.class, 3);
        // Вспомогательный метод фиксирует тип:
        Fill2.fill(Adapter.collectionAdapter(carrier), Latte.class, 2);
        for(Coffee c: carrier)
            System.out.println(c);
        System.out.println("---------------------");
        // Использование адаптированного класса:
        AddableSimpleQueue<Coffee> coffeeQueue = new AddableSimpleQueue<Coffee>();
        Fill2.fill(coffeeQueue, Mocha.class, 4);
        Fill2.fill(coffeeQueue, Latte.class, 1);
        for(Coffee c: coffeeQueue)
            System.out.println(c);
    }
}