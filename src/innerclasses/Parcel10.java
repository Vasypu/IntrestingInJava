package innerclasses;

/**
 *  Инициализация экземпляра для каждого объекта.
 *
 *  Перегружать такой инициализатор нельзя, поэтому он будет присутствовать в
 *  классе в единственном экземпляре.
 *  Анонимные классы несколько ограничены в наследовании, они могут либо расширять
 *  класс, либо реализовывать интерфейс, но не то и другое одновременно. Реализовывать
 *  можно только один интерфейс.
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