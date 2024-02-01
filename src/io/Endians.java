package io;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 *  Данные о двух концах
 *  <p>
 *  На разных компьютерах данные могут храниться с различным порядком следования байтов. Прямой порядок big_endian
 *  располагает старший байт по младшему адресу памяти, адля обратного порядка little_endian старший байт помещается по
 *  высшему адресу памяти. При хранении значения, занимающего более одного байта, такого как число int, float и т. n.,
 *  вам, возможно, придется учитывать различные варианты следования байтов в памяти. Буфер ByteBuffer укладывает данные
 *  в порядке big_endian, такой же способ всегда используется для данных, пересылаемых по сети.
 *  <p>
 *  Если вы прочитаете эти данные как тип short (ByteBuffer.asShortBuffer()), то получите число 97 (00000000 01100001),
 *  однако при изменении порядка байтов вы увидите число 24 832 (01100001 00000000).
 */
// Метод array() является необязательным и вызывать его следует только для буфера на базе массива; в противном случае
// возбуждается исключение UnsupportedOperationException.
public class Endians {
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[12]);
        bb.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(bb.array()));
        bb.rewind();
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(bb.array()));
        bb.rewind();
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(bb.array()));
    }
}
