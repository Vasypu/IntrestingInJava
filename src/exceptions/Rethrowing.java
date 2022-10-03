package exceptions;

/**
 *  Повторное возбуждение исключения
 *
 *  Повторное возбуждение исключения заставляет его перейти в распоряжение обработчика
 *  исключений более высокого уровня. Все последующие блоки catch текущего блока try
 *  игнорируются. Кроме того, вся информация из объекта, представляющего исключение,
 *  сохраняется, соответственно, обработчик более высокого уровня, перехватывающий
 *  подобные исключения, сможет ее извлечь.
 *  Если вы просто заново возбуждаете исключение, информация о нем, печатаемая методом
 *  printStackTrace(), будет по-прежнему относиться к месту, где возникло исключение,
 *  но не к месту его повторного возбуждения. Если вам понадобится использовать новую
 *  информацию о трассировке стека, используйте метод fillInStackTrace(), который
 *  возвращает исключение (объект Throwable), созданное на основе старого включением
 *  туда текущей информации о стеке.
 *  Строка, в которой вызывается метод fillInStackTrace(), становится новой точкой происхождения исключения
 */

public class Rethrowing {
    public static void f() throws Exception {
        System.out.println("Создание исключения в f()");
        throw new Exception("Возбуждено из f()");
    }

    public static void g() throws Exception {
        try {
            f();
        } catch (Exception e) {
            System.out.println("B методе g(), e.printStackTrace()");
            e.printStackTrace(System.out);
            throw e;
        }
    }

    public static void h() throws Exception {
        try {
            f();
        } catch (Exception e) {
            System.out.println("B методе h(), e.printStackTrace()");
            e.printStackTrace(System.out);
            throw (Exception) e.fillInStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            g();
        } catch (Exception e) {
            System.out.println("main: printStackTrace()");
            e.printStackTrace(System.out);
        }
        try {
            h();
        } catch (Exception e) {
            System.out.println("main: printStackTrace()");
            e.printStackTrace(System.out);
        }
    }
}

class OneException extends Exception {
    public OneException(String s) { super(s); }
}
class TwoException extends Exception {
    public TwoException(String s) { super(s); }
}
/*class RethrowNew {
    public static void f() throws OneException {
        System.out.println("Создание исключения в f()");
        throw new OneException("n3 f()");
    }
    public static void main(String[] args) {
        try {
            f();
        } catch (OneException e) {
            System.out.println(
                    "Перехвачено во внутреннем блоке try, e.printStackTrace()");
            e.printStackTrace(System.out);
            throw new TwoException("n3 внутреннего блока try");
        } catch (TwoException e) {
            System.out.println(
                    "Перехвачено во внешнем блоке try, e.printStackTrace()");
            e.printStackTrace(System.out);
        }
    }
}*/

