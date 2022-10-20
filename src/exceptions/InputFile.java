package exceptions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *  Конструкторы
 *
 *  Самый безопасный способ использования класса, который может выдавать исключения
 *  во время выполнения конструктора и требует завершающих действий, заключается в
 *  использовании вложенных блоков try.
 */

public class InputFile {
    private BufferedReader in;

    public InputFile(String fname) throws Exception {
        try {
            in = new BufferedReader(new FileReader(fname));
            // Остальной код, способный возбуждать исключения
        } catch (FileNotFoundException e) {
            System.out.println("Could not open " + fname);
            // Файл не был открыт, закрывать не нужно
            throw e;
        } catch (Exception e) {
            // При других исключениях необходимо закрыть файл
            try {
                in.close();
            } catch (IOException e2) {
                System.out.println("ошибка при выполнении in.close()");
            }
            throw e; // Rethrow
        } finally {
            // Здесь файл не закрывается!!!
        }
    }

    public String getLine() {
        String s;
        try {
            s = in.readLine();
        } catch (IOException e) {
            throw new RuntimeException("ошибка при выполнении readLine()");
        }
        return s;
    }

    public void dispose() {
        try {
            in.close();
            System.out.println("dispose() успешен");
        } catch (IOException e2) {
            throw new RuntimeException("ошибка при выполнении in.close()");
        }
    }
}

//Если при конструировании произойдет ошибка, программа входит во внешний блок catch,
// а метод dispose() не вызывается. Но если конструирование проходит успешно, необходимо
// проследить за тем, чтобы с объектом были выполнены завершающие действия, поэтому сразу
// же после конструирования создается новый блок try. Блок finally, выполняющий завершающие
// действия, связан с внутренним блоком try; в случае неудачи при конструировании блок
// finally не выполняется, но он всегда будет выполнен в случае успешного конструирования.

class Cleanup {
    public static void main(String[] args) {
        try {
            InputFile in = new InputFile("C:\\Users\\Vasypu\\Desktop\\test\\Cleanup.java");
            try {
                String s;
                int i = 1;
                while ((s = in.getLine()) != null)
                    ; // Обработка данных по строкам...
            } catch (Exception e) {
                System.out.println("перехвачено исключение Exception в main");
                e.printStackTrace(System.out);
            } finally {
                in.dispose();
            }
        } catch (Exception e) {
            System.out.println("Ошибка при конструировании InputFile");
        }
    }
}

// За созданием каждого объекта, нуждающегося в завершении,
// должна следовать конструкция try-finally
class NeedsCleanup { // При конструировании ошибок быть не может
    private static long counter = 1;
    private final long id = counter++;

    public void dispose() {
        System.out.println("NeedsCleanup " + id + " освобожден");
    }
}

class ConstructionException extends Exception {}

class NeedsCleanup2 extends NeedsCleanup {
    // Возможны ошибки при конструировании:
    public NeedsCleanup2() throws ConstructionException {
    }
}

class CleanupIdiom {
    public static void main(String[] args) {
        // Часть 1:
        NeedsCleanup ncl = new NeedsCleanup();
        try {
            // ...
        } finally {
            ncl.dispose();
        }
        // Часть 2:
        // Если ошибки конструирования невозможны, объекты можно группировать:
        NeedsCleanup nc2 = new NeedsCleanup();
        NeedsCleanup nc3 = new NeedsCleanup();
        try {
            // ...
        } finally {
            nc3.dispose(); // Обратный порядок конструирования
            nc2.dispose();
        }
        // Часть 3:
        // Если при конструировании возможны ошибки,
        // необходимо защитить каждую операцию:
        try {
            NeedsCleanup2 nc4 = new NeedsCleanup2();
            try {
                NeedsCleanup2 nc5 = new NeedsCleanup2();
                try {
                    // ...
                } finally {
                    nc5.dispose();
                }
            } catch (ConstructionException e) { // Конструктор nc5
                System.out.println(e);
            } finally {
                nc4.dispose();
            }
        } catch (ConstructionException e) { // Конструктор nc4 System.out.println(e)j
        }
    }
}