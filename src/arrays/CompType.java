package arrays;

import containers.Generator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 *  Сравнения элементов массивов
 *  <p>
 *  При реализации интерфейса java.lang.Comparable. Это очень простой интерфейс с одним методом,
 *  compareTo(). Метод получает в качестве аргумента другой объект того же типа и возвращает
 *  отрицательное значение, если аргумент меньше текущего объекта, ноль, если аргумент равен ему,
 *  и положительное значение, если аргумент больше, чем текущий объект.
 */
public class CompType implements Comparable<CompType> {
    int i;
    int j;
    private static int count = 1;
    public CompType(int n1, int n2) {
        i = n1;
        j = n2;
    }
    public String toString() {
        String result = "[i = " + i + ", j = " + j + "]";
        if(count++ % 3 == 0)
            result += "\n ";
        return result;
    }
    public int compareTo(CompType rv) {
        return (i < rv.i ? -1 : (i == rv.i ? 0 : 1));
    }
    private static Random r = new Random(47);
    public static Generator<CompType> generator() {
        return new Generator<CompType>() {
            public CompType next() {
                return new CompType(r.nextInt(100), r.nextInt(100));
            }
        };
    }

    public static void main(String[] args) {
        CompType[] a = Generated.array(new CompType[12], generator());
        System.out.println("before sorting:");
        System.out.println(Arrays.toString(a));
        Arrays.sort(a);
        System.out.println("after sorting:");
        System.out.println(Arrays.toString(a));
    }
}

// Класс Collections содержит метод reverseOrder(), который производит реализацию Comparator,
// меняющую порядок сортировки на обратный.
class Reverse {
    public static void main(String[] args) {
        CompType[] a = Generated.array(new CompType[12], CompType.generator());
        System.out.println("before sorting:");
        System.out.println(Arrays.toString(a));
        Arrays.sort(a, Collections.reverseOrder());
        System.out.println("after sorting:");
        System.out.println(Arrays.toString(a));
    }
}

class CompTypeComparator implements Comparator<CompType> {
    public int compare(CompType o1, CompType o2) {
        return (o1.j < o2.j ? -1 : (o1.j == o2.j ? 0 : 1));
    }
}
class ComparatorTest {
    public static void main(String[] args) {
        CompType[] a = Generated.array(new CompType[12], CompType.generator());
        System.out.println("before sorting:");
        System.out.println(Arrays.toString(a));
        Arrays.sort(a, new CompTypeComparator());
        System.out.println("after sorting:");
        System.out.println(Arrays.toString(a));
    }
}