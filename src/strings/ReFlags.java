package strings;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Флаги шаблонов
 * <p>
 * Альтернативный метод compile() получает флаги, управляющие процессом поиска совпадений:
 * Pattern.compile(String regex, int flag)
 * Флаги также могут объединяться операцией | (ИЛИ)
 */
public class ReFlags {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("^java",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        Matcher m = p.matcher(
                "java has regex\nJava has regex\n" +
                        "JAVA has pretty good regular expressions\n" +
                        "Regular expressions are in Java");
        while (m.find())
            System.out.println(m.group());
    }
}

// Метод split() разбивает входную строку по совпадениям регулярного
// выражения и создает массив объектов String.
class SplitDemo {
    public static void main(String[] args) {
        String input = "This!!unusual use!!of exclamation!!points";
        System.out.println(Arrays.toString(Pattern.compile("!!").split(input)));
        // Only do the first three:
        System.out.println(Arrays.toString(Pattern.compile("!!").split(input, 3)));

        System.out.println(Arrays.toString(input.split("!!")));
        System.out.println(Arrays.toString(input.split("!!", 3)));
    }
}
