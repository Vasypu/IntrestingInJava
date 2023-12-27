package containers;

import java.util.BitSet;
import java.util.Random;

/**
 *  BitSet
 *  <p>
 *  Класс BitSet служит для эффективного хранения большого количества «переключателей» с двумя состояниями (включено-выключено).
 *  Он эффективен только с точки зрения размеров; по эффективности доступа он обычно уступает низкоуровневым массивам.
 *  <p>
 *  EnumSet обычно предпочтительнее BitSet при работе с фиксированным набором флагов, которому можно присвоить имя (вместо числовых
 *  позиций битов), — это снижает вероятность ошибок, потому что EnumSet позволяет работать с именами вместо позиций конкретных битов.
 *  EnumSet также предотвращает случайное добавление новых флагов, что приводит к появлению серьезных, трудно обнаружимых ошибок.
 */
public class Bits {
    public static void printBitSet(BitSet b) {
        System.out.println("bits: " + b);
        StringBuilder bbits = new StringBuilder();
        for(int j = 0; j < b.size() ; j++)
            bbits.append(b.get(j) ? "1" : "0");
        System.out.println("bit pattern: " + bbits);
    }

    public static void main(String[] args) {
        Random rand = new Random(47);
        // Получить младший бит nextInt():
        byte bt = (byte)rand.nextInt();
        BitSet bb = new BitSet();
        for(int i = 7; i >= 0; i--)
            if(((1 << i) & bt) != 0)
                bb.set(i);
            else
                bb.clear(i);
        System.out.println("byte value: " + bt);
        printBitSet(bb);

        short st = (short)rand.nextInt();
        BitSet bs = new BitSet();
        for(int i = 15; i >= 0; i--)
            if(((1 << i) & st) != 0)
                bs.set(i);
            else
                bs.clear(i);
        System.out.println("short value: " + st);
        printBitSet(bs);

        int it = rand.nextInt();
        BitSet bi = new BitSet();
        for(int i = 31; i >= 0; i--)
            if(((1 << i) & it) != 0) bi.set(i);
            else
                bi.clear(i);
        System.out.println("int value: " + it);
        printBitSet(bi);

        // Тестирование набора битов размером >= 64 бита:
        BitSet bl27 = new BitSet();
        bl27.set(127);
        System.out.println("set bit 127: " + bl27);
        BitSet b255 = new BitSet(65);
        b255.set(255);
        System.out.println("set bit 255: " + b255);
        BitSet bl023 = new BitSet(512);
        bl023.set(1023);
        bl023.set(1024);
        System.out.println("set bit 1023: " + bl023);
    }
}