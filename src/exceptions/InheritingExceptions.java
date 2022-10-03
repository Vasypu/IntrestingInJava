package exceptions;

/**
 *  Создание собственных исключений
 */

class SimpleException extends Exception {}

public class InheritingExceptions {
    public void f() throws SimpleException {
        System.out.println("Возбуждаем SimpleException из f()");
        throw new SimpleException();
    }
    public static void main(String[] args) {
        InheritingExceptions sed = new InheritingExceptions();
        try {
            sed.f();
        } catch (SimpleException e) {
            System.out.println("Перехвачено!");
        }
    }
}

// Класс исключения с конструктором, который получает аргумент-строку
class MyException extends Exception {
    public MyException() {}
    public MyException(String msg) { super(msg); }
}

class FullConstructors {
    public static void f() throws MyException {
        System.out.println("Возбуждаем MyException из f()");
        throw new MyException();
    }
    public static void g() throws MyException {
        System.out.println("Возбуждаем MyException из g()");
        throw new MyException("Создано в g()");
    }
    public static void main(String[] args) {
        try {
            f();
        } catch(MyException e) {
            e.printStackTrace(System.out);
        }
        try {
            g();
        } catch(MyException e) {
            e.printStackTrace(System.out);
        }
    }
}