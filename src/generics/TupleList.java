package generics;

import java.util.ArrayList;
import java.util.Random;

/**
 *  Построение сложных моделей
 *  <p>
 *  Одним из важных преимуществ обобщений является возможность простого и безопасного создания
 *  сложных моделей. Например, можно легко создать контейнер List для кортежей.
 */
public class TupleList<A,B,C,D> extends ArrayList<FourTuple<A,B,C,D>> {

    public static void main(String[] args) {
        TupleList<Vehicle, Amphibian, String, Integer> tl = new TupleList<>();
        tl.add(TupleTest.h());
        tl.add(TupleTest.h());
        for (FourTuple<Vehicle, Amphibian, String, Integer> i : tl)
            System.out.println(i);
    }
}

// Следующий пример также показывает, как легко строятся сложные модели с применением обобщенных типов.
// Несмотря на то, что каждый отдельный класс создается как «строительный блок», общая модель состоит
// из множества частей.
class Product {
    private final int id;
    private String description;
    private double price;
    public Product(int IDnumber, String descr, double price) {
        id = IDnumber;
        description = descr;
        this.price = price;
        System.out.println(toString());
    }
    public String toString() {
        return id + ": " + description + ", price: $" + price;
    }
    public void priceChange(double change) { price += change; }
    public static Generator<Product> generator = new Generator<Product>() {
        private Random rand = new Random(47);
        public Product next() {
            return new Product(rand.nextInt(1000), "Test",
                    Math.round(rand.nextDouble() * 1000.0) + 0.99);
        }
    };
}

class Shelf extends ArrayList<Product> {
    public Shelf(int nProducts) { Generators.fill(this, Product.generator, nProducts); }
}

class Aisle extends ArrayList<Shelf> {
    public Aisle(int nShelves, int nProducts) {
        for (int i = 0; i < nShelves; i++)
            add(new Shelf(nProducts));
    }
}

class CheckoutStand {}
class Office {}

class Store extends ArrayList<Aisle> {
    private ArrayList<CheckoutStand> checkouts = new ArrayList<CheckoutStand>();
    private Office office = new Office();
    public Store(int nAisles, int nShelves, int nProducts) {
        for (int i = 0; i < nAisles; i++)
            add(new Aisle(nShelves, nProducts));
    }
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Aisle a : this)
            for(Shelf s : a)
                for(Product p : s) {
                    result.append(p);
                    result.append("\n");
                }
        return result.toString();
    }
    public static void main(String[] args){
        System.out.println(new Store(14, 5, 10));
    }
}