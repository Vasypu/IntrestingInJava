package exceptions;

/**
 *  Ограничения исключений
 *
 *  Когда класс StormyInning расширяется от Inning и реализует интерфейс Storm,
 *  выясняется, что метод event() из Storm не способен изменить тип исключения
 *  для метода event() класса inning.
 *
 *  Ограничения для исключений не распространяются на конструкторы. Вы можете заметить,
 *  что в классе StormyInning конструктор волен возбудить любое исключение по своему вкусу,
 *  не обращая внимания на то, какие исключения вырабатывает конструктор базового класса.
 *  Однако конструктор базового класса так или иначе вызывается (в нашем случае автоматически
 *  вызывается конструктор по умолчанию), и поэтому конструктор унаследованного класса
 *  должен объявить все исключения базового конструктора в своей спецификации исключений.
 *  Конструктор унаследованного класса не может перехватывать исключения, возбуждаемые
 *  конструктором базового класса.
 *
 *  Переопределенный метод event() показывает, что в методах унаследованных классов можно
 *  вообще не возбуждать исключений, даже если это делается в базовой версии. Опять-таки,
 *  это верно, так как не влияет на уже напцсанный код — подразумевается, что метод
 *  базового класса возбуждает исключения.
 */

class BaseballException extends Exception {}
class Foul extends BaseballException {}
class Strike extends BaseballException {}

abstract class Inning {
    public Inning() throws BaseballException {}
    public void event() throws BaseballException { // Фактически возбуждать исключение не нужно
    }
    public abstract void atBat() throws Strike, Foul;
    public void walk() {} // Не возбуждает контролируемых исключений
}
class StormException extends Exception {}
class RainedOut extends StormException {}
class PopFoul extends Foul {}
interface Storm {
    public void event() throws RainedOut;
    public void rainHard() throws RainedOut;
}

public class StormyInning extends Inning implements Storm {
// Можно добавлять новые исключения для
// конструкторов, но вы должны обработать
// и исключения базового конструктора:
public StormyInning() throws RainedOut, BaseballException {}
    public StormyInning(String s) throws Foul, BaseballException {}
    // Обычные методы должны соответствовать правилам базового класса:
//! public void walk() throws PopFoul {} //Ошибка компиляции
// Интерфейс НЕ МОЖЕТ добавлять исключения
// к существующим методам базового класса:
//! public void event() throws RainedOut {}
// Если метод не был определен в базовом
// классе, исключение допускается:
    public void rainHard() throws RainedOut {}
// Вы можете не возбуждать исключений вообще,
// даже если базовая версия это делает:
public void event() {}
// Переопределенные методы могут возбуждать
// унаследованные исключения:
public void atBat() throws PopFoul {}

class UmpireException extends Exception {}
public void test() throws UmpireException {
    throw new UmpireException();
}

    public static void main(String[] args) {
        try {
            StormyInning si = new StormyInning();
            si.atBat();
            si.test();
        } catch(PopFoul e) {
            System.out.println("Pop foul");
        } catch(RainedOut e) {
            System.out.println("Rained out");
        } catch(BaseballException e) {
            System.out.println("Generic baseball exception");
        } catch (UmpireException e) {
            throw new RuntimeException(e);
        }
// Strike не возбуждается в унаследованной версии
        try {
// Что произойдет при восходящем преобразовании?
            Inning i = new StormyInning();
            i.atBat();
// Вы должны перехватывать исключения из базовой версии метода:
        } catch(Strike e) {
            System.out.println("Strike");
        } catch(Foul e) {
            System.out.println("Foul");
        } catch(RainedOut e) {
            System.out.println("Rained out");
        } catch(BaseballException e) {
            System.out.println("06mee исключение");
        }
    }
}
