package colections;

import containers.Generator;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 *  Очереди и Приоритетные очереди
 */

// За исключением приоритетных очередей Queue производит элементы в порядке их помещения в Queue.
public class QueueBehavior {
    private static int count = 10;
    static <T> void test(Queue<T> queue, Generator<T> gen) {
        for(int i = 0; i < count; i++)
            queue.offer(gen.next());
        while(queue.peek() != null)
            System.out.print(queue.remove() + " ");
        System.out.println();
    }
    static class Gen implements Generator<String> {
        String[] s = ("one two three four five six seven eight nine ten").split(" ");
        int i;
        public String next() { return s[i++]; }
    }

    public static void main(String[] args) {
        test(new LinkedList<String>(), new Gen());
        test(new PriorityQueue<String>(), new Gen());
        test(new ArrayBlockingQueue<String>(count), new Gen());
        test(new ConcurrentLinkedQueue<String>(), new Gen());
        test(new LinkedBlockingQueue<String>(), new Gen());
        test(new PriorityBlockingQueue<String>(), new Gen());
    }
}

// Рассмотрим более интересную задачу: список дел, в котором каждый объект содержит строку и два
// приоритета, первичный и вторичный.
class ToDoList extends PriorityQueue<ToDoList.ToDoItem> {
    static class ToDoItem implements Comparable<ToDoItem> {
        private char primary;
        private int secondary;
        private String item;
        public ToDoItem(String td, char pri, int sec) {
            primary = pri;
            secondary = sec;
            item = td;
        }
        public int compareTo(ToDoItem arg) {
            if (primary > arg.primary) return + 1;
            if (primary == arg.primary)
                if (secondary > arg.secondary) return + 1;
                else if (secondary == arg.secondary) return 0;
            return -1;
        }
        public String toString () { return Character.toString(primary) + secondary + ": " + item; }
    }
    public void add(String td, char pri, int sec) { super.add(new ToDoItem(td, pri, sec)); }

    public static void main(String[]args){
        ToDoList toDoList = new ToDoList();
        toDoList.add("Empty trash", 'C', 4);
        toDoList.add("Feed dog", 'A', 2);
        toDoList.add("Feed bird", 'B', 7);
        toDoList.add("Mow lawn", 'C', 3);
        toDoList.add("Water lawn", 'A', 1);
        toDoList.add("Feed cat",'B', 1);
        toDoList.add("Some",'B', 2);
        while (!toDoList.isEmpty())
            System.out.println(toDoList.remove());
    }
}