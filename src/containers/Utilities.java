package containers;

import java.util.*;

/**
 *  Вспомогательные средства работы с коллекциями
 *  <p>
 *  Также существуют некоторые автономные вспомогательные средства для работы с контейнерами, представленные
 *  статическими методами класса java.util.Collections.
 *  <p>
 *  Как упоминалось ранее, список List или массив необходимо отсортировать перед вызовом binarySearch().
 */
public class Utilities {
    static List<String> list = Arrays.asList(
            "one Two three Four five six one".split(" "));
    public static void main(String[] args) {
        System.out.println(list);
        System.out.println("'list' disjoint (Four)?: " +
                Collections.disjoint(list,
                        Collections.singletonList("Four")));
        System.out.println("max: " + Collections.max(list));
        System.out.println("min: " + Collections.min(list));
        System.out.println("max w/ comparator: " + Collections.max(list,
                String.CASE_INSENSITIVE_ORDER));
        System.out.println("min w/ comparator: " + Collections.min(list,
                String.CASE_INSENSITIVE_ORDER));
        List<String> sublist = Arrays.asList("Four five six".split(" "));
        System.out.println("indexOfSubList: " + Collections.indexOfSubList(list, sublist));
        System.out.println("lastIndexOfSubList: " + Collections.lastIndexOfSubList(list, sublist));
        Collections.replaceAll(list, "one", "Yo");
        System.out.println("replaceAll: " + list);
        Collections.reverse(list);
        System.out.println("reverse: " + list);
        Collections.rotate(list, 3);
        System.out.println("rotate: " + list);
        List<String> source = Arrays.asList("in the matrix".split(" "));
        Collections.copy(list, source);
        System.out.println("copy: " + list);
        Collections.swap(list, 0, list.size() - 1);
        System.out.println("swap: " + list);
        Collections.shuffle(list, new Random(47));
        System.out.println("shuffled: " + list);
        Collections.fill(list, "pop");
        System.out.println("fill: " + list);
        System.out.println("frequency of 'pop': " + Collections.frequency(list, "рор"));
        List<String> dups = Collections.nCopies(3, "snap");
        System.out.println("dups: " + dups);
        System.out.println("'list' disjoint 'dups'?: " + Collections.disjoint(list, dups));
        // Получение Enumeration "в старом стиле":
        Enumeration<String> e = Collections.enumeration(dups);
        Vector<String> v = new Vector<String>();
        while(e.hasMoreElements())
            v.addElement(e.nextElement());
        // Преобразование Vector "в старом стиле"
        // в List с использованием Enumeration:
        ArrayList<String> arrayList = Collections.list(v.elements());
        System.out.println("arrayList: " + arrayList);
    }
}
