package io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 *  Анонимные внутренние классы
 *  <p>
 *  Именно так безымянные внутренние классы позволяют быстро создать специализированный «одноразовый» класс для
 *  решения конкретной задачи. Одно из преимуществ такого решения заключается в том, что составные части кода,
 *  решающего определенную задачу, находятся в одном месте.
 */

// Заметьте, что аргумент метода filter() должен быть неизменным (final). Это необходимо для того, чтобы
// анонимный внутренний класс смог получить к нему доступ даже за пределами своей области действия.
// Представьте, что вы передали в созданный объект анонимного класса переменную, а затем изменили её "снаружи"
// для избежания ошибок необходимо использовать модификатор final.
public class DirList2 {
    public static FilenameFilter filter(final String regex) {
        // Создание анонимного внутреннего класса:
        return new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        }; // Конец анонимного внутреннего класса:
    }
    public static void main(String[] args) {
        File path = new File(".");
        String[] list;
        if (args.length == 0)
            list = path.list();
        else
            list = path.list(filter(args[0]));
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list)
            System.out.println(dirItem);
    }
}

// На этот раз неизменным (final) оказывается аргумент метода main(), так как безымянный внутренний класс
// использует параметр командной строки (args[0]) напрямую.
class DirList3 {
    public static void main(final String[] args) {
        File path = new File(".");
        String[] list;

        if(args.length == 0)
            list = path.list();
        else
            list = path.list(new FilenameFilter() {
                private Pattern pattern = Pattern.compile(args[0]);

                public boolean accept(File dir, String name) {
                    return pattern.matcher(name).matches();
                }
            });

        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list)
            System.out.println(dirItem);
    }
}