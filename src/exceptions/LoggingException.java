package exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 *  Вывод информации об исключениях
 *
 *  Метод getStackTrace() возвращает массив элементов трассировки стека;
 *  каждый элемент представляет один кадр стека.
 */

public class LoggingException extends Exception {
    private static final Logger logger = Logger.getLogger("LoggingException");

    public LoggingException() {
        StringWriter trace = new StringWriter();
        printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }
}

class LoggingExceptions {
    public static void main(String[] args) {
        try {
            throw new LoggingException();
        } catch(LoggingException e) {
            System.err.println("Перехвачено " + e);
        }
        try {
            throw new LoggingException();
        } catch(LoggingException e){
            System.err.println("Перехвачено " + e);
        }
    }
}

class LoggingExceptions2 {
    private static final Logger logger = Logger.getLogger("LoggingExceptions2");

    static void logException(Exception e) {
        StringWriter trace = new StringWriter();
        e.printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }

    public static void main(String[] args) {
        try {
            throw new NullPointerException();
        } catch (NullPointerException e) {
            logException(e);
        }
    }
}

class MyException2 extends Exception {
    private int x;
    public MyException2() {}
    public MyException2(String msg) { super(msg); }
    public MyException2(String msg, int x){
        super(msg);
        this.x = x;
    }
    public int val() { return x; }
    public String getMessage() {
        return "Подробное сообщение: " + x + " " + super.getMessage();
    }
}
class ExtraFeatures {
    public static void f() throws MyException2 {
        System.out.println("Возбуждаем MyException2 из f()");
        throw new MyException2();
    }
    public static void g() throws MyException2 {
        System.out.println("Возбуждаем MyException2 из g()");
        throw new MyException2("Создано в g()");
    }
    public static void h() throws MyException2 {
        System.out.println("Возбуждаем MyException2 из h()");
        throw new MyException2("Создано в h()", 47);
    }
    public static void main(String[] args) {
        try {
            f();
        } catch (MyException2 e) {
            e.printStackTrace(System.out);
        }
        try {
            g();
        } catch (MyException2 e) {
            e.printStackTrace(System.out);
        }
        try {
            h();
        } catch (MyException2 e) {
            e.printStackTrace(System.out);
            System.out.println("e.val() = " + e.val());
        }
    }
}

// Программный доступ к информации трассировки стека
// Здесь мы выводим имена методов, которые спровоцировали ошибку
class WhoCalled {
    static void f() {
        // Генерируем исключение для заполнения трассировки стека
        try {
            throw new Exception();
        } catch (Exception e) {
            for (StackTraceElement ste : e.getStackTrace())
                System.out.println(ste.getMethodName());
        }
    }
    static void g() { f(); }
    static void h() { g(); }
    public static void main(String[] args) {
        f();
        System.out.println("--------------------------------------------------------");
        g();
        System.out.println("--------------------------------------------------------");
        h();
    }
}