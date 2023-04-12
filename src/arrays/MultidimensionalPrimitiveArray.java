package arrays;

import java.util.Arrays;
import java.util.Random;

/**
 *  Многомерные массивы
 *  <p>
 *
 */
public class MultidimensionalPrimitiveArray {
    public static void main(String[] args) {
        int[][] a = {
                {1, 2, 3,},
                {4, 5, 6,},
        };
        System.out.println(Arrays.deepToString(a));

        // Если явное значение инициализации не указано, примитивные значения в массивах инициализируются
        // автоматически. Массивы объектов инициализируются значениями null.
        // 3-D array with fixed length:
        int[][][] b = new int[2][2][4];
        System.out.println(Arrays.deepToString(b));

        // Векторы массивов, образующих матрицу, могут иметь произвольную длину (так называемые ступенчатые массивы)
        // Первая конструкция new создает массив, у которого первый элемент имеет случайную длину, а остальные не
        // определены. Вторая конструкция new в цикле for заполняет элементы, но оставляет третий индекс неопределенным
        // до третьего new
        Random rand = new Random(47);
        // Трехмерный массив с векторами переменной длины:
        int[][][] c = new int[rand.nextInt(7)][][];
        for(int i = 0; i < c.length; i++) {
            c[i] = new int[rand.nextInt(5)][];
            for(int j = 0; j < c[i].length; j++)
                c[i][j] = new int[rand.nextInt(5)];
        }
        System.out.println(Arrays.deepToString(c));

        // spheres — еще один ступенчатый массив с разными длинами списков объектов
        BerylliumSphere[][] spheres = {
                { new BerylliumSphere(), new BerylliumSphere() },
                { new BerylliumSphere(), new BerylliumSphere(),
                 new BerylliumSphere(), new BerylliumSphere() },
                { new BerylliumSphere(), new BerylliumSphere(),
                 new BerylliumSphere(), new BerylliumSphere(),
                 new BerylliumSphere(), new BerylliumSphere(),
                 new BerylliumSphere(), new BerylliumSphere() },
        };
        System.out.println(Arrays.deepToString(spheres));

        // Автоматическая упаковка также работает с инициализаторами массивов
        Integer[][] a2 = { // Autoboxing:
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {21, 22, 23, 24, 25, 26, 27, 28, 29, 30},
                {51, 52, 53, 54, 55, 56, 57, 58, 59, 60},
                {71, 72, 73, 74, 75, 76, 77, 78, 79, 80},
        };
        System.out.println(Arrays.deepToString(a2));

        // А вот как происходит последовательное построение непримитивных объектов
        Integer[][] a3;
        a3 = new Integer[3][];
        for(int i = 0; i < a3.length; i++) {
            a3[i] = new Integer[3];
            for (int j = 0; j < a3[i].length; j++)
                a3[i][j] = i * j; // Автоматическая упаковка
        }
        System.out.println(Arrays.deepToString(a));

        Integer[][] a4 = { // Автоматическая упаковка
                { 1, 2, 3, },
                { 4, 5, 6, },
        };
        Double[][][] a5 = { // Автоматическая упаковка
                { { 1.1, 2.2 }, { 3.3, 4.4 } },
                { { 5.5, 6.6 }, { 7.7, 8.8 } },
                { { 9.9, 1.2 }, { 2.3, 3.4 } },
        };
        String[][] a6 = {
                {"The", "Quick", "Sly", "Fox"},
                {"lumped", "Over"},
                {"The", "Lazy", "Brown", "Dog", "and", "friend"},
        };
        System.out.println("a4: " + Arrays.deepToString(a4));
        System.out.println("a5: " + Arrays.deepToString(a5));
        System.out.println("a6: " + Arrays.deepToString(a6));
    }
}