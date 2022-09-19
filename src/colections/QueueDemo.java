package colections;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 *  Queue
 *
 *  Очередь (queue) — это контейнер, работающий по принципу «первый вошел,
 *  первый вышел» (FIFO). Иначе говоря, объекты помещаются в один «конец»
 *  очереди, а извлекаются с другого «конца».
 *
 *  Методы peek() и element() возвращают элемент в начале очереди без его
 *  извлечения, но peek() возвращает null для пустой очереди, а element()
 *  выдает исключение NoSuchElementException. Методы poll() и remove()
 *  извлекают и возвращают элемент в начале очереди, но poll() возвращает
 *  null для пустой очереди, а remove() выдает NoSuchElementException.
 */

public class QueueDemo {
    public static void printQ(Queue queue) {
        while(queue.peek() != null)
            System.out.print(queue.remove() + " ");
        System.out.println();
    }
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        Random rand = new Random(47);
        for (int i = 0; i < 10; i++)
            queue.offer(rand.nextInt(i + 10));
        printQ(queue);
        Queue<Character> qc = new LinkedList<>();
        for(char c :"Brontosaurus".toCharArray())
            qc.offer(c);
        printQ(qc);
    }
}
