package colections;

import java.util.ArrayList;
import java.util.List;

/**
 *  ListIterator
 *
 *  Более мощная разновидность Iterator. ListIterator является двухсторонним.
 *  ListIterator может выдавать индексы следующего и предыдущего элементов по
 *  отношению к текущей позиции итератора и заменять последний посещенный элемент
 *  методом set().
 */

public class ListIterator {
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
        List<Pet> pets = petCreator.arrayList(8);
        java.util.ListIterator<Pet> it = pets.listIterator();
        while (it.hasNext())
            System.out.print(it.next() + ". " + it.nextIndex() +    // выводит следующий индекс
                    " " + it.previousIndex() + ": ");               // выводит прошлый индекс
        System.out.println();
        // В обратном направлении
        while (it.hasPrevious())
            System.out.print(it.previous() + " ");

        System.out.println();
        System.out.println(pets);
        it = pets.listIterator(3);
        while (it.hasNext()) {
                it.next();
                it.set(petCreator.randomPet());                     // заменять элемент рандомным животным
        }
        System.out.println(pets);
    }
}
