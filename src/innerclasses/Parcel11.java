package innerclasses;

/**
 *  Вложенные классы.
 *
 *  Вложенный класс это внутренний класс с ключевым словом static.
 *  Вложенный класс отличается внутреннего тремя вещами:
 *  - нельзя обратиться к полям внешнего класса из вложенного класса
 *  - для создания объекта статического (вложенного) класса не нужен объект
 *  - вложенный класс может содержать статические элементы
 */

public class Parcel11 {

    int d = 10;
    private static class PContents implements Contents {
        private int i = 11;
//        private int s = d + 2;                              // нельзя обратиться к полям внешнего класса из вложенного класса
        public int value() { return i; }
    }

    protected static class ParcelDestination implements Destination {
        private String lable;
        private ParcelDestination(String whereTo) {
            lable = whereTo;
        }
        public String readLable() { return lable; }
                                                            // Вложенный класс может содержать статические элементы
        public static void f() {}
        static int x = 10;
        static class AnotherLevel {
            public static void f() {}
            static int x = 10;
        }
    }
    public static Destination destination(String s) {
        return new ParcelDestination(s);
    }
    public static Contents cont() {
        return new PContents();
    }
    public static void main(String[] args) {
        Contents c = cont();
        PContents contents = new PContents();                 // для создания объекта статического (вложенного) класса не нужен объект
        Destination d = destination("Тасмания");
    }
}