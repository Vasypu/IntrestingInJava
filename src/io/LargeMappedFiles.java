package io;

import java.io.*;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 *  Отображаемые в память файлы
 *  <p>
 *  Механизм отображения файлов в память позволяет вам создавать и изменять файлы, размер которых слишком велик для
 *  размещения в памяти. В таком случае вы считаете, что файл целиком находится в памяти, и работаете с ним как с очень
 *  большим массивом.
 *  <p>
 *  Для того чтобы производить одновременное чтение и запись, мы начинаем с создания объекта RandomAccessFile, получаем
 *  для этого файла канал, а затем вызываем метод map(), чтобы произвести буфер MappedByteBuffer, представляющий собой
 *  разновидность буфера прямого доступа. Заметьте, что необходимо указать начальную точку и длину участка, который будет
 *  проецироваться, то есть у вас есть возможность отображать маленькие участки больших файлов.
 *  <p>
 *  Программа напрямую создает файл размером в 128 Мбайт, что может оказаться не «по плечу» операционной системе. Кажется,
 *  что весь файл доступен сразу, поскольку только часть его подгружается в память, в то время как остальные части
 *  выгружены. Таким образом можно работать с очень большими файлами (размером до 2 Гбайт). Заметьте, что для достижения
 *  максимальной производительности работает механизм отображения файлов используемой операционной системы.
 */
public class LargeMappedFiles {
    static int length = 0x8FFFFFF; // 128 Мбайт
    public static void main(String[] args) throws Exception {
        MappedByteBuffer out = new RandomAccessFile("test.dat", "rw")
                .getChannel().map(FileChannel.MapMode.READ_WRITE, 0, length);
        for(int i = 0; i < length; i++)
            out.put((byte)'x');
        System.out.println("Finished writing");
        for(int i = length/2; i < length/2 + 6; i++)
            System.out.print((char)out.get(i));
    }
}


// Хотя быстродействие «старого» ввода-вывода было улучшено за счет переписывания его с учетом новых библиотек nio,
// техника отображения файлов качественно эффективнее.

// Отметим, что в методы test() также включена инициализация различных объектов для работы с вводом/выводом. И несмотря
// на то, что настройка отображаемых файлов может быть сопряжена с определенными затратами, общее преимущество по сравнению
// с потоковым вводом/выводом все равно получается значительным.
class MappedIO {
    private static int numOfInts = 4000000;
    private static int numOfUbuffInts = 200000;
    private abstract static class Tester {
        private String name;
        public Tester(String name) { this.name = name; }

        public void runTest() {
            System.out.print(name + ": ");
            try {
                long start = System.nanoTime();
                test();
                double duration = System.nanoTime() - start;
                System.out.format("%.2f\n", duration/1.0e9);
            } catch(IOException e) { throw new RuntimeException(e);
            }
        }
        public abstract void test() throws IOException;
    }
    private static Tester[] tests = {
            new Tester("Stream Write") {
        public void test() throws IOException {
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("temp.tmp"))));
            for (int i = 0; i < numOfInts; i++)
                dos.writeInt(i);
            dos.close();
        }
    },
            new Tester("Mapped Write") {
                public void test() throws IOException{
                    FileChannel fc = new RandomAccessFile("temp.tmp","rw").getChannel();
                    IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE,0,fc.size()).asIntBuffer();
                    for(int i = 0; i < numOfInts; i++)
                        ib.put(i);
                    fc.close();
                }
            },
            new Tester("Stream Read") {
                public void test() throws IOException {
                    DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream("temp.tmp")));
                    for(int i = 0; i < numOfInts; i++)
                        dis.readInt();
                    dis.close();
                }
            },
            new Tester("Mapped Read") {
                public void test() throws IOException{
                    FileChannel fc = new FileInputStream(new File("temp.tmp")).getChannel();
                    IntBuffer ib = fc.map(FileChannel.MapMode.READ_ONLY,0, fc.size()).asIntBuffer();
                    while(ib.hasRemaining())
                        ib.get();
                    fc.close();
                }
            },
            new Tester("Stream Read/Write") {
                public void test() throws IOException {
                    RandomAccessFile raf = new RandomAccessFile(new File("temp.tmp"), "rw");
                    raf.writeInt(1);
                    for(int i = 0; i < numOfUbuffInts; i++) {
                        raf.seek(raf.length()-4);
                        raf.writeInt(raf.readInt());
                    }
                    raf.close();
                }
            },
            new Tester("Mapped Read/Write") {
                public void test() throws IOException{
                    FileChannel fc = new RandomAccessFile(new File("temp.tmp"), "rw").getChannel();
                    IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0,fc.size()).asIntBuffer();
                    ib.put(0);
                    for(int i = 1; i < numOfUbuffInts; i++)
                        ib.put(ib.get(i - 1));
                    fc.close();
                }
            }
    };

    public static void main(String[] args) {
        for (Tester test : tests)
            test.runTest();
    }   }