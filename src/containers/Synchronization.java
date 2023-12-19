package containers;

import java.util.*;

/**
 *  Синхронизация коллекции или карты
 *  <p>
 *  Синхронизация — это важное понятие из области многозадачности, более сложной темы. Сейчас я просто отмечу,
 *  что класс Collections предоставляет способ автоматически синхронизировать контейнер целиком.
 */
public class Synchronization {
    public static void main(String[] args) {
        Collection<String> с = Collections.synchronizedCollection(new ArrayList<String>());
        List<String> list = Collections.synchronizedList(new ArrayList<String>());
        Set<String> s = Collections.synchronizedSet(new HashSet<String>());
        Set<String> ss = Collections.synchronizedSortedSet(new TreeSet<String>());
        Map<String, String> га = Collections.synchronizedMap(new HashMap<String, String>());
        Map<String, String> sm = Collections.synchronizedSortedMap(new TreeMap<String, String>());
    }
}