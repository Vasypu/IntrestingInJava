package containers;

import java.util.*;

/**
 *  Сортировка и поиск в списках
 *  <p>
 *  Инструменты, предназначенные для сортировки списков и поиска в них, имеют точно такие же имена и описания,
 *  как те, что использовались нами для проведения аналогичных операций над массивами, но это статические
 *  методы класса Collections, в то время как для массивов применялся класс Arrays. Вот пример с использованием
 *  данных list из Utilities.java.
 *  <p>
 *  Как и в случае с поиском и сортировкой в массивах, при сортировке с использованием Comparator этот же объект
 *  Comparator должен использоваться и при вызове binarySearch().
 */
public class ListSortSearch {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>(Utilities.list);
        list.addAll(Utilities.list);
        System.out.println(list);
        Collections.shuffle(list, new Random(47));
        System.out.println("Shuffled: " + list);
        // Использование ListIterator для отсечения последних элементов:
        ListIterator<String> it = list.listIterator(10);
        while(it.hasNext()) {
            it.next();
            it.remove();
        }
        System.out.println("Trimmed: " + list);
        Collections.sort(list);
        System.out.println("Sorted: " + list);
        String key = list.get(7);
        int index = Collections.binarySearch(list, key);
        System.out.println("Location of " + key + " is " + index + ", list.get(" + index + ") = " + list.get(index));
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
        System.out.println("Case-insensitive sorted: " + list);
        key = list.get(7);
        index = Collections.binarySearch(list, key, String.CASE_INSENSITIVE_ORDER);
        System.out.println("Location of " + key + " is " + index + ", list.get(" + index + ") = " + list.get(index));
    }
}
