package innerclasses;

/**
 *  Инициализация экземпляра для каждого объекта.
 */

public class Parcel10 {
    public Destination destination(final String dest, final float price) {
        return new Destination() {
            private int cost;
            {
                cost = Math.round(price);
                if (cost > 100)
                    System.out.println("Превышение бюджета!");
            }
            private String lable = dest;
            public String readLable() { return lable; }
        };
    }
    public static void main(String[] args) {
        Parcel10 p = new Parcel10();
        Destination d = p.destination("Тасмания", 101.395f);
    }
}
