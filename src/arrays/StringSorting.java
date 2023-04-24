package arrays;

import java.util.Arrays;
import java.util.Collections;

/**
 *  Сортировка массива
 *  <p>
 *  Алгоритм сортировки строк является лексикографическим: все слова начинающиеся с прописных букв,
 *  помещаются в начало списка, и только после них следуют слова с первой строчной буквой. Чтобы сгруппировать
 *  слова без учета регистра, используйте режим String. CASE_INSENSITIVE_ ORDER, как показано в последнем
 *  вызове sort() предыдущего примера.
 *  <p>
 *  Алгоритм сортировки из стандартной библиотеки Java разработан с учетом максимальной эффективности для
 *  сортируемого типа: для простейших типов используется быстрая сортировка (quick sort), а для объектов
 *  применяется устойчивая сортировка слиянием (stable merge).
 */
public class StringSorting {
    public static void main(String[] args) {
        String[] sa = Generated.array(new String[20], new RandomGenerator.String(5));
        System.out.println("Before sort: " + Arrays.toString(sa));
        Arrays.sort(sa);
        System.out.println("After sort: " + Arrays.toString(sa));
        Arrays.sort(sa, Collections.reverseOrder());
        System.out.println("Reverse sort: " + Arrays.toString(sa));
        Arrays.sort(sa, String.CASE_INSENSITIVE_ORDER);
        System.out.println("Case-insensitive sort: " + Arrays.toString(sa));
    }
}