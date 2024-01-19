package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *  Чтение из стандартного потока ввода
 *  <p>
 *  Стандартный вывод System.out, для которого уже надстроен класс форматирования данных PrintStream. Поток для
 *  ошибок System.err схож со стандартным выводом, а стандартный ввод System.in представляет собой «низкоуровневый»
 *  поток InputStream без «обepток».
 *  <p>
 *  Потоки System.out и System.err можно использовать напрямую, в то время как стандартный ввод System.in должен
 *  иметь «обертку».
 *  <p>
 *  Обычно чтение осуществляется построчно, методом readLine(), поэтому имеет смысл буферизовать стандартный ввод
 *  System.in посредством BufferedReader. Чтобы сделать это, предварительно следует конвертировать поток System,
 *  in в объект Reader при помощи класса-преобразователя InputStreamReader.
 */
public class Echo {
    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s = stdin.readLine()) != null && s.length()!= 0)
            System.out.println(s);
        // Пустая строка или Ctrl-Z завершает работу програгты.
    }
}

/**
 *  Замена System.out на PrintWriter
 *  <p>
 *  Стандартный вывод System.out является объектом PrintStream, который, в свою очередь, является производным от
 *  базового класса OutputStream. В классе PrintWriter имеется конструктор, который принимает в качестве аргумента
 *  выходной поток OutputStream. Таким образом, при помощи этого конструктора можно преобразовать поток стандартного
 *  вывода System.out в символьно-ориентированный поток PrintWriter.
 */
class ChangeSystemOut {
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out, true);
        out.println("Hello, world");
    }
}