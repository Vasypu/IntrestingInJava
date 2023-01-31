package generics;

import java.util.ArrayList;
import java.util.List;

/**
 *  Списки аргументов переменной длины и обобщенные методы.
 *  <p>
 *  Обобщенные методы нормально сосуществуют со списками аргументов переменной длины.
 *  Метод makeList() реализует ту же функциональность, что и метод java.util.Arrays
 *  asList() стандартной библиотеки.
 */
public class GenericVarargs {
    public static <T> List<T> makeList(T... args) {
        List<T> result = new ArrayList<>();
        for(T item : args)
            result.add(item);
        return result;
    }
    public static void main(String[] args) {
        List<String> ls = makeList("A");
        System.out.println(ls);
        ls = makeList("A", "B", "C");
        System.out.println(ls);
        ls = makeList("ABCDEFFHIJKLMNOPQRSTUVWXYZ".split(""));
        System.out.println(ls);
    }
}
