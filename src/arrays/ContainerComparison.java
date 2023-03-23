package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  Особое отношение к массивам
 *  <p>
 *  Оба способа хранения объектов обеспечивают проверку типов. С введением автоматической упаковки контейнеры
 *  стали почти такими же простыми в использовании с примитивами, как массивы. Единственным оставшимся
 *  преимуществом массивов является их эффективность.
 */
class BerylliumSphere {
    private static long counter;
    private final long id = counter++;
    public String toString() { return "Sphere " + id; }
}

public class ContainerComparison {
    public static void main(String[] args) {
        BerylliumSphere[] spheres = new BerylliumSphere[10];
        for(int i = 0; i < 5; i++)
            spheres[i] = new BerylliumSphere();
        System.out.println(Arrays.toString(spheres));
        System.out.println(spheres[4]);
        List<BerylliumSphere> sphereList = new ArrayList<>();
        for(int i = 0; i < 5; i++)
            sphereList.add(new BerylliumSphere());
        System.out.println(sphereList);
        System.out.println(sphereList.get(4));
        int[] integers = { 0, 1, 2, 3, 4, 5 };
        System.out.println(Arrays.toString(integers));
        System.out.println(integers[4]);
        List<Integer> intList = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        intList.add(97);
        System.out.println(intList);
        System.out.println(intList.get(4));
    }
}

/**
 *  Массивы как полноценные объекты
 *  <p>
 *  С каким бы типом массива вы ни работали, его идентификатор — всего лишь ссылка на реальный объект,
 *  созданный в куче. Это объект, хранящий ссылки на другие объекты. Массив простейших типов и массив
 *  объектов практически идентичны в использовании. Единственное отличие состоит в том, что массив
 *  объектов содержит ссылки на них, в то время как в массиве примитивов хранятся реальные значения.
 */
class ArrayOptions {
    public static void main(String[] args) {
        // Массив объектов:
        BerylliumSphere[] a; // Локальная неинициализированная переменная
        BerylliumSphere[] b = new BerylliumSphere[5];
        // Ссылки в массиве автоматически инициализируются null:
        System.out.println("b: " + Arrays.toString(b));
        BerylliumSphere[] c = new BerylliumSphere[4];
        for(int i = 0; i < c.length; i++)
            if(c[i] == null) // Можно проверить ссылку на null
                c[i] = new BerylliumSphere();
        // Групповая инициализация:
        BerylliumSphere[] d = { new BerylliumSphere(),
                new BerylliumSphere(), new BerylliumSphere(), };
        // Динамическая групповая инициализация:
        a = new BerylliumSphere[] { new BerylliumSphere(), new BerylliumSphere(), };
        // (Завершающая запятая в обоих случаях не обязательна)
        System.out.println("a.length = " + a.length);
        System.out.println("b.length = " + b.length);
        System.out.println("c.length = " + c.length);
        System.out.println("d.length = " + d.length);
        a = d;
        System.out.println("a.length = " + a.length);
        // Массивы примитивов:
        int[] e; // Null-ссыпка
        int[] f = new int[5];
        // Примитивы в массиве автоматически инициализируются нулями:
        System.out.println("f: " + Arrays.toString(f));
        int[] g = new int[4];
        for(int i = 0; i < g.length; i++)
            g[i] = i*i;
        int[] h = { 11, 47, 93 };
        // Ошибка компиляции: переменная e не инициализирована:
        // !print("e.length = " + e.length);
        System.out.println("f.length = " + f.length);
        System.out.println("g.length = " + g.length);
        System.out.println("h.length = " + h.length);
        e = h;
        System.out.println("e.length = " + e.length);
        e = new int [] {1, 2};
        System.out.println("e.length = " + e.length);
    }
}