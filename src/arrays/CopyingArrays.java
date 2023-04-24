package arrays;

import java.util.Arrays;

/**
 *  Копирование массива
 *  <p>
 *  В стандартной библиотеке^уа имеется статический (static) метод с именем System. arraycopy(), который
 *  способен гораздо быстрее копировать массивы, чем получается в случае «ручного» копирования элементов
 *  в цикле for. Метод System.arraycopy() перегружен для всех нужных типов.
 *  <p>
 *  В аргументах arraycopy() передается исходный массив; смещение копируемого блока в исходном массиве;
 *  приемный массив; смещение копируемого блока в приемном массиве; и количество копируемых элементов.
 *  <p>
 *  Копироваться могут как массивы примитивов, так и массивы объектов. Однако копирование массивов объектов
 *  ограничивается копированием ссылок — сами объекты при этом не дублируются. Такое копирование называется
 *  поверхностным. Метод System.arraycopy() не выполняет ни автоматической упаковки, ни автоматической
 *  распаковки — типы двух массивов должны точно совпадать.
 */
public class CopyingArrays {
    public static void main(String[] args) {
        int[] i = new int[7];
        int[] j = new int[10];
        Arrays.fill(i, 47);
        Arrays.fill(j, 99);
        System.out.println("i = " + Arrays.toString(i));
        System.out.println("j = " + Arrays.toString(j));
        System.arraycopy(i, 0, j, 0, i.length);
        System.out.println("j = " + Arrays.toString(j));
        int[] k = new int[5];
        Arrays.fill(k, 103);
        System.arraycopy(i, 0, k, 0, k.length);
        System.out.println("k = " + Arrays.toString(k));
        Arrays.fill(k, 103);
        System.arraycopy(k, 0, i, 0, k.length);
        System.out.println("i = " + Arrays.toString(i));
        // Objects:
        Integer[] u = new Integer[10];
        Integer[] v = new Integer[5];
        Arrays.fill(u, new Integer(47));
        Arrays.fill(v, new Integer(99));
        System.out.println("u = " + Arrays.toString(u));
        System.out.println("v = " + Arrays.toString(v));
        System.arraycopy(v, 0, u, u.length/2, v.length);
        System.out.println("u = " + Arrays.toString(u));
    }
}