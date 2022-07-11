package interfaces;

import java.nio.CharBuffer;
import java.util.Random;
import java.util.Scanner;

/**
 *  Интерфейсы как средство адаптации.
 *
 *  Для того чтобы класс RandomWords мог работать со Scanner мы реализовали
 *  в нем интерфейс Readable.
 *
 *  Если мы не можем реализовать в классе RandomDoubles по каким-то причинам
 *  интерфейс Readable, то можно создать класс AdaptedRandomDoubles адаптер,
 *  который будет наследовать наш класс и реализовывать интерфейс.
 */

public class RandomWords implements Readable{
    private static final Random rand = new Random(47);
    private static final char[] capitals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final char[] lowers = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] vowels = "aeiou".toCharArray();
    private int count;
    public RandomWords(int count) { this.count = count; }
    public int read(CharBuffer cb) {
        if (count-- == 0)
            return -1;      // Признак конца входных данных
        cb.append(capitals[rand.nextInt(capitals.length)]);
        for (int i = 0; i < 4; i++) {
            cb.append(vowels[rand.nextInt(vowels.length)]);
            cb.append(lowers[rand.nextInt(vowels.length)]);
        }
        cb.append(" ");
        return 10; // Количество присоединенных символов
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(new RandomWords(10));
        while (s.hasNext())
            System.out.println(s.next());
    }
}

class RandomDoubles {
    private static final Random rand = new Random(47);
    public double next() { return rand.nextDouble(); }
    public static void main(String[] args) {
        RandomDoubles rd = new RandomDoubles();
        for (int i = 0; i < 7; i++)
            System.out.println(rd.next() + " ");
    }
}

class AdaptedRandomDoubles extends RandomDoubles implements Readable {
    private int count;
    AdaptedRandomDoubles(int count) {
        this.count = count;
    }
    public int read(CharBuffer cb) {
        if (count-- == 0)
            return -1;
        String result = next() + " ";
        cb.append(result);
        return result.length();
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(new AdaptedRandomDoubles(7));
        while (s.hasNextDouble())
            System.out.println(s.nextDouble() + " ");
    }
}
