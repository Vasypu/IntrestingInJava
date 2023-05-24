package containers;

import arrays.CountingGenerator;
import arrays.RandomGenerator;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 *  Генераторы для Мар
 *  <p>
 *  Генераторы можно использовать и для Mар, но для этого потребуется класс Pair, поскольку для заполнения Мар
 *  при каждом вызове метода next() генератора должна производиться пара объектов (ключ и значение).
 */

class Pair<K,V> {
    public final K key;
    public final V value;
    public Pair(K k, V v) {
        key = k;
        value = v;
    }
}

// Адаптер Мар теперь может использовать различные комбинации Generator, Iterable и констант для заполнения объектов
// инициализации Мар:
class MapData<K,V> extends LinkedHashMap<K,V> {
    // Один генератор Pair:
    public MapData(Generator<Pair<K,V>> gen, int quantity) {
        for (int i = 0; i < quantity; i++) {
            Pair<K, V> p = gen.next();
            put(p.key, p.value);
        }
    }
    // Два разных генератора:
    public MapData(Generator<K> genK, Generator<V> genV, int quantity) {
        for (int i = 0; i < quantity; i++) {
            put(genK.next(), genV.next());
        }
    }
    // Генератор ключа и одно значение:
    public MapData(Generator<K> genK, V value, int quantity) {
        for(int i = 0; i < quantity; i++) {
            put(genK.next(), value);
        }
    }
    // Iterable и генератор значения:
    public MapData(Iterable<K> genK, Generator<V> genV) {
        for (K key : genK) {
            put(key, genV.next());
        }
    }
    // Iterable и одно значение:
    public MapData(Iterable<K> genK, V value) {
        for(K key : genK) {
            put(key, value);
        }
    }
    // Обобщенные вспомогательные методы:
    public static <K,V> MapData<K,V> map(Generator<Pair<K,V>> gen, int quantity) { return new MapData<K,V>(gen, quantity); }
    public static <K,V> MapData<K,V> map(Generator<K> genK, Generator<V> genV, int quantity) { return new MapData<K, V>(genK, genV, quantity); }
    public static <K,V> MapData<K,V> map(Generator<K> genK, V value, int quantity) { return new MapData<K,V>(genK, value, quantity); }
    public static <K,V> MapData<K,V> map(Iterable<K> genK, Generator<V> genV) { return new MapData<K, V>(genK, genV); }
    public static <K,V> MapData<K,V> map(Iterable<K> genK, V value) { return new MapData<K, V>(genK, value); }
}

// Генератор Letters также реализует Iterable, предоставляя Iterator; это позволяет использовать его для тестирования
// методов MapData.map(), которые работают с Iterable.
class Letters implements Generator<Pair<Integer,String>>,Iterable<Integer> {
    private int size = 9;
    private int number = 1;
    private char letter = 'A';
    public Pair<Integer,String> next() { return new Pair<Integer, String>(number++, "" + letter++); }
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            public Integer next() { return number++; }
            public boolean hasNext() { return number < size; }
            public void remove() { throw new UnsupportedOperationException(); }
        };
    }
}

public class MapDataTest {
    public static void main(String[] args) {
        // Генератор Pair:
        System.out.println(MapData.map(new Letters(), 11));
        // Два разных генератора:
        System.out.println(MapData.map(new CountingGenerator.Character(), new RandomGenerator.String(3), 8));
        // Генератор ключей и одно значение:
        System.out.println(MapData.map(new CountingGenerator.Character(), "Value", 6));
        // Iterable и генератор значений:
        System.out.println(MapData.map(new Letters(), new RandomGenerator.String(3)));
        // Iterable и одно значение:
        System.out.println(MapData.map(new Letters(), "Рор"));
    }
}