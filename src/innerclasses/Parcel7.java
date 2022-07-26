package innerclasses;

/**
 *  Анонимные классы.
 *
 *  В примере Parcel7 в методе contents() создается анонимный класс. На деле
 *  в методе реализуется интерфейс Contents. Аналогичный пример без использования
 *  анонимного класса содержится в Parcel7b. Анонимный класс создается с той же целью,
 *  что и локальный, для закрытой от внешних классов реализации интерфейсов.
 */

public class Parcel7 {
    public Contents contents() {
        return new Contents() {
            private int i = 11;
            public int value() { return i; }
        };
    }
    public static void main(String[] args) {
        Parcel7 p = new Parcel7();
        Contents c = p.contents();
        System.out.println(c.value());
    }
}

class Parcel7b {
    class MyContents implements Contents {
        private int i = 11;
        public int value() { return i; }
    }
    public Contents contents() { return new MyContents(); }
    public static void main(String[] args) {
        Parcel7 p = new Parcel7();
        Contents c = p.contents();
        System.out.println(c.value());
    }
}

/**
 *  Анонимный класс с передачей аргумента конструктору.
 */
class Parcel8 {
    public Wrapping wrapping(int x) {
        return new Wrapping(x) {
            public int value() { return super.value() * 47; }
        };
    }
    public static void main(String[] args) {
        Parcel8 p = new Parcel8();
        Wrapping w = p.wrapping(10);
    }

    private class Wrapping {
        private int i;
        public Wrapping(int x) { i = x; }
        public int value() { return i; }
    }
}

/**
 *  Инициализация в точке определения полей анонимного класса. Если требуется использовать аргументы
 *  объявленные вне этого внутреннего класса, необходимо чтобы переданные на них ссылки объявлялись
 *  неизменными (final).
 */

class Parcel9 {
    public Destination destination(final String dest) {
        return new Destination() {
            private String lable = dest;
            public String readLable() { return lable; }
        };
    }
    public static void main(String[] args) {
        Parcel9 p = new Parcel9();
        Destination d = p.destination("Тасмания");
    }
}