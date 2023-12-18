package containers;

import java.util.*;

/**
 *  Получение неизменяемых коллекций и карт
 *  <p>
 *  Довольно часто бывает удобно иметь версию коллекции (Collection) или карты (Мар), доступную только для чтения.
 *
 */
public class ReadOnly {
    static Collection<String> data = new ArrayList<String>(Countries.names(6));
    public static void main(String[] args) {
        Collection<String> c = Collections.unmodifiableCollection(new ArrayList<String>(data));
        System.out.println(c); // Чтение разрешено
        //! c.add("one"); // Но изменение невозможно.
        List<String> a = Collections.unmodifiableList(new ArrayList<String>(data));
        ListIterator<String> lit = a.listIterator();
        System.out.println(lit.next()); // Чтение разрешено
        //! lit.add("one"); // Но изменение невозможно.
        Set<String> s = Collections.unmodifiableSet(new HashSet<>(data));
        System.out.println(s); // Чтение разрешено
        //! s.add("one");
        // Но изменение невозможно.
        // Для SortedSet:
        Set<String> ss = Collections.unmodifiableSortedSet(new TreeSet<>(data));
        Map<String, String> m = Collections.unmodifiableMap(new HashMap<>(Countries.capitals(6)));
        System.out.println(m); // Чтение разрешено
        // ! ra.put("Ralph", "Howdy!");
        // Для SortedMap:
        Map<String, String> sm = Collections.unmodifiableSortedMap(new TreeMap<>(Countries.capitals(6)));
    }
}
