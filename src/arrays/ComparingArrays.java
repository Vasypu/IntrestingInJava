package arrays;

import java.util.Arrays;

/**
 *  Сравнение массивов
 *  <p>
 *  В классе Arrays имеется перегруженный метод equals() для сравнения целых массивов, для всех примитивных
 *  типов и для Object. При сравнении массивов проверяются следующие условия: в массивах должно быть равное
 *  количество элементов и каждый элемент должен быть эквивалентен соответствующему элементу другого массива,
 *  для проверки этого условия используется метод equals().
 *  <p>
 *  В последнем случае все элементы s1 указывают на один и тот же объект, но s2 содержит пять уникальных объектов.
 *  Однако равенство массивов определяется на основании содержимого (через Object.equals()), поэтому результат
 *  равен true.
 */
public class ComparingArrays {
    public static void main(String[] args) {
        int[] a1 = new int[10];
        int[] a2 = new int[10];
        Arrays.fill(a1, 47);
        Arrays.fill(a2, 47);
        System.out.println(Arrays.equals(a1, a2));
        a2[3] = 11;
        System.out.println(Arrays.equals(a1, a2));
        String[] s1 = new String[4];
        Arrays.fill(s1, "Hi");
        String[] s2 = { new String("Hi"), new String("Hi"),
                new String("Hi"), new String("Hi") };
        System.out.println(Arrays.equals(s1, s2));
    }
}
