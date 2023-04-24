package arrays;

import java.util.Arrays;
import java.util.Random;

/**
 *  Создание тестовых данных
 */

// метод fill() — одно значение просто копируется во все ячейки массива, или, в случае с объектами,
// копируется одна и та же ссылка. Заполнять можно как целый массив, так и диапазон элементов (как в двух
// последних командах).
public class FillingArrays {
    public static void main(String[] args) {
        int size = 6;
        boolean[] a1 = new boolean[size];
        byte[] a2 = new byte[size];
        char[] a3 = new char[size];
        short[] a4 = new short[size];
        int[] a5 = new int[size];
        long[] a6 = new long[size];
        float[] a7 = new float[size];
        double[] a8 = new double[size];
        String[] a9 = new String[size];
        Arrays.fill(a1, true);
        System.out.println("a1 = " + Arrays.toString(a1));
        Arrays.fill(a2, (byte)11);
        System.out.println("a2 = " + Arrays.toString(a2));
        Arrays.fill(a3, 'x');
        System.out.println("a3 = " + Arrays.toString(a3));
        Arrays.fill(a4, (short)17);
        System.out.println("a4 = " + Arrays.toString(a4));
        Arrays.fill(a5, 19);
        System.out.println("a5 = " + Arrays.toString(a5));
        Arrays.fill(a6, 23);
        System.out.println("a6 = " + Arrays.toString(a6));
        Arrays.fill(a7, 29);
        System.out.println("a7 = " + Arrays.toString(a7));
        Arrays.fill(a8, 47);
        System.out.println("a8 = " + Arrays.toString(a8));
        Arrays.fill(a9, "Hello");
        System.out.println("a9 = " + Arrays.toString(a9));
        // Операции с диапазонами:
        Arrays.fill(a9, 3, 5, "World");
        System.out.println("a9 = " + Arrays.toString(a9));
    }
}

interface Generator<T> { T next(); }

// генераторов-счетчиков для всех «оберток» примитивных типов и String
class CountingGenerator {
    public static class Boolean implements Generator<java.lang.Boolean> {
        private boolean value = false;
        public java.lang.Boolean next() {
            value = !value; // Простое переключение
            return value;
        }
    }

    public static class Byte implements Generator<java.lang.Byte> {
        private byte value = 0;
        public java.lang.Byte next() { return value++; }
    }

    static char[] chars = ("abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIDKLMNOPQRSTUVWXYZ").toCharArray();
    public static class Character implements Generator<java.lang.Character> {
        int index = -1;
        public java.lang.Character next() {
            index = (index + 1) % chars.length;
            return chars[index];
        }
    }

    public static class String implements Generator<java.lang.String> {
        private int length = 7;
        Generator<java.lang.Character> cg = new Character();
        public String() {}
        public String(int length) { this.length = length; }
        public java.lang.String next() {
            char[] buf = new char[length];
            for (int i = 0; i < length; i++)
                buf[i] = cg.next();
            return new java.lang.String(buf);
        }
    }

    public static class Short implements Generator<java.lang.Short> {
        private short value = 0;
        public java.lang.Short next() { return value++; }
    }

    public static class Integer implements Generator<java.lang.Integer> {
        private int value = 0;
        public java.lang.Integer next() { return value++; }
    }

    public static class Long implements Generator<java.lang.Long> {
        private long value = 0;
        public java.lang.Long next() { return value++; }
    }

    public static class Float implements Generator<java.lang.Float> {
        private float value = 0;

        public java.lang.Float next() {
            float result = value;
            value += 1.0;
            return result;
        }
    }

    public static class Double implements Generator<java.lang.Double> {
        private double value = 0.0;

        public java.lang.Double next() {
            double result = value;
            value += 1.0;
            return result;
        }
    }
}

class GeneratorsTest {
    public static int size = 10;
    public static void test(Class<?> surroundingClass) {
        for(Class<?> type : surroundingClass.getClasses()) {
            System.out.print(type.getSimpleName() + ": ");
            try {
                Generator<?> g = (Generator<?>) type.newInstance();
                for (int i = 0; i < size; i++)
                    System.out.printf(g.next() + " ");
                System.out.println();
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void main(String[] args){
        test(CountingGenerator.class);
    }
}

// Следующий набор Generator использует генератор случайных чисел
class RandomGenerator {
    private static Random r = new Random(47);
    public static class Boolean implements Generator<java.lang.Boolean> {
        public java.lang.Boolean next() { return r.nextBoolean(); }
    }

    public static class Byte implements Generator<java.lang.Byte> {
        public java.lang.Byte next() { return (byte)r.nextInt(); }
    }

    public static class Character implements Generator<java.lang.Character> {
        public java.lang.Character next() {
            return CountingGenerator.chars[r.nextInt(CountingGenerator.chars.length)];
        }
    }

    public static class String extends CountingGenerator.String {
        // Подключение генератора случайных символов:
        { cg = new Character(); } // Инициализация экземпляра
        public String() {}
        public String(int length) { super(length); }
    }

    public static class Short implements Generator<java.lang.Short> {
        public java.lang.Short next() { return (short) r.nextInt(); }
    }

    public static class Integer implements Generator<java.lang.Integer> {
        private int mod = 10000;
        public Integer() {}
        public Integer(int modulo) { mod = modulo; }
        public java.lang.Integer next() { return r.nextInt(mod); }
    }

    public static class Long implements Generator<java.lang.Long> {
        private int mod = 10000; public Long() {}
        public Long(int modulo) { mod = modulo; }
        public java.lang.Long next() { return new java.lang.Long(r.nextInt(mod)); }
    }

    public static class Float implements Generator<java.lang.Float> {
        public java.lang.Float next() {
            // Усечение дробной части до двух цифр:
            int trimmed = Math.round(r.nextFloat() * 100);
            return ((float) trimmed) / 100;
        }
    }

    public static class Double implements Generator<java.lang.Double> {
        public java.lang.Double next() {
            long trimmed = Math.round(r.nextDouble() * 100);
            return ((double) trimmed) / 100;
        }
    }
}

class RandomGeneratorsTest {
    public static void main(String[] args) {
        GeneratorsTest.test(RandomGenerator.class);
    }
}