package strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Pattern и Matcher.
 * Откомпилируйте регулярное выражение статическим методом Pattern. compile().
 * В результате на базе аргумента String создается объект Pattern; чтобы
 * использовать этот объект, вызовите его метод matcher() и передайте строку,
 * в которой ведется поиск.
 */

public class TestRegularExpression {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage:\njava TestRegularExpression " +
                    "characterSequence regularExpression+");
            System.exit(0);
        }
        System.out.println("Input: \"" + args[0] + "\"");
        for (String arg : args) {
            System.out.println("Regular expression: \"" + arg + "\"");
            Pattern p = Pattern.compile(arg);
            Matcher m = p.matcher(args[0]);
            while (m.find()) {
                System.out.println("Match \"" + m.group() + "\" at positions " +
                        m.start() + "-" + (m.end() - 1));
            }
        }
    }
}

// Шаблон \\w+ разбивает входные данные на слова. Метод find() действует как итератор,
// перемещаясь по входной строке. Второй версии find() может передаваться целочисленный
// аргумент с позицией символа, с которой должен начинаться поиск, — эта версия сбрасывает
// позицию поиска до значения аргумента,
class Finding {
    public static void main(String[] args) {
        Matcher m = Pattern.compile("\\w+")
                .matcher("Evening is full of the linnet's wings");
        while (m.find())
            System.out.print(m.group() + " ");
        System.out.println();
        int i = 0;
        while (m.find(i)) {
            System.out.print(m.group() + " ");
            i++;
        }
    }
}

// Шаблон регулярного выражения содержит несколько подвыражений, заключенных в
// круглые скобки; эти подвыражения состоят из произвольного количества символов,
// не являющихся пропусками (\S+), за которыми следует произвольное количество
// символов-пропусков (\s+). Группы предназначены для сохранения трех последних
// слов в каждой строке текста, конец которой обозначается знаком $. Однако
// обычно $ совпадает только в конце всей входной последовательности, поэтому
// вы должны явно приказать регулярному выражению учитывать разрывы строк во
// входных данных. Задача решается при помощи флага шаблона (?m) в начале последовательности
class Groups {
    static public final String POEM =
            "Twas brillig, and the slithy toves\n" +
                    "Did gyre and gimble in the wabe.\n" +
                    "All mimsy were the borogoves,\n" +
                    "And the mome raths outgrabe.\n\n" +
                    "Beware the Jabberwock, my son,\n" +
                    "The jaws that bite, the claws that catch.\n" +
                    "Beware the lubjub bird, and shun\n" +
                    "The frumious Bandersnatch.";

    public static void main(String[] args) {
        Matcher m =
                Pattern.compile("(?m)(\\S+)\\s+((\\S+)\\s+(\\S+))$")
                        .matcher(POEM);
        while (m.find()) {
            for (int j = 0; j <= m.groupCount(); j++)
                System.out.print("[" + m.group(j) + "]");
            System.out.println();
        }
    }
}
