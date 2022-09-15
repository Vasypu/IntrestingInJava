package colections;

import java.util.*;

/**
 *  Итераторы
 *
 *  Итераторы используются для работы с контейнером, не зная его тип.
 */

public class SimpleIteration {
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
        List<Pet> pets = petCreator.arrayList(12);
        Iterator<Pet> it = pets.iterator();
        while (it.hasNext()) {                              // проверяет есть ли в списке следующий элемент
            Pet p = it.next();                              // возвращает следующий элемент в списке
            System.out.printf(p.id + " " + p + " ");
        }
        System.out.println();

        for (Pet p : pets) {
            System.out.printf(p.id + " " + p + " ");
        }
        System.out.println();
        it = pets.iterator();
        for (int i = 0; i < 6; i++) {
            it.next();                                      // не обходимо вызывать next() перед удалением
            it.remove();
        }
        System.out.println(pets);
    }
}

// пример показывает, что итератерам ненужно знать о типе контейнера
class CrossContainerIteration {
    public static void display(Iterator<Pet> it) {
        while (it.hasNext()) {
            Pet p = it.next();
            System.out.print(p.id + ":" + p + " ");
        }
        System.out.println();
    }

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
        ArrayList<Pet> pets = petCreator.arrayList(8);
        LinkedList<Pet> petsLL = new LinkedList<>(pets);
        HashSet<Pet> petsHS = new HashSet<>(pets);
//        TreeSet<Pet> petsTS = new TreeSet<>(pets);
        display(pets.iterator());
        display(petsLL.iterator());
        display(petsHS.iterator());
//        display(petsTS.iterator());
    }
}
