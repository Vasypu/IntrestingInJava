package colections;

import java.util.LinkedList;

/**
 *  Stack
 *
 *  Стек часто называют контейнером, построенным на принципе «первый вошел,
 *  последний вышел» (LIFO). То есть что бы вы ни поместили (push) в стек
 *  в последнюю очередь, это будет первым, что вы получите при «выталкивании»
 *  (pop) элемента из стека.
 */

public class Stack<T> {
    private LinkedList<T> storage = new LinkedList<T>();
    public void push(T v) { storage.addFirst(v); }
    public T peek() { return storage.getFirst(); }
    public T pop() { return storage.removeFirst(); }
    public boolean empty() { return storage.isEmpty(); }
    public String toString() { return storage.toString(); }
}

class StackTest {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        for(String s : "Му dog has fleas".split(" "))
            stack.push(s);
        while(!stack.empty())
            System.out.print(stack.pop() + " ");
    }
}
