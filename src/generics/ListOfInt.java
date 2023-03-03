package generics;

import java.util.*;

/**
 *  Примитивы не могут использоваться как параметры-типы
 *  <p>
 *  Примитивы не могут использоваться в качестве параметров-типов. Таким образом, например, вы не
 *  сможете создать ArrayList<int>. Проблема решается использованием примитивных классов-«оберток»
 *  в сочетании с автоматической упаковкой Java SE. Если вы создадите ArrayList<Integer> и используете
 *  примитивные значения int с этим контейнером, вы обнаружите, что автоматическая упаковка выполняет
 *  преобразование к Integer и обратно автоматически.
 *  <p>
 *  Так как RandomGenerator.Integer реализует Generator<Integer>, я надеялся, что автоматическая упаковка
 *  преобразует значение next() из Integer в int. Однако автоматическая упаковка не применяется к массивам,
 *  поэтому решение не работает.
 */
public class ListOfInt {
    public static void main(String[] args) {
        List<Integer> li = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++)
            li.add(i);
        for (int i : li)
            System.out.print(i + " ");
    }
}

class ByteSet {
    Byte[] possibles = { 1,2,3,4,5,6,7,8,9 };
    Set<Byte> mySet = new HashSet<Byte>(Arrays.asList(possibles));
    // Но такая запись невозможна:
//     Set<Byte> mySet2 = new HashSet<Byte>(Arrays.<Byte>asList(1,2,3,4,5,6,7,8,9));
}

// Заполнение массива с Использованием генератора:
class FArray {
    public static <T> T[] fill(T[] a, Generator<T> gen) {
        for(int i = 0; i < a.length; i++)
            a[i] = gen.next();
        return a;
    }
}

class PrimitiveGenericTest {
    public static void main(String[] args) {
        String[] strings = FArray.fill(new String[7], new RandomGenerator.String(10));
        for (String s : strings)
            System.out.println(s);
        Integer[] integers = FArray.fill(new Integer[7], new RandomGenerator.Integer());
        for (int i : integers)
            System.out.println(i);
        // Автоматическая упаковка не спасет - команда не компилируется:
        // int[] b = FArray.fill(new int[7], new RandIntGenerator());
    }
}

class RandomGenerator {
    private static Random r = new Random(47);

    public static class Boolean implements Generator<java.lang.Boolean> {
        public java.lang.Boolean next() {
            return r.nextBoolean();
        }
    }

    public static class Byte implements Generator<java.lang.Byte> {
        public java.lang.Byte next() {
            return (byte) r.nextInt();
        }
    }

    public static class Character implements Generator<java.lang.Character> {
        public java.lang.Character next() {
            return CountingGenerator.chars[r.nextInt(CountingGenerator.chars.length)];
        }
    }

    public static class String extends CountingGenerator.String {
        // Подключение генератора случайных символов:
        {
            cg = new Character();
        } // Инициализация экземпляра public String() {}

        public String(int length) {
            super(length);
        }
    }

    public static class Short implements Generator<java.lang.Short> {
        public java.lang.Short next() {
            return (short) r.nextInt();
        }
    }

    public static class Integer implements Generator<java.lang.Integer> {
        private int mod = 10000; public Integer() {}
        public Integer(int modulo) { mod = modulo; }
        public java.lang.Integer next() {
            return r.nextInt(mod);
        }
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

        public String() { }
        public String(int length) { this.length = length; }

        public java.lang.String next() {
            char[] buf = new char[length];
            for (int i = 0; i < length; i++) buf[i] = cg.next();
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