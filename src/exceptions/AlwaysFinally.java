package exceptions;

/**
 *  Использование finally
 */

class FourException extends Exception {}

public class AlwaysFinally {
    public static void main(String[] args) {
        System.out.println("Входим в первый блок try");
        try {
            System.out.println("Входим во второй блок try");
            try {
                throw new FourException();
            } finally {
                System.out.println("finally во втором блоке try");
            }
        } catch (FourException e) {
            System.out.println("Перехвачено FourException в первом блоке try");
        } finally {
            System.out.println("finally в первом блоке try");
        }
    }
}

//  finally при return
class MultipleReturns {
    public static void f(int i) {
        System.out.println("Инициализация, требующая завершения");
        try {
            System.out.println("Точка 1");
            if (i == 1) return;
            System.out.println("Точка 2");
            if (i == 2) return;
            System.out.println("Точка 3");
            if (i == 3) return;
            System.out.println("Конец");
        } finally {
            System.out.println("Завершающее действия");
        }
    }
    public static void main(String[] args) {
        for(int i = 1; i <= 4; i++)
            f(i);
    }
}