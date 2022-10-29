package strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TheReplacements {
    public static void main(String[] args) {
//        String s = TextFile.read("TheReplacements.java");
        String s = "Here's а block of text to use as input to\n" +
                "the regular expression matcher. Note that we’ll\n " +
                "first extract the block of text by looking for\n " +
                "the special delimiters, then process the\n " +
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
        // Обработка информации find при выполнении замены: while(m.find())
        m.appendReplacement(sbuf, m.group().toUpperCase());
        // Присоединение оставшегося текста:
        m.appendTail(sbuf);
        System.out.println(sbuf);
    }
}
