package io;

import java.io.*;

/**
 *  Вывод в файл
 */
public class BasicFileOutput {
    static String file = "BasicFileOutput.out";
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new StringReader(BufferedInputFile.read("src/io/BasicFileOutput.java")));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        int lineCount = 1;
        String s;
        while ((s = in.readLine()) != null)
            out.println(lineCount++ + ": " + s);
        out.close();
        // Вывод сохраненного файла:
        System.out.println(BufferedInputFile.read(file));
    }
}

/**
 *  Сокращенная запись для вывода в текстовые файлы
 *  <p>
 *  B Java SE5 у PrintWriter появился вспомогательный конструктор, чтобы вам не приходилось явно проделывать все
 *  необходимое каждый раз, когда вы захотите создать текстовый файл и записать в него информацию.
 */
class FileOutputShortcut {
    static String file = "FileOutputShortcut.out";
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new StringReader(BufferedInputFile.read("src/io/BasicFileOutput.java")));
        // Сокращенная запись:
        PrintWriter out = new PrintWriter(file);
        int lineCount = 1;
        String s;
        while((s = in.readLine()) != null )
            out.println(lineCount++ + ": " + s);
        out.close();
        // Вывод сохраненного файла:
        System.out.println(BufferedInputFile.read(file));
    }
}