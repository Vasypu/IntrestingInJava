package io;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

/**
 *  Простое сжатие в формате GZIP
 *  <p>
 *  Интерфейс сжатия в формате GZIP является наиболее простым и идеально подходит для ситуаций с одним потоком данных,
 *  который необходимо уплотнить (в отличие от набора фрагментов данных).
 */
public class GZIPcompress {
    public static void main(String[] args) throws IOException {
        if(args.length == 0) {
            System.out.println("Usage: \nGZIPcompress file\n" +
                            "\tUses GZIP compression to compress " +
                            "the file to test.gz");
            System.exit(1);
        }
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream("test.gz")));
        System.out.println("Writing file");
        int c;
        while ((c = in.read()) != -1)
            out.write(c);
        in.close();
        out.close();

        System.out.println("Reading file");
        BufferedReader in2 = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream("test.gz"))));
        String s;
        while ((s = in2.readLine()) != null)
            System.out.println(s);
    }
}

class ZipCompress {
    public static void main(String[] args) throws IOException {
        FileOutputStream f = new FileOutputStream("test.zip");
        CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
        ZipOutputStream zos = new ZipOutputStream(csum);
        BufferedOutputStream out = new BufferedOutputStream(zos);
        zos.setComment("A test of Java Zipping");
        // Впрочем, соответствующего метода getComment() нет.
        for(String arg : args) {
            System.out.println("Writing file " + arg);
            BufferedReader in = new BufferedReader(new FileReader(arg));
            zos.putNextEntry(new ZipEntry(arg));
            int c;
            while((c = in.read()) != -1)
                out.write(c);
            in.close();
            out.flush();
        }
        out.close();
        // Контрольная сумма действительна только после закрытия файла!
        System.out.println("Checksum: " + csum.getChecksum().getValue());
        // Теперь извлечь файлы:
        System.out.println("Reading file");
        FileInputStream fi = new FileInputStream("test.zip");
        CheckedInputStream csumi = new CheckedInputStream(fi, new Adler32());
        ZipInputStream in2 = new ZipInputStream(csumi);
        BufferedInputStream bis = new BufferedInputStream(in2);
        ZipEntry ze;
        while((ze = in2.getNextEntry()) != null) {
            System.out.println("Reading file " + ze);
            int x;
            while((x = bis.read()) != -1)
                System.out.write(x);
        }
        if(args.length == 1)
            System.out.println("Checksum: " + csumi.getChecksum().getValue());
        bis.close();
        // Альтернативный способ открытия и чтения ZIP-файлов:
        ZipFile zf = new ZipFile("test.zip");
        Enumeration e = zf.entries();
        while(e.hasMoreElements()) {
            ZipEntry ze2 = (ZipEntry)e.nextElement();
            System.out.println("File: " + ze2);
            // ...после чего данные извлекаются так же, как прежде
        }
        /* if(args.length ~ 1) */
    }
}