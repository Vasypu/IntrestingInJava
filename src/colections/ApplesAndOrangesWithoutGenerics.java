package colections;

import java.util.ArrayList;

/**
 *  Параметризованные и типизированные контейнеры.
 *  <p>
 *  Первый пример добавления в коллекцию (без явного указания типа) объектов разного типа.
 *  Второй пример с явным определением типа для коллекции.
 *  Третий пример с коллекцией для хранения экземпляров класса наследованных от базового.
 */

class Apple {
    private static long counter;
    private final long id = counter++;
    public long id() { return id; }
}

class Orange {}

public class ApplesAndOrangesWithoutGenerics {
    @SuppressWarnings("unchecked")                    // подавляет предупреждения при добавлении в список
    public static void main(String[] args) {
        ArrayList apples = new ArrayList();
        for (int i = 0; i < 3; i++) {
            apples.add(new Apple());
        }
        apples.add(new Orange());                   // Объект Orange обнаружится только во время выполнения программы
        for (int i = 0; i < apples.size(); i++)
            ((Apple)apples.get(i)).id();
    }
}

class ApplesAndOrangesWithGenerics {
    public static void main(String[] args) {
        ArrayList<Apple>  apples = new ArrayList<Apple>();
        for (int i = 0; i < 3; i++) {
            apples.add(new Apple());
        }
//        apples.add(new Orange());                   // Ошибка компиляции
        for (Apple apple : apples)
            System.out.println(apple.id());
    }
}

class Granny extends Apple {}
class Gala extends Apple {}
class Fuji extends Apple {}
class Braeburn extends Apple {}

class GenericsAndUpcasting {
    public static void main(String[] args) {
        ArrayList<Apple>  apples = new ArrayList<>();
        apples.add(new Granny());
        apples.add(new Gala());
        apples.add(new Fuji());
        apples.add(new Braeburn());

        for (Apple apple : apples)
            System.out.println(apple);
    }
}