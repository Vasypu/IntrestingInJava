package io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *  Чтение двоичных файлов
 *  <p>
 *  Метод available() возвращает размер массива, а эта перегруженная версия метода read() заполняет массив.
 */
public class BinaryFile {
    public static byte[] read(File bFile) throws IOException {
        try (BufferedInputStream bf = new BufferedInputStream(new FileInputStream(bFile))) {
            byte[] data = new byte[bf.available()];
            bf.read(data);
            return data;
        }
    }
    public static byte[] read(String bFile) throws IOException {
        return read(new File(bFile).getAbsoluteFile());
    }

    public static void main(String[] args) throws IOException {
        var bytes = read("src/io/TextFile.java");
        System.out.println("File reed");
    }
}
