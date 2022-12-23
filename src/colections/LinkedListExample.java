package colections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *  LinkedList
 *  <p>
 *  Класс LinkedList, как и ArrayList, реализует базовый интерфейс List, но при этом выполняет
 *  некоторые операции (вставка и удаление в середине списка) более эффективно, чем ArrayList.
 *  И наоборот, с операциями произвольного доступа он работает менее эффективно.
 *  <p>
 *  Метод offer() делает то же, что add() и addLast(). Все эти методы добавляют элемент в конец списка.
 *  Методы getFirst() и element() идентичны — они возвращают начало (первый элемент) списка без его
 *  удаления и выдают исключение NoSuchElementException, если список пуст.
 *  Метод peek() — вариация на тему этих двух методов, которая возвращает null для пустого списка
 */

public class LinkedListExample {
    public static void main(String[] args) {
        PetCreator petCreator = new PetCreator() {
            public List<Class<? extends Pet>> types() {
                List<Class<? extends Pet>> somePets = new ArrayList<>();
                somePets.add(Mutt.class);
                somePets.add(Manx.class);
                somePets.add(Cat.class);
                somePets.add(Dog.class);
                somePets.add(Rodent.class);
                return somePets;
            }
        };

        LinkedList<Pet> pets = new LinkedList<Pet>(petCreator.arrayList(5));
        System.out.println(pets);

        System.out.println("pets.getFirst() " + pets.getFirst());
        System.out.println("pets.element() " + pets.element());
        // Отличается только поведение для пустого списка:
        System.out.println("pets.peek(): " + pets.peek());
        // Идентично; удаление и возвращение первого элемента:
        System.out.println("pets.remove(): " + pets.remove());
        System.out.println("pets.removeFirst(): " + pets.removeFirst());
        // Отличается только поведение для пустого списка:
        System.out.println("pets.poll(): " + pets.poll());
        System.out.println(pets);
        pets.addFirst(new Rat());
        System.out.println("После addFirst(): " + pets);
        pets.offer(petCreator.randomPet());
        System.out.println("После offer(): " + pets);
        pets.add(petCreator.randomPet());
        System.out.println("После add(): " + pets);
        pets.addLast(new Hamster());
        System.out.println("После addLast(): " + pets);
        System.out.println("pets.removeLast(): " + pets.removeLast());
    }
}
