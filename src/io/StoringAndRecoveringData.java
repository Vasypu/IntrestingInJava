package io;

import java.io.*;

/**
 *  Сохранение и восстановление данных
 *  <p>
 *  DataOutputStream и DataInputStream ориентированы на отправку байтов, поэтому для них потребуются потоки OutputStream
 *  и InputStream.
 *  <p>
 *  Чтобы правильно интерпретировать любые данные, вы должны точно знать их расположение в потоке, поскольку с таким же
 *  успехом можно прочитать число double как какую-то последовательность байтов или символов. Поэтому данные в файле
 *  должны храниться в определенном формате, или придется использовать дополнительную информацию, которая будет показывать,
 *  какие именно данные находятся в тех или иных местах.
 */
public class StoringAndRecoveringData {
    public static void main(String[] args) throws IOException {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("Data.txt")));
        out.writeDouble(3.14159);
        out.writeUTF("That was pi");
        out.writeDouble(1.41413);
        out.writeUTF("Square root of 2");
        out.close();
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("Data.txt")));
        System.out.println(in.readDouble());
        // Только readUTF() правильно восстановит объект String в кодировке UTF для Java:
        System.out.println(in.readUTF());
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
    }
}
