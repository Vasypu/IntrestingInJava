package exceptions;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *  Демонстрируется возможность игнорировать исключение и пустить его «всплывать»
 *  вверх по стеку вызова без необходимости писать конструкции try-catch и/или
 *  спецификации исключений. Впрочем, вы все еще можете перехватить и обработать
 *  некоторое исключение, используя метод getCause(),
 */

class WrapCheckedException {
    void throwRuntimeException(int type) {
        try {
            switch (type) {
                case 0:
                    throw new FileNotFoundException();
                case 1:
                    throw new IOException();
                case 2:
                    throw new RuntimeException("Где я?");
                default:
                    return;
            }
        } catch (Exception e) { // Превращаем в неконтролируемое:
            throw new RuntimeException(e);
        }
    }
}

class SomeOtherException extends Exception {}

public class TurnOffChecking {
    public static void main(String[] args) {
        WrapCheckedException wce = new WrapCheckedException();
        // Вы можете вызвать f() без блока try и позволить
        // исключению RuntimeException покинуть метод:
        wce.throwRuntimeException(3);
        // Или перехватить исключения:
        for (int i = 0; i < 4; i++)
            try {
                if (i < 3)
                    wce.throwRuntimeException(i);
                else
                    throw new SomeOtherException();
            } catch (SomeOtherException e) {
                System.out.println("SomeOtherException: " + e);
            } catch (RuntimeException re) {
                try {
                    throw re.getCause();
                } catch (FileNotFoundException e) {
                    System.out.println("FileNotFoundException: " + e);
                } catch (IOException e) {
                    System.out.println("IOException: " + e);
                } catch (Throwable e) {
                    System.out.println("Throwable: " + e);
                }
            }
    }
}