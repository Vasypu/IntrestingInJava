package io;

import java.nio.*;

/**
 *  Представления буферов
 *  <p>
 *  «Представления буферов» дают вам возможность интерпретировать байтовый буфер через «окно» конкретного примитивного
 *  типа. Байтовый буфер все так же хранит действительные данные и одновременно поддерживает представление, поэтому все
 *  изменения, которые вы сделаете в представлении, отразятся на содержимом байтового буфера. Как мы увидели из предыдущего
 *  примера, это удобно для вставки значений примитивов в байтовый буфер.
 *  <p>
 *  один и тот же массив байт может представлять разные данные для разных типов данных.
 */
public class IntBufferDemo {
    private static final int BSIZE = 1024;
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        IntBuffer ib = bb.asIntBuffer();
        // Сохраняем массив целых чисел:
        ib.put(new int[] { 11, 42, 47, 99, 143, 811, 1016 });
        // Чтение и запись по абсолютным позициям:
        System.out.println(ib.get(3));
        ib.put(3, 1811);
        // Установление нового предела перед возвратом к началу буфера
        ib.flip();
        while(ib.hasRemaining()) {
            int i = ib.get();
            System.out.println(i);
        }
    }
}

// Следующий пример интерпретирует одну и ту же последовательность байтов как числа short, int, float, long и double,
// создавая для одного байтового буфера ByteBuffer различные представления.
class ViewBuffers {
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[] { 0, 0, 0, 0, 0, 0, 0, 'a' });
        bb.rewind();
        System.out.print("Byte Buffer ");
        while(bb.hasRemaining())
            System.out.print(bb.position()+ " -> " + bb.get() + ", ");
        System.out.println();
        CharBuffer cb = ((ByteBuffer)bb.rewind()).asCharBuffer();
        System.out.print("Char Buffer ");
        while(cb.hasRemaining())
            System.out.print(cb.position() + " -> " + cb.get() + ", ");
        System.out.println();
        FloatBuffer fb = ((ByteBuffer)bb.rewind()).asFloatBuffer();
        System.out.print("Float Buffer ");
        while(fb.hasRemaining())
            System.out.print(fb.position() + " -> " + fb.get() + ", ");
        System.out.println();
        IntBuffer ib = ((ByteBuffer)bb.rewind()).asIntBuffer();
        System.out.print("Int Buffer ");
        while(ib.hasRemaining())
            System.out.print(ib.position()+ " -> " + ib.get() + ", ");
        System.out.println();
        LongBuffer lb = ((ByteBuffer)bb.rewind()).asLongBuffer();
        System.out.print("Long Buffer ");
        while(lb.hasRemaining())
            System.out.print(lb.position() + " -> " + lb.get() + ", ");
        System.out.println();
        ShortBuffer sb = ((ByteBuffer)bb.rewind()).asShortBuffer();
        System.out.print("Short Buffer ");
        while(sb.hasRemaining())
            System.out.print(sb.position()+ " -> " + sb.get() + ", ");
        System.out.println();
        DoubleBuffer db = ((ByteBuffer)bb.rewind()).asDoubleBuffer();
        System.out.print("Double Buffer ");
        while(db.hasRemaining())
            System.out.print(db.position()+ " -> " + db.get() + ", ");
    }
}