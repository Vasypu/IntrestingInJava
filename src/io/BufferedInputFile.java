package io;

import java.io.*;

/**
 *  Буферизованное чтение из файла
 */
public class BufferedInputFile {
    // Вывод исключений на консоль:
    public static String read(String filename) throws IOException {
        // Чтение по строкам:
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = in.readLine()) != null)
            sb.append(s + "\n");
        in.close();
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.print(read("BufferedInputFile.java"));
    }
}

/**
 *  Чтение из памяти
 */
class MemoryInput {
    public static void main(String[] args) throws IOException {
        StringReader in = new StringReader(BufferedInputFile.read("MemoryInput.java"));
        int c;
        while ((c = in.read()) != -1)
            System.out.print((char) c);
        // Заметьте, что метод read() возвращает следующий символ в формате int,
        // поэтому для правильного вывода на консоль приходится приводить его результат к типу char.
    }
}

/**
 *  Форматированное чтение из памяти
 *  <p>
 *  Метод available() всегда работает по-разному, в зависимости от источника данных; дословно его функция описывается следующим
 *  образом: «количество байтов, которые можно прочитать без блокировки». Когда читается файл, доступны все его байты, но для
 *  другого рода потоков это не обязательно верно, потому используйте этот метод разумно.
 */
// Для чтения «форматированных» данных применяется класс DataInputStream, ориентированный на ввод-вывод байтов
// (а не символов). Это вынуждает нас работать с классами иерархии InputStream, а не их аналогов на основе класса.
class FormattedMemoryInput {
    public static void main(String[] args) throws IOException {
        try {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(BufferedInputFile.read("FormattedMemoryInput.java").getBytes()));
            while (true)
                System.out.print((char) in.readByte());
        } catch(EOFException e) {
            System.err.println("End of stream");
        }
    }
}

// При чтении байтов из форматированного потока DataInputStream методом readByte() любое полученное значение будет
// законным результатом, поэтому он неприменим для идентификации конца потока. Вместо этого можно использовать метод available().
class TestEOF {
    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("TestEOF.java")));
        while (in.available() != 0)
            System.out.print((char) in.readByte());
    }
}