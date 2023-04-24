package arrays;

import java.util.Arrays;

/**
 *  Как только массив отсортирован, в нем можно быстро найти определенный элемент, привлекая для
 *  этого методы Arrays.binarySearch(). Метод Arrays.binarySearch() возвращает большее или равное
 *  нулю значение, если при поиске был найден нужный элемент. В противном случае методом возвращается
 *  отрицательное значение, представляющее позицию, где следует вставить элемент, если бы отсортированный
 *  массив поддерживался «вручную».
 *  <p>
 *  Если в массиве присутствуют дубликаты, процедура поиска не гарантирует, какой именно из дубликатов будет
 *  найден. Используемый алгоритм не был разработан в расчете на дубликаты, хотя их присутствие не запрещается.
 */
public class ArraySearching {
    public static void main(String[] args) {
        Generator<Integer> gen = new RandomGenerator.Integer(1000);
        int[] a = ConvertTo.primitive(Generated.array(new Integer[25], gen));
        Arrays.sort(a);
        System.out.println("Sorted array: " + Arrays.toString(a));
        while(true) {
            int r = gen.next();
            int location = Arrays.binarySearch(a, r);
            if (location >= 0) {
                System.out.println("Location of " + r + " is " + location +
                        ", a[" + location + "] = " + a[location]);
                break; // Out of while loop
            }
        }
    }
}

// Если вы отсортировали массив объектов с использованием интерфейса Comparator то придется включить
// этот же Comparator в вызов метода binarySearch() (используя перегруженную версию метода). Объект
// Comparator необходимо передать в перегруженный метод binarySearch() в третьем аргументе.
class AlphabeticSearch {
    public static void main(String[] args) {
        String[] sa = Generated.array(new String[30], new RandomGenerator.String(5));
        Arrays.sort(sa, String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(sa));
        int index = Arrays.binarySearch(sa, sa[10], String.CASE_INSENSITIVE_ORDER);
        System.out.println("Index: "+ index + "\n"+ sa[index]);
    }
}