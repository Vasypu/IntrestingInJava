package colections;

import containers.Countries;
import containers.MapEntry;

import java.util.*;

/**
 *  Понимание hashCode()
 *  <p>
 *  Для чего вообще нужно хеширование? Для поиска объекта по другому объекту.
 *  Но это можно сделать с помощью TreeMap или реализовать собственную карту (Мар).
 *  В отличие от реализации с хешированием следующий пример реализует Мар на базе двух контейнеров ArrayList.
 */
public class SlowMap<K,V> extends AbstractMap<K,V> {
    private List<K> keys = new ArrayList<K>();
    private List<V> values = new ArrayList<V>();

    public V put(K key, V value) {
        V oldValue = get(key); // Старое значение или null
        if (!keys.contains(key)) {
            keys.add(key);
            values.add(value);
        } else
            values.set(keys.indexOf(key), value);
        return oldValue;
    }

    public V get(Object key) { // key относится к типу Object, не К
        if(!keys.contains(key))
            return null;
        return values.get(keys.indexOf(key));
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<Map.Entry<K, V>>();
        Iterator<K> ki = keys.iterator();
        Iterator<V> vi = values.iterator();
        while (ki.hasNext())
            set.add(new MapEntry<K, V>(ki.next(), vi.next()));
        return set;
    }

    public static void main(String[] args) {
        SlowMap<String, String> m = new SlowMap<String, String>();
        m.putAll(Countries.capitals(15));
        System.out.println(m);
        System.out.println(m.get("BULGARIA"));
        System.out.println(m.entrySet());
    }
}