package io;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *  Чтение/запись файлов с произвольным доступом
 *  <p>
 *  Можно предположить, что класс доступа к буферу памяти ByteArrayInputStream стал бы хорошим потоком произвольного
 *  доступа, но класс RandomAccessFile работает только с файлами. Буферизация уже встроена в него и присоединить ее, как
 *  это делается с потоками, нельзя.
 *  <p>
 *  Все возможности настройки сводятся ко второму аргументу конструктора: можно открыть RandomAccessFile только для
 *  чтения (указав строку «г») или для чтения/записи (строка «rw»).
 */
public class UsingRandomAccessFile {
    static String file = "rtest.dat";
    static void display() throws IOException {
        RandomAccessFile rf = new RandomAccessFile(file, "r");
        for(int i = 0; i < 7; i++)
            System.out.println("Value " + i + ": " + rf.readDouble());
        System.out.println(rf.readUTF());
        rf.close();
    }

    public static void main(String[] args) throws IOException {
        RandomAccessFile rf = new RandomAccessFile(file, "rw");
        for (int i = 0; i < 7; i++)
            rf.writeDouble(i * 1.414);
        rf.writeUTF("The end of the file");
        rf.close();
        display();
        rf = new RandomAccessFile(file, "rw");
        // записываем в определенную позицию
        rf.seek(5 * 8);
        rf.writeDouble(47.0001);
        rf.close();
        display();
    }
}