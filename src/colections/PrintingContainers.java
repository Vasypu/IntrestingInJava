package colections;

import java.util.*;

/**
 *  Вывод содержимого контейнеров. Различия контейнеров.
 *
 *  ArrayList и LinkedList относятся к семейству List. Элементы хранятся в порядке вставки.
 *  LinkedList содержит больше операций чем ArrayList
 *
 *  HashSet, TreeSet и LinkedHashSet относится к семейству Set. В множествах Set каждый
 *  элемент хранится в одном экземпляре.
 *  - В HashSet порядок элементов определят алгоритм, благодаря котором обеспечивается
 *  минимальное время выборки элементов.
 *  - В TreeSet объекты хранятся в отсортированном по возрастанию в порядке сравнения.
 *  - В LinkedHashSet объекты хранятся в порядке добавления.
 *
 *  Map позволяет искать элементы по ключу.
 *  - HashMap, как и HashSet обеспечивает скорость выборки.
 *  - TreeMap хранит ключи отсортированными по возрастанию.
 *  - LinkedHashMap хранит ключи в порядке вставки.
 */

public class PrintingContainers {
    static Collection fill(Collection<String> collection) {
        collection.add("rat");
        collection.add("cat");
        collection.add("dog");
        collection.add("dog");
        return collection;
    }
    static Map fill(Map<String, String> map) {
        map.put("rat", "Fuzzy");
        map.put("cat", "Rags");
        map.put("dog", "Bosco");
        map.put("dog", "Spot");
        return map;
    }
    public static void main(String[] args) {
        System.out.println(fill(new ArrayList<String>()));
        System.out.println(fill(new LinkedList<String>()));
        System.out.println(fill(new HashSet<String>()));
        System.out.println(fill(new TreeSet<String>()));
        System.out.println(fill(new LinkedHashSet<String>()));
        System.out.println(fill(new HashMap<String, String>()));
        System.out.println(fill(new TreeMap<String, String>()));
        System.out.println(fill(new LinkedHashMap<String, String>()));
    }
}
