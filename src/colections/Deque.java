package colections;

import java.util.LinkedList;

/**
 *  Деки
 *  <p>
 *  Дек (двусторонняя очередь) ведет себя как очередь, но с возможностью добавления и удаления
 *  элементов с обоих концов. В LinkedList имеются методы поддержки операций дека, но явно определенного
 *  интерфейса для деков в стандартных библиотеках Java не существует. Таким образом, LinkedList не может
 *  реализовать этот интерфейс, и вы не можете выполнить восходящее преобразование к интерфейсу Deque
 *  (в отличие от интерфейса Queue в предыдущем примере). Впрочем, класс Deque можно создать посредством
 *  композиции и просто предоставить доступ к соответствующим методам из LinkedList:
 */
public class Deque<T> {
    private LinkedList<T> deque = new LinkedList<T>();
    public void addFirst(T e) { deque.addFirst(e); }
    public void addLast(T e) { deque.addLast(e); }
    public T getFirst() { return deque.getFirst(); }
    public T getLast() { return deque.getLast(); }
    public T removeFirst() { return deque.removeFirst(); }
    public T removeLast() { return deque.removeLast(); }
    public int size() { return deque.size(); }
    public String toString() { return deque.toString(); }
}

class DequeTest {
    static void fillTest(Deque<Integer> deque) {
        for (int i = 20; i < 27; i++)
            deque.addFirst(i);
        for (int i = 50; i < 55; i++)
            deque.addLast(i);
    }
    public static void main(String[] args) {
        Deque<Integer> di = new Deque<Integer>();
        fillTest(di);
        System.out.println(di);
        while(di.size() != 0)
            System.out.print(di.removeFirst() + " ");
        System.out.println();
        fillTest(di);
        while(di.size() != 0)
            System.out.print(di.removeLast() + " ");
    }
}
