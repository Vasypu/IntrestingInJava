package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *  Новый ввод-вывод (nio)
 *  <p>
 *  Библиотека ориентирована на единственную цель: скорость. Увеличения скорости удалось достичь с помощью структур,
 *  близких к средствам самой операционной системы: это каналы (channels) и буферы (buffers). Единственный тип буфера,
 *  который напрямую взаимодействует с каналом, — буфер ByteBuffer, то есть буфер, хранящий простые байты.
 *  <p>
 *  Три класса из «старой» библиотеки ввода-вывода были изменены так, чтобы они позволяли получить канал FileChannel:
 *  это FileInputStream, FileOutputStream и позволяющий одновременное чтение и запись RandomAccessFile. Заметьте, что эти
 *  классы манипулируют байтами, что согласуется с низкоуровневой направленностью nio. Классы для символьных данных Reader
 *  и Writer не образуют каналов, однако вспомогательный класс java.nio.channels.Channels имеет набор методов, позволяющих
 *  произвести объекты Reader и Writer из каналов.
 */
public class GetChannel {
    private static final int BSIZE = 1024;
    public static void main(String[] args) throws Exception {
        // Запись файла:
        FileChannel fc = new FileOutputStream("data.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text ".getBytes()));
        fc.close();
        // Добавление в конец файла:
        fc = new RandomAccessFile("data.txt", "rw").getChannel();
        fc.position(fc.size()); // Перемещение в конец
        fc.write(ByteBuffer.wrap("Some more".getBytes()));
        fc.close();
        // Чтение файла:
        fc = new FileInputStream("data.txt").getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        fc.read(buff);
        buff.flip();
        while(buff.hasRemaining())
            System.out.print((char)buff.get());
    }
}

// После каждого вызова метода read(), помещающего данные в буфер, метод flip() подготавливает буфер так, чтобы
// информация из него могла быть извлечена методом write(). После вызова write() информация все еще хранится в буфере,
// поэтому метод clear() сбрасывает все внутренние указатели, чтобы буфер снова был способен принимать данные в методе
// read().
class ChannelCopy {
    private static final int BSIZE = 1024;
    public static void main(String[] args) throws Exception {
        if(args.length != 2) {
            System.out.println("arguments: sourcefile destfile");
            System.exit(1);
        }
        FileChannel in = new FileInputStream(args[0]).getChannel(),
                out = new FileOutputStream(args[1]).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        while(in.read(buffer) != -1) {
            buffer.flip(); // Подготовка к записи
            out.write(buffer);
            buffer.clear(); // Подготовка к чтению
        }
    }
}

// Специальные методы transferTo() и transferFrom() позволяют напрямую присоединить один канал к другому.
// Что позволяет копировать файлы.
class TransferTo {
    public static void main(String[] args) throws Exception {
        if(args.length != 2) {
            System.out.println("arguments: sourcefile destfile");
            System.exit(1);
        }
        FileChannel in = new FileInputStream(args[0]).getChannel(),
                out = new FileOutputStream(args[1]).getChannel();
        in.transferTo(0, in.size(), out);
        // Или:
        // out.transferFrom(in, 0, in.size());
    }
}