package colections;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *  SortedSet
 *  <p>
 *  Элементы отсортированного множества SortedSet гарантированно хранятся в порядке сортировки. Элементы в множестве
 *  SortedSet отсортированы согласно их функциям сравнения, а не в соответствии с очередностью добавления.
 *  <p>
 *  Comparator comparator(): возвращает объект Comparator, использующийся для данного множества, или null для естественного упорядочивания.
 *  Object first(); производит наименьший элемент множества.
 *  Object last(): производит наибольший элемент множества.
 *  SortedSet subSet(fromElement, toElement): производит подмножество данного множества, в которое включены элементы от fromElement (включительно) до toElement (не включая).
 *  SortedSet headSet(toElement): производит подмножество, содержащее все элементы, меньшие toElement.
 *  SortedSet tailSet(fromElement): производит подмножество, содержащее все элементы, большие либо равные fromElement.
 */
public class SortedSetDemo {
    public static void main(String[] args) {
        SortedSet<String> sortedSet = new TreeSet<String>();
        Collections.addAll(sortedSet, "one two three four five six seven eight".split(" "));
        System.out.println(sortedSet);
        String low = sortedSet.first();
        String high = sortedSet.last();
        System.out.println(low);
        System.out.println(high);
        Iterator<String> it = sortedSet.iterator();
        for (int i = 0; i <= 6; i++) {
            if (i == 3) low = it.next();
            if (i == 6) high = it.next();
            else it.next();
        }
        System.out.println(low);
        System.out.println(high);
        System.out.println(sortedSet.subSet(low, high));
        System.out.println(sortedSet.headSet(high));
        System.out.println(sortedSet.tailSet(low));
    }
}