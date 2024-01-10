package io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 *  Вспомогательные средства для работы с каталогами
 */
public class Directory {
    public static File[] local(File dir, final String regex) {
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);

            public boolean accept(File dir, String name) {
                return pattern.matcher(new File(name).getName()).matches();
            }
        });
    }
    public static File[] local(String path, final String regex) { // Перегружен
        return local(new File(path), regex);
    }
    // Кортеж для возвращения пары объектов:
    public static class TreeInfo implements Iterable<File> {
        public List<File> files = new ArrayList<File>();
        public List<File> dirs = new ArrayList<File>();
        // The default iterable element is the file list:
        public Iterator<File> iterator() { return files.iterator(); }

        void addAll(TreeInfo other) {
            files.addAll(other.files);
            dirs.addAll(other.dirs);
        }
        public String toString() {
            return "dirs: " + PPrint.pformat(dirs) +
                    "\n\nfiles: " + PPrint.pformat(files);
        }
    }
    public static TreeInfo walk(String start, String regex) { // Начало рекурсии
        return recurseDirs(new File(start), regex);
    }
    public static TreeInfo walk(File start, String regex) { // Перегружен
        return recurseDirs(start, regex);
    }
    public static TreeInfo walk(File start) { // Bce
        return recurseDirs(start, ".*");
    }
    public static TreeInfo walk(String start) {
        return recurseDirs(new File(start), ".*");
    }
    static TreeInfo recurseDirs(File startDir, String regex) {
        TreeInfo result = new TreeInfo();
        for(File item : startDir.listFiles()) {
            if(item.isDirectory()) {
                result.dirs.add(item);
                result.addAll(recurseDirs(item, regex));
            } else // Обычный файл
                if(item.getName().matches(regex))
                    result.files.add(item);
        }
        return result;
    }
    // Простая проверка:
    public static void main(String[] args) {
        if(args.length == 0)
            System.out.println(walk("."));
        else
            for(String arg : args)
                System.out.println(walk(arg));
    }
}

class DirectoryDemo {
    public static void main(String[] args) {
        // Все каталоги
        PPrint.pprint(Directory.walk(".").dirs);
        // Все файлы, имена которых начинаются с 'T'
        for(File file : Directory.local(".", "T.*"))
            System.out.println(file);
        System.out.println("----------------------");
        // Все файлы Java, имена которых начинаются с 'T'
        for(File file : Directory.walk(".", "T.*\\.java"))
            System.out.println(file);
        System.out.println("======================");
        // Файлы классов, имена которых содержат "Z" или "z":
        for(File file : Directory.walk(".",".*[Zz].*\\.class"))
            System.out.println(file);
    }
}