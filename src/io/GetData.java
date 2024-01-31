package io;

import java.nio.ByteBuffer;

/**
 *  Извлечение примитивов
 *  <p>
 *  Несмотря на то, что в буфере ByteBuffer хранятся только байты, он поддерживает методы, образующие любые значения
 *  примитивов из этих байтов.
 *  <p>
 *  Простейший способ вставить примитив в ByteBuffer — получить подходящее «представление» этого буфера методами
 *  asCharBuffer(), asShortBuffer() и т. n., а затем поместить в это представление значение методом put(). В примере мы
 *  так поступаем для каждого из примитивных типов. Единственное отклонение из этого ряда — использование буфера
 *  ShortBuffer, требующего приведения типов (которое усекает и изменяет результирующее значение). Все остальные
 *  представления не нуждаются в преобразовании типов.
 */
public class GetData {
    private static final int BSIZE = 1024;
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        // При выделении буфер автоматически заполняется нулями:
        int i = 0;
        while(i++ < bb.limit())
            if(bb.get() != 0)
                System.out.println("nonzero");
        System.out.println("i = " + i);
        bb.rewind();
        // Сохраняем и читаем символьный массив:
        bb.asCharBuffer().put("Howdy!");
        char c;
        while((c = bb.getChar()) != 0)
            System.out.print(c + " ");
        System.out.println();
        bb.rewind();
        // Сохраняем и читаем число типа short:
        bb.asShortBuffer().put((short)471142);
        System.out.println(bb.getShort());
        bb.rewind();
        // Сохраняем и читаем число типа int:
        bb.asIntBuffer().put(99471142);
        System.out.println(bb.getInt());
        bb.rewind();
        // Сохраняем и читаем число типа long:
        bb.asLongBuffer().put(99471142);
        System.out.println(bb.getLong());
        bb.rewind();
        // Сохраняем и читаем число типа float:
        bb.asFloatBuffer().put(99471142);
        System.out.println(bb.getFloat());
        bb.rewind();
        // Сохраняем и читаем число типа double:
        bb.asDoubleBuffer().put(99471142);
        System.out.println(bb.getDouble());
        bb.rewind();
    }
}
