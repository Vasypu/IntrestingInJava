package strings;

import java.util.Arrays;

/**
 * Регулярные выражения
 */

//  Выражение «необязательный знак "минус", за которым следует одна или несколько цифр»
//  записывается следующим образом: -?\\d+
//  В первых двух строках обнаруживаются совпадения, но третье число, начинающееся с +,
//  является допустимым числом, но не совпадает с регулярным выражением. Таким образом,
//  нужно включить условие «может начинаться с + или -». В регулярных выражениях круглые
//  скобки используются для группировки, а вертикальная черта 1 означает ИЛИ. Итак,
//  выражение (-|\\+)?
public class IntegerMatch {
    public static void main(String[] args) {
        System.out.println("-1234".matches("-?\\d+"));
        System.out.println("5678".matches("-?\\d+"));
        System.out.println("+911".matches("-?\\d+"));
        System.out.println("+911".matches("(-|\\+)?\\d+"));
    }
}

//  Еще один полезный инструмент, встроенный в String — метод split(), — разбивает строку
//  по совпадениям заданного регулярного выражения.
//  Первый вызов split(), в котором строка разбивается по пробелам.
//  Во втором и третьем вызовах split() используется \w, обозначающая символ, не являющийся
//  символом слова (версия в нижнем регистре \w обозначает символ слова), — вы видите, что
//  во втором случае знаки препинания были удалены.
//  Третий вызов split() означает: «Буква n, за которой следует один или несколько символов,
//  не являющихся символами слов».
class Splitting {
    public static String knights =
            "Then, when you have found the shrubbery, you must " +
                    "cut down the mightiest tree in the forest... " +
                    "with... a herring!";

    public static void split(String regex) {
        System.out.println(Arrays.toString(knights.split(regex)));
    }

    public static void main(String[] args) {
        split(" ");     // Выражение может не содержать специальных символов
        split("\\W+");  // Разбиение по символам, не являющимся символами слов
        split("n\\W+"); // Буква 'n', за которой следуют символы,
        // не являющиеся символами слов
    }
}

//  Последний инструмент String, связанный с регулярными выражениями, — замена. Операция
//  может заменять как первое совпадение, так и все совпадения.
class Replacing {
    static String s = Splitting.knights;

    public static void main(String[] args) {
        System.out.println(s.replaceFirst("f\\w+", "located"));
        System.out.println(s.replaceAll("shrubbery|tree|herring", "banana"));
    }
}

class Rudolph {
    public static void main(String[] args) {
        for (String pattern : new String[]{"Rudolph",
                "[rR]udolph", "[rR][aeiou][a-z]ol.*", "R.*"})
            System.out.println("Rudolph".matches(pattern));
    }
}