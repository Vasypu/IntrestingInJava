package arrays;

import containers.Generator;
import java.util.Arrays;

/**
 *  Применение генераторов для создания массивов
 *  <p>
 *  Первая версия метода получает существующий массив и заполняет его с использованием генератора, а вторая
 *  версия получает объект Class, Generator и нужное количество элементов, после чего создает новый массив,
 *  снова заполняя его при помощи генератора.
 */
public class Generated {
    // Заполнение существующего массива:
     public static <T> T[] array(T[] a, Generator<T> gen) {
         return new CollectionData<T>(gen, a.length).toArray(a);
}
    // Создание нового массива:
    @SuppressWarnings("unchecked")
    public static <T> T[] array(Class<T> type, Generator<T> gen, int size) {
        T[] a = (T[]) java.lang.reflect.Array.newInstance(type, size);
        return new CollectionData<T>(gen, size).toArray(a);
    }
}

class TestGenerated {
    public static void main(String[] args) {
        Integer[] a = {9, 8, 7, 6};
        System.out.println(Arrays.toString(a));
        a = Generated.array(a, new CountingGenerator.Integer());
        System.out.println(Arrays.toString(a));
        Integer[] b = Generated.array(Integer.class, new CountingGenerator.Integer(), 15);
        System.out.println(Arrays.toString(b));
    }
}

// Каждая версия primitive() создает соответствующий массив примитивов нужной длины, а затем
// копирует элементы из массива типов-«оберток»
class ConvertTo {
    public static boolean[] primitive(Boolean[] in) {
        boolean[] result = new boolean[in.length];
        for (int i = 0; i < in.length; i++)
            result[i] = in[i]; // Автоматическая распаковка
        return result;
    }
    public static char[] primitive(Character[] in) {
        char[] result = new char[in.length];
        for (int i = 0; i < in.length; i++)
            result[i] = in[i];
        return result;
    }
    public static byte[] primitive(Byte[] in) {
        byte[] result = new byte[in.length];
        for(int i = 0; i < in.length; i++)
            result[i] = in[i];
        return result;
    }
    public static short[] primitive(Short[] in) {
        short[] result = new short[in.length];
        for (int i = 0; i < in.length; i++)
            result[i] = in[i];
        return result;
    }
    public static int[] primitive(Integer[] in) {
        int[] result = new int[in.length];
        for (int i = 0; i < in.length; i++)
            result[i] = in[i];
        return result;
    }
    public static long[] primitive(Long[] in) {
        long[] result = new long[in.length];
        for(int i = 0; i < in.length; i++)
            result[i] = in[i];
        return result;
    }
    public static float[] primitive(Float[] in) {
        float[] result = new float[in.length];
        for(int i = 0; i < in.length; i++)
            result[i] = in[i];
        return result;
    }
    public static double[] primitive(Double[] in) {
        double[] result = new double[in.length];
        for(int i = 0; i < in.length; i++)
            result[i] = in[i];
        return result;
    }
}

// ConvertTo для обеих версий Generated.array():
class PrimitiveConversionDemonstration {
    public static void main(String[] args) {
        Integer[] a = Generated.array(Integer.class, new CountingGenerator.Integer(), 15);
        int[] b = ConvertTo.primitive(a);
        System.out.println(Arrays.toString(b));
        boolean[] c = ConvertTo.primitive(Generated.array(Boolean.class, new CountingGenerator.Boolean(), 7));
        System.out.println(Arrays.toString(c));
    }
}

// программа тестирует средства генерирования массивов с использованием классов RandomGenerator:
class TestArrayGeneration {
    public static void main(String[] args) {
        int size = 6;
        boolean[] a1 = ConvertTo.primitive(Generated.array(Boolean.class, new RandomGenerator.Boolean(), size));
        System.out.println("a1 = " + Arrays.toString(a1));
        byte[] a2 = ConvertTo.primitive(Generated.array(Byte.class, new RandomGenerator.Byte(), size));
        System.out.println("a2 = " + Arrays.toString(a2));
        char[] a3 = ConvertTo.primitive(Generated.array(Character.class, new RandomGenerator.Character(), size));
        System.out.println("a3 = " + Arrays.toString(a3));
        short[] a4 = ConvertTo.primitive(Generated.array(Short.class, new RandomGenerator.Short(), size));
        System.out.println("a4 = " + Arrays.toString(a4));
        int[] a5 = ConvertTo.primitive(Generated.array(Integer.class, new RandomGenerator.Integer(), size));
        System.out.println("a5 = " + Arrays.toString(a5));
        long[] a6 = ConvertTo.primitive(Generated.array(Long.class, new RandomGenerator.Long(), size));
        System.out.println("a6 = " + Arrays.toString(a6));
        float[] a7 = ConvertTo.primitive(Generated.array(Float.class, new RandomGenerator.Float(), size));
        System.out.println("a7 = " + Arrays.toString(a7));
        double[] a8 = ConvertTo.primitive(Generated.array(Double.class, new RandomGenerator.Double(), size));
        System.out.println("a8 = " + Arrays.toString(a8));
    }
}