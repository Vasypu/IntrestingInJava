package containers;

import java.util.LinkedList;
import java.util.Stack;

enum Month { JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE,
        JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER }

/**
 *  Stack
 *  <p>
 *  Посредством композиции класс Stack был унаследован от класса Vector.
 *  <p>
 *  Чтобы специально подчеркнуть эту возможность, показано, как можно вызвать методы класса vector для объекта Stack.
 *  Это делается очень просто, так как, по определению наследования, класс Stack «является частным случаем» класса Vector.
 *  Таким образом, все операции, описанные в классе vector, применимы и для класса Stack: например, можно получить произвольный
 *  элемент стека методом elementAt().
 */
public class Stacks {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        for(Month m : Month.values())
            stack.push(m.toString());
        System.out.println("stack = " + stack);
        // Работа со стеком как c Vector:
        stack.addElement("The last line");
        System.out.println("element 5 = " + stack.elementAt(5));
        System.out.println("popping elements:");
        while(!stack.empty())
            System.out.print(stack.pop() + " ");

        // Использование LinkedList как стека:
        LinkedList<String> lstack = new LinkedList<String>();
        for(Month m : Month.values())
            lstack.addFirst(m.toString());
        System.out.println("lstack = " + lstack);
        while(!lstack.isEmpty())
            System.out.print(lstack.removeFirst() + " ");

        // Использование класса Stack из главы 11:
        containers.Stack<String> stack2 = new containers.Stack<String>();
        for(Month m : Month.values())
            stack2.push(m.toString());
        System.out.println("stack2 = " + stack2);
        while(!stack2.empty())
            System.out.print(stack2.pop() + " ");
    }
}
