package colections;

import java.util.*;

/**
 *  PriorityQueue
 *
 *  В приоритетной очереди PriorityQueue следующим извлекается элемент, обладающий
 *  наивысшим приоритетом. При вызове offer() для объекта, помещаемого в PriorityQueue,
 *  позиция этого объекта в очереди определяется сортировкой. Сортировка по умолчанию
 *  использует естественный порядок следования объектов в очереди, но вы можете изменить
 *  его, предоставив собственную реализацию Comparator. PriorityQueue гарантирует, что
 *  при вызове peek(), poll() или remove() будет получен элемент с наивысшим приоритетом.
 */

public class PriorityQueueDemo {
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        Random rand = new Random(47);
        for (int i = 0; i < 10; i++)
            priorityQueue.offer(rand.nextInt(i + 10));
        QueueDemo.printQ(priorityQueue);

        List<Integer> ints = Arrays.asList(25, 22, 20, 18, 14, 9, 3, 1, 1, 2, 3, 9, 14, 18, 21, 23, 25);
        priorityQueue = new PriorityQueue<>(ints);
        QueueDemo.printQ(priorityQueue);
        priorityQueue = new PriorityQueue<>(ints.size(), Collections.reverseOrder());
        priorityQueue.addAll(ints);
        QueueDemo.printQ(priorityQueue);

        //Как видите, дубликаты допустимы, а наименьшие значения обладают наивысшим приоритетом
        // (в случае String пробелы также считаются значениями, которые обладают более высоким
        // приоритетом, чем буквы).
        String fact = "EDUCATION SHOULD ESCHEW OBFUSCATION";
        List<String> strings = Arrays.asList(fact.split(""));
        PriorityQueue<String> stringPQ = new PriorityQueue<>(strings);
        QueueDemo.printQ(stringPQ);
        stringPQ = new PriorityQueue<>(strings.size(), Collections.reverseOrder());
        stringPQ.addAll(strings);
        QueueDemo.printQ(stringPQ);

        Set<Character> charSet = new HashSet<>();
        for (char c : fact.toCharArray())
            charSet.add(c); // Autoboxing
        PriorityQueue<Character> characterPQ = new PriorityQueue<>(charSet);
        QueueDemo.printQ(characterPQ);
    }
}
