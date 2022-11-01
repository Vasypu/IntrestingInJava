package strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Операции замены
 * <p>
 * appendReplacement(StringBuffer sbuf, String replacement) выполняет пошаговые замены в sbuf (вместо замены первого
 * совпадения или всех совпадений, как replaceFirst () и replaceAll() соответственно). Этот метод очень важен,
 * поскольку он позволяет вызывать методы и выполнять другие операции для получения replacement (replaceFirst()
 * и replaceAll() могут добавлять только фиксированные строки).
 * appendTail(StringBuffer sbuf, String replacement) вызывается после одного или нескольких вызовов метода
 * appendReplacement() для копирования остатка входной строки.
 */

public class TheReplacements {
    public static void main(String[] args) {
        String s = "Here's а block of text to use as input to\n" +
                "the regular expression matcher.  Note that we’ll\n " +
                "first extract the block of text by  looking for\n " +
                "the special delimiters,  then process the\n " +
                "extracted block.";
        // Поиск блока текста, заключенного в специальные комментарии:
        Matcher mInput = Pattern.compile("/\\*!(.*)!\\*/", Pattern.DOTALL).matcher(s);
        if (mInput.find())
            s = mInput.group(1); // Совпадение подвыражения в круглых скобках
        // Два и более пробела заменяются одним пробелом:
        s = s.replaceAll(" {2,}", " ");
        // Один и более пробелов в начале строки заменяются пустой строкой.
        // Для выполнения должен быть активен режим MULTILINE:
        s = s.replaceAll("(?m)^ +", "");
        System.out.println(s);
        s = s.replaceFirst("[aeiou]", "(VOWEL1)");
        StringBuffer sbuf = new StringBuffer();
        Pattern p = Pattern.compile("[aeiou]");
        Matcher m = p.matcher(s);
        // Обработка информации find при выполнении замены:
        while (m.find())
            m.appendReplacement(sbuf, m.group().toUpperCase());
        // Присоединение оставшегося текста:
        m.appendTail(sbuf);
        System.out.println(sbuf);
    }
}

// с помощью метода reset() можно обновить строку для поиска
class Resetting {
    public static void main(String[] args) throws Exception {
        Matcher m = Pattern.compile("[frb][aiu][gx]")
                .matcher("fix the rug with bags");
        while (m.find())
            System.out.print(m.group() + " ");
        System.out.println();
        m.reset("fix the rig with rags");
        while (m.find())
            System.out.print(m.group() + " ");
    }
}
