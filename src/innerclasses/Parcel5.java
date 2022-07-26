package innerclasses;

/**
 *  Внутренние классы в методах (локальные) и областях действия.
 *
 *  Если нам нужно использовать класс всего для одной реализации интерфейса, его
 *  можно создать внутри метода, такой класс называется локальным, так как будет
 *  использоваться только для одной задачи. Метод dest() будет возвращать ссылку
 *  на закрытую реализацию интерфейса Destination. Локальный класс можно создать
 *  только при реализации интерфейса или наследовании базового класса.
 */

public class Parcel5 {
    public Destination dest(String s) {
        class PDestination implements Destination {
            private String lable;
            private PDestination(String whereTo) {
                lable = whereTo;
            }
            public String readLable() { return lable; }
        }
        return new PDestination(s);
    }

    public static void main(String[] args) {
        Parcel5 p = new Parcel5();
        Destination d = p.dest("Тасмания");
        System.out.println(d.readLable());
    }
}

/**
 *  Пример с внутренним классом в произвольной области действия. Класс TrackingSlip
 *  не создается в зависимости от условия - он компилируется вместе с остальным кодом,
 *  но не доступен вне контекста.
 */
class Parcel6 {
    private void internalTracking(boolean b) {
        if (b) {
            class TrackingSlip {
                private String id;
                TrackingSlip(String s) {
                    id = s;
                }
                String getSlip() { return id; }
            }
            TrackingSlip ts = new TrackingSlip("ожидание");
            String s = ts.getSlip();
        }
//        нельзя использовать класс
//        вне области видимости
//        TrackingSlip ts = new TrackingSlip("x");
    }
    public void track() { internalTracking(true); }

    public static void main(String[] args) {
        Parcel6 p = new Parcel6();
        p.track();
    }
}
