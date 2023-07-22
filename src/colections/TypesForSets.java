package colections;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *  Set и порядок хранения
 *  <p>
 *  При создании собственных типов учтите, что множество должно каким-то образом поддерживать порядок хранения.
 *  Способ поддержания этого порядка зависит от реализации Set. Таким образом, разные реализации Set могут различаться
 *  не только поведением, но и требованиями к типу объекта, который можно поместить в конкретную реализацию Set:
 *  <p>
 *  Каждый элемент, добавляемый в множество Set, должен быть уникальным; в противном случае дубликат не добавляется.
 *  Все объекты, помещаемые в Set, должны определять метод equals() для выполнения сравнения. Множество Set не гарантирует
 *  того, что хранимые в нем элементы будут располагаться в определенном порядке.
 *  <p>
 *  HashSet
 *  Для реализаций Set, у которых первостепенное значение имеет быстрый поиск. Хранимые объекты должны определять метод hashCode()
 *  TreeSet
 *  Упорядоченное множество, реализованное на основе дерева. Из него можно извлекать упорядоченную последовательность элементов.
 *  Элементы также должны реализовать интерфейс Comparable.
 *  LinkedHashSet
 *  Обладает аналогичной HashSet скоростью поиска, а также своими силами (используя связанный список) запоминает порядок добавления
 *  элементов (порядок вставки). Таким образом, при переборе элементов этого множества они следуют в порядке вставки. Элементы
 *  также должны определять hashCode()
 */
class SetType {
    int i;
    public SetType(int n) { i = n; }
    public boolean equals(Object o) {
        return o instanceof SetType && (i == ((SetType) o).i);
    }
    public String toString () { return Integer.toString(i); }
}

class HashType extends SetType {
    public HashType(int n) { super(n); }
    public int hashCode() { return i; }
}

class TreeType extends SetType implements Comparable<TreeType> {
    public TreeType(int n) { super(n); }
    public int compareTo(TreeType arg) {
        return (arg.i < i ? -1 : (arg.i == i ? 0 : 1));
    }
}

public class TypesForSets {
    static <T> Set<T> fill(Set<T> set, Class<T> type) {
        try {
            for (int i = 0; i < 10; i++)
                set.add(type.getConstructor(int.class).newInstance(i));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return set;
    }
    static <T> void test(Set<T> set, Class<T> type) {
        fill(set, type);
        fill(set, type); // Попытка добавления дубликатов
        fill(set, type);
        System.out.println(set);
    }

    public static void main(String[] args) {
        test(new HashSet<HashType>(), HashType.class);
        test(new LinkedHashSet<HashType>(), HashType.class);
        test(new TreeSet<TreeType>(), TreeType.class);
        // Следующие операции не работают:
        test(new HashSet<SetType>(), SetType.class);
        test(new HashSet<TreeType>(), TreeType.class);
        test(new LinkedHashSet<SetType>(), SetType.class);
        test(new LinkedHashSet<TreeType>(), TreeType.class);
        try {
            test(new TreeSet<SetType>(), SetType.class);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            test(new TreeSet<HashType>(), HashType.class);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}