package strings;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.MatchResult;

/**
 * Сканирование с использованием регулярных выражений
 * <p>
 * Кроме сканирования с чтением заранее определенных примитивных типов, вы также можете
 * проводить сканирование ввода по собственным шаблонам.
 * <p>
 * Будьте внимательны при сканировании по регулярным выражениям: совпадение шаблона ищется
 * только к следующей входной лексеме, так что если ваш шаблон содержит разделитель, совпадение
 * никогда не будет найдено
 */

public class ThreatAnalyzer {
    static String threatData =
            "58.27.82.161@02/10/2005\n" +
                    "204.45.234.40@02/11/2005\n" +
                    "58.27.82.161@02/11/2005\n" +
                    "58.27.82.161@02/12/2005\n" +
                    "58.27.82.161@02/12/2005\n" +
                    "[Next log section with different data format]";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(threatData);
        String pattern = "(\\d+[.]\\d+[.]\\d+[.]\\d+)@" +
                "(\\d{2}/\\d{2}/\\d{4})";
        while (scanner.hasNext(pattern)) {
            scanner.next(pattern);
            MatchResult match = scanner.match();
            String ip = match.group(1);
            String date = match.group(2);
            System.out.format("Threat on %s from %s\n", date, ip);
        }
    }
}

// StringTokenizer
// С регулярными выражениями и объектами Scanner строку также можно разбить
// на части по более сложным шаблонам — с StringTokenizer это сделать сложнее.
// Можно уверенно сказать, что класс StringTokenizer устарел и пользоваться им не следует
class ReplacingStringTokenizer {
    public static void main(String[] args) {
        String input = "But I'm not dead yet! I feel happy!";
        StringTokenizer stoke = new StringTokenizer(input);
        while (stoke.hasMoreElements())
            System.out.print(stoke.nextToken() + " ");
        System.out.println();
        System.out.println(Arrays.toString(input.split(" ")));
        Scanner scanner = new Scanner(input);
        while (scanner.hasNext())
            System.out.print(scanner.next() + " ");
    }
}
