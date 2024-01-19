package io;

import java.io.*;

/**
 *  Перенаправление стандартного ввода-вывода
 *  <p>
 *  Класс System позволяет вам перенаправить стандартный ввод, вывод и поток ошибок.
 *  <p>
 *  Эта программа присоединяет стандартный ввод к файлу и перенаправляет стандартный ввод и поток для ошибок в другие
 *  файлы. Обратите внимание на то, как исходный объект System.out сохраняется в начале программы и восстанавливается
 *  перед ее завершением.
 */
public class Redirecting {
    public static void main(String[] args) throws IOException {
        PrintStream console = System.out;
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("src/io/Redirecting.java"));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("test.out")));
        System.setIn(in);
        System.setOut(out);
        System.setErr(out);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s = br.readLine()) != null)
            System.out.println(s);
        out.close(); // Не забудьте!
        System.setOut(console);
    }
}