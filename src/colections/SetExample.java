package colections;

import java.util.*;

/**
 *  Set
 *
 *  Контейнер Set сохраняет не более одного экземпляра каждого объекта-значения.
 *  При попытке добавить более одного экземпляра эквивалентного объекта Set
 *  предотвращает появление дубликата.
 *
 *  TreeSet хранит элементы отсортированными в специальной структуре данных
 *  красно-черном дереве, тогда как HashSet применяет хеширование.
 *  LinkedHashSet также использует хеширование для повышения скорости поиска
 *  по ключу, но выглядит все так, словно элементы сохраняются в связанном
 *  списке в порядке вставки.
 *
 *  В Set добавляются десять тысяч случайных чисел от 0 до 29; понятно, что
 *  в каждое значение добавляется огромное количество дубликатов. Однако мы
 *  видим, что в итоговом множестве каждое значение присутствует только в
 *  одном экземпляре.
 */
public class SetExample {
    public static void main(String[] args) {
        Random rand = new Random(47);
        Set<Integer> intset = new HashSet<>();
        for (int i = 0; i < 10000; i++)
            intset.add(rand.nextInt(30));
        System.out.println(intset);

        SortedSet<Integer> intSortSet = new TreeSet<>();
        for(int i = 0; i < 10000; i++)
            intSortSet.add(rand.nextInt(30));
        System.out.println(intSortSet);

        Set<String> set1 = new HashSet<String>();
        Collections.addAll(set1, "A B C D E F G H I 3 K L".split(" "));
        set1.add("M");
        System.out.println("H: " + set1.contains("H"));
        System.out.println("N: " + set1.contains("N"));
        Set<String> set2 = new HashSet<String>();
        Collections.addAll(set2, "H I 3 K L".split(" "));
        System.out.println("set2 in set1: " + set1.containsAll(set2));
        set1.remove("H");
        System.out.println("set1: " + set1);
        System.out.println("set2 in set1: " + set1.containsAll(set2));
        set1.removeAll(set2);
        System.out.println("set2 removed from set1: " + set1);
        Collections.addAll(set1, "X Y Z".split(" "));
        System.out.println("'X Y Z' added to set1: " + set1);
    }
}
