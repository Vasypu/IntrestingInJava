package exceptions;

/**
 *  Потерянное исключение
 *
 *  После каждого блока try обязательно нужно вызывать обработчик исключений
 *  иначе возможна потеря исключения.
 */

class VeryImportantException extends Exception {
    public String toString() {
        return "Очень важное исключение!";
    }
}

class HoHumException extends Exception {
    public String toString() {
        return "Второстепенное исключение";
    }
}

public class LostMessage {
    void f() throws VeryImportantException {
        throw new VeryImportantException();
    }
    void dispose() throws HoHumException {
        throw new HoHumException();
    }
    public static void main(String[] args) {
        try {
            LostMessage lm = new LostMessage();
            try {
                lm.f();
            } finally {
                lm.dispose();
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}

class ExceptionSilencer {
    public static void main(String[] args) {
        try {
            throw new RuntimeException();
        } finally {
// Использование 'return' в блоке finally
// подавляет любое возбужденное исключение,
            return;
        }
    }
}
