package io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 *  Получение содержимого каталогов
 *  <p>
 *  Объект File позволяет получить этот список двумя способами. Если вызвать метод list() без аргументов, то результатом
 *  будет полный список файлов и каталогов (точнее, их названий), содержащихся в данном каталоге.
 *  <p>
 *  Метод accept() принимает в качестве аргументов объект File, представляющий каталог, в котором был найден данный файл,
 *  и строку, содержащую имя файла.
 */
public class DirList {
    public static void main(String[] args) {
        File path = new File(".");
        String[] list;
        if (args.length == 0)
            list = path.list();
        else
            list = path.list(new DirFilter(args[0]));
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list)
            System.out.println(dirItem);
    }
}

class DirFilter implements FilenameFilter {
    private Pattern pattern;
    public DirFilter(String regex) {
        pattern = Pattern.compile(regex);
    }
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}