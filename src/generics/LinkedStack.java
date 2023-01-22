package generics;

import java.util.ArrayList;
import java.util.Random;

/**
 *  Обобщенный класс стека
 *  <p>
 *  Для внутреннего класса доступны обобщенные параметры-типы внешнего класса.
 */
public class LinkedStack<T> {
    private static class Node<U> {
        U item;
        Node<U> next;
        Node() {
            item = null;
            next = null;
        }
        Node(U item, Node<U> next) {
            this.item = item;
            this.next = next;
        }
        boolean end() { return item == null && next == null; }
    }
    private Node<T> top = new Node<T>(); // Сторож
    public void push(T item) { top = new Node<T>(item, top); }
    public T pop() {
        T result = top.item;
        if(!top.end())
            top = top.next;
        return result;
    }
    public static void main(String[] args) {
        LinkedStack<String> lss = new LinkedStack<String>();
        for (String s : "Phasers on stun!".split(" "))
            lss.push(s);
        String s;
        while ((s = lss.pop()) != null)
            System.out.println(s);
    }
}

// Допустим, мы хотим создать особую разновидность списка, которая случайным образом выбирает
// один из своих элементов при каждом вызове select(). Чтобы реализация такого списка работала
// с любыми объектами, следует использовать обобщения.
class RandomList<T> {
    private ArrayList<T> storage = new ArrayList<T>();
    private Random rand = new Random(47);
    public void add(T item) { storage.add(item); }
    public T select() { return storage.get(rand.nextInt(storage.size())); }

    public static void main(String[] args) {
        RandomList<String> rs = new RandomList<String>();
        for (String s : ("The quick brown fox jumped over " +
                "the lazy brown dog").split(" "))
            rs.add(s);
        for (int i = 0; i < 11; i++)
            System.out.print(rs.select() + " ");
    }
}