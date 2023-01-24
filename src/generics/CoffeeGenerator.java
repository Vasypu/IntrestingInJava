package generics;

import java.util.Iterator;
import java.util.Random;

/**
 * Обобщенные интерфейсы
 */
interface Generator<T> { T next(); }

class Coffee {
    private static long counter = 0;
    private final long id = counter++;
    public String toString() { return getClass().getSimpleName() + " " + id; }
}

class Latte extends Coffee {}
class Mocha extends Coffee {}
class Cappuccino extends Coffee {}
class Americano extends Coffee {}
class Breve extends Coffee {}

public class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {
    private Class[] types = { Latte.class, Mocha.class,
            Cappuccino.class, Americano.class, Breve.class, };
    private static Random rand = new Random(47);
    public CoffeeGenerator() {}
    // Для перебора:
    private int size = 0;
    public CoffeeGenerator(int sz) { size = sz; }
    public Coffee next() {
        try {
            return (Coffee)
                    types[rand.nextInt(types.length)].newInstance();
            // Сообщить программисту об ошибках во время выполнения:
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    class CoffeeIterator implements Iterator<Coffee> {  // CoffeeGenerator также реализует интерфейс Iterable,
        int count = size;                               // что позволяет использовать его в конструкции foreach
        public boolean hasNext() { return count > 0; }
        public Coffee next() {
            count--;
            return CoffeeGenerator.this.next();
        }
        public void remove() { // Не реализован
            throw new UnsupportedOperationException();
        }
    };
    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }
    public static void main(String[] args) {
        CoffeeGenerator gen = new CoffeeGenerator();
        for(int i = 0; i < 5; i++)
            System.out.println(gen.next());
        for(Coffee c : new CoffeeGenerator(5))
            System.out.println(c);
    }
}

// Вторая реализация Generator<T> — на этот раз предназначенная для
// генерирования чисел Фибоначчи:
class Fibonacci implements Generator<Integer> {
    private int count = 0;
    public Integer next() { return fib(count++); }
    private int fib(int n) {
        if(n < 2) return 1;
        return fib(n - 2) + fib(n - 1);
    }
    public static void main(String[] args) {
        Fibonacci gen = new Fibonacci();
        for(int i = 0; i < 18; i++)
            System.out.print(gen.next() + " ");
    }
}

// Создадим адаптер, предоставляющий нужный интерфейс. Существуют разные способы реализации
// адаптеров. Например, для создания адаптируемого класса можно применить наследование.
// Чтобы использовать IterableFibonacci в конструкции foreach, мы передаем конструктору
// границу, чтобы метод hasNext() знал, когда возвращать false.
class IterableFibonacci extends Fibonacci implements Iterable<Integer> {
    private int n;
    public IterableFibonacci(int count) { n = count; }
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            public boolean hasNext() { return n > 0; }
            public Integer next() {
                n --;
                return IterableFibonacci.this.next();
            }
            public void remove() { // Не реализован
                throw new UnsupportedOperationException();
            }
        };
    }
    public static void main(String[] args){
        for (int i : new IterableFibonacci(18))
            System.out.print(i + " ");
    }
}