package colections;

import java.util.*;

/**
 *  Идиома "Метод-Адаптер" позволяет использовать foreach с другими разновидностями Iterable.
 *
 *
 */

//  Если у вас имеется один интерфейс, а нужен другой, проблема решается написанием адаптера.
//  В данном случае я хочу добавить возможность получения обратного итератора к прямому итератору
//  по умолчанию, поэтому обычное переопределение не подходит. Вместо этого мы добавим метод,
//  создающий объект Iterable, который может использоваться в команде foreach.
public class ReversibleArrayList<T> extends ArrayList<T> {

    public ReversibleArrayList(Collection<T> c) { super(c); }

    public Iterable<T> reversed() {
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    int current = size() - 1;

                    public boolean hasNext() { return current > -1; }
                    public T next() { return get(current--); }
                    public void remove() { // Не реализован
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}

class AdapterMethodIdiom {
    public static void main(String[] args) {
        ReversibleArrayList<String> ral = new ReversibleArrayList<>(
                Arrays.asList("To be or not to be".split(" ")));
        // Получение обычного итератора при помощи iterator():
        for (String s : ral)
            System.out.print(s + " ");
        System.out.println();
        // Передача реализации Iterable no вашему выбору
        for (String s : ral.reversed())
            System.out.print(s + " ");
    }
}

//  Из выходных данных видно, что метод Collections.shuffle() не влияет на исходный массив,
//  а только перемешивает ссылки в shuffled. Это истинно только потому, что метод randomized()
//  «заворачивает» результат Arrays.asList() в ArrayList. Если контейнер List, полученный вызовом
//  Arrays.asList(), будет перемешиваться напрямую, это повлияет на базовый массив.
class MultiIterableClass extends IterableClass {
    public Iterable<String> reversed() {
        return new Iterable<String>() {
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    int current = words.length - 1;

                    public boolean hasNext() { return current > -1; }
                    public String next() { return words[current--]; }
                    public void remove() { // Не реализован
                         throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    Iterable<String> randomized() {
        return new Iterable<String>() {
            public Iterator<String> iterator() {
                List<String> shuffled = new ArrayList<>(Arrays.asList(words));
                Collections.shuffle(shuffled, new Random(47));
                return shuffled.iterator();
            }
        };
    }
    public static void main(String[] args) {
        MultiIterableClass mic = new MultiIterableClass();
        for (String s : mic.reversed())
            System.out.print(s + " ");
        System.out.println();
        for (String s : mic.randomized())
            System.out.print(s + " ");
        System.out.println();
        for (String s : mic)
            System.out.print(s + " ");
    }
}

//В первом случае вывод Arrays.asList() передается конструктору ArrayList(); при этом
// создается объект ArrayList, ссылающийся на элементы ia. Перемешивание этих ссылок не
// изменяет массив. Однако если использовать результат Arrays.asList(ia) напрямую,
// перемешивание изменит порядок ia. Важно понимать, что Arrays.asList() создает объект
// List, использующий базовый массив как физическую реализацию.
class ModifyingArraysAsList {
    public static void main(String[] args) {
        Random rand = new Random(47);
        Integer[] ia = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<Integer> list1 = new ArrayList<>(Arrays.asList(ia));
        System.out.println("До перемешивания: " + list1);
        Collections.shuffle(list1, rand);
        System.out.println("После перемешивания: " + list1);
        System.out.println("Массив: " + Arrays.toString(ia));
        List<Integer> list2 = Arrays.asList(ia);
        System.out.println("До перемешивания: " + list2);
        Collections.shuffle(list2, rand);
        System.out.println("После перемешивания: " + list2);
        System.out.println("Массив: " + Arrays.toString(ia));
    }
}