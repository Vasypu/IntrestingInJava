package generics;

import java.util.ArrayList;
import java.util.Collection;

/**
 *  Обобщенный метод для использования с генераторами.
 */
public class Generators {
    public static <T> Collection<T> fill(Collection<T> coll, Generator<T> gen, int n) {
        for(int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }

    public static void main(String[] args) {
        Collection<Coffee> coffee = fill(new ArrayList<>(), new CoffeeGenerator(), 4);
        for (Coffee c : coffee)
            System.out.println(c);

        Collection<Integer> fnumbers = fill(new ArrayList<>(), new Fibonacci(), 12);
        for (int i : fnumbers)
            System.out.print(i + ", ");
    }
}
