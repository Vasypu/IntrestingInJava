package arrays;

import java.util.Arrays;
import java.util.Random;

/**
 *  Возврат массива
 *  <p>
 *  Возврат массива сходен с возвратом любого другого объекта — это ссылка. Неважно, был ли
 *  массив создан внутри метода flavorSet() или в любом другом месте. Уборщик мусора возьмет
 *  на себя удаление массива после того, как вы завершите с ним работу, а до этого массив может
 *  существовать сколь угодно долго, пока в нем есть надобность.
 */
public class IceCream {
    private static Random rand = new Random(47);
    static final String[] FLAVORS = {
            "Chocolate", "Strawberry", "Vanilla Fudge Swirl",
            "Mint Chip", "Mocha Almond Fudge", "Rum Raisin",
            "Praline Cream", "Mud Pie"
    };

    public static String[] flavorSet(int n) {
        if(n > FLAVORS.length)
            throw new IllegalArgumentException("Set too big");
        String[] results = new String[n];
        boolean[] picked = new boolean[FLAVORS.length];
        for(int i = 0; i < n; i++) {
            int t;
            do
                t = rand.nextInt(FLAVORS.length);
            while (picked[t]);
            results[i] = FLAVORS[t];
            picked[t] = true;
        }
        return results;
    }

    public static void main(String[] args){
        for (int i = 0; i < 7; i++)
            System.out.println(Arrays.toString(flavorSet(3)));
    }
}
