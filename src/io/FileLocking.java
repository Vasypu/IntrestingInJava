package io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 *  Блокировка файлов
 *  <p>
 *  Блокировка файлов, появившаяся в naKeTeJDK 1.4, позволяет синхронизировать доступ к файлу как к совместно используемому
 *  ресурсу. Впрочем, программные потоки, претендующие на один и тот же файл, могут принадлежать различным виртуальным
 *  машинам JVM, или один поток может быть Java-потоком, а другой представлять собой обычный поток операционной системы.
 *  Блокированные файлы видны другим процессам операционной системы, поскольку механизм блокировки Java напрямую связан
 *  со средствами операционной системы.
 *  <p>
 *  Метод tryLock() не приостанавливает программу. Он пытается овладеть объектом блокировки, но если ему это не удается
 *  (если другой процесс уже владеет этим объектом или файл не является разделяемым), то он просто возвращает управление.
 *  Метод lock() ждет до тех пор, пока не удастся получить объект блокировки, или поток, в котором этот метод был вызван,
 *  не будет прерван, или же пока не будет закрыт канал, для которого был вызван метод lock(). Блокировка снимается методом
 *  FileChannel.release()
 *  <p>
 *
 */
public class FileLocking {
    public static void main(String[] args) throws Exception {
        FileOutputStream fos= new FileOutputStream("file.txt");
        FileLock fl = fos.getChannel().tryLock();
        if(fl != null)
        {
            System.out.println("Locked File");
            TimeUnit.MILLISECONDS.sleep(100);
            fl.release();
            System.out.println("Released Lock");
        }
        fos.close();
    }
}

/**
 *  Блокирование части отображаемого файла
 *  <p>
 *  Как упоминалось ранее, отображение файлов обычно применяется для очень больших файлов. При работе с таким большим
 *  файлом может понадобиться заблокировать некоторые его части, так чтобы другие процессы могли изменять его доступные
 *  участки.
 */

// Класс потока LockAndModify устанавливает область буфера и получает его для модификации методом slice(). В методе run()
// запрашивается объект блокировки для файлового канала (запросить блокировку для буфера нельзя — это возможно только для
// канала). Вызов метода lock() напоминает механизм синхронизации доступа потоков к объектам, у вас появляется некая
// «критическая секция» с монопольным доступом к данной части файла. Блокировки автоматически снимаются при завершении
// работы JVM, закрытии канала, для которого они были получены, но можно также явно вызвать метод release().
class LockingMappedFiles {
    static final int LENGTH = 0x8FFFFFF; // 128 Мбайт
    static FileChannel fc;
    public static void main(String[] args) throws Exception {
        fc = new RandomAccessFile("test.dat", "rw").getChannel();
        MappedByteBuffer out = fc.map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);
        for (int i = 0; i < LENGTH; i++)
            out.put((byte) 'x');
        new LockAndModify(out, 0, 0 + LENGTH / 3);
        new LockAndModify(out, LENGTH / 2, LENGTH / 2 + LENGTH / 4);
    }

    private static class LockAndModify extends Thread {
        private ByteBuffer buff;
        private int start, end;
        LockAndModify(ByteBuffer mbb, int start, int end) {
            this.start = start;
            this.end = end;
            mbb.limit(end);
            mbb.position(start);
            buff = mbb.slice();
            start();
        }
        public void run() {
            try {
                // Монопольная блокировка без перекрытия:
                FileLock fl = fc.lock(start, end, false);
                System.out.println("Locked: " + start +" to " + end);
                // Модификация:
                while(buff.position() < buff.limit() - 1)
                    buff.put((byte)(buff.get() + 1));
                fl.release();
                System.out.println("Released: " + start + " to " + end);
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}