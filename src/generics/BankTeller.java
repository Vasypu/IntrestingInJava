package generics;

import java.util.*;

/**
 *  Анонимные внутренние классы
 *  <p>
 *  Обобщения также могут использоваться с внутренними классами и анонимными внутренними классами
 *  <p>
 *  Customer, и Teller имеют закрытые конструкторы, поэтому для создания их экземпляров приходится
 *  использовать объекты Generator. Класс Customer содержит метод generator(), который создает новый
 *  объект Generator<Customer> при каждом вызове.
 */
class Customer {
    private static long counter = 1;
    private final long id = counter++;
    private Customer() {}
    public String toString() { return "Customer " + id; }
    // Метод для получения объектов Generator:
    public static Generator<Customer> generator() {
        return new Generator<Customer>() {
            public Customer next() { return new Customer(); }
        };
    }
}

class Teller {
    private static long counter = 1;
    private final long id = counter++;
    private Teller() {}
    public String toString() { return "Teller " + id; }
    // Объект Generator:
    public static Generator<Teller> generator = new Generator<Teller>() {
        public Teller next() { return new Teller(); }
    };
}

public class BankTeller {
    public static void serve(Teller t, Customer c) {
        System.out.println(t + " serves " + c);
    }
    public static void main(String[] args) {
        Random rand = new Random(47);
        Queue<Customer> line = new LinkedList<Customer>();
        Generators.fill(line, Customer.generator(), 15);
        List<Teller> tellers = new ArrayList<Teller>();
        Generators.fill(tellers, Teller.generator, 4);
        for(Customer c : line)
            serve(tellers.get(rand.nextInt(tellers.size())), c);
    }
}
