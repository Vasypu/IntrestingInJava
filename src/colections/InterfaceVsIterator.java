package colections;

import java.util.*;

/**
 *  Collection
 *
 *  Collection — корневой интерфейс, описывающий общие аспекты всех последовательных контейнеров.
 *  Обе версии display() работают с объектами Map, а также подтипами Collection, причем как интерфейс
 *  Collection, так и Iterator отделяют методы display() от информации о конкретной реализации
 *  используемого контейнера.
 */

public class InterfaceVsIterator {
    public static void display(Iterator<Pet> it) {
        while (it.hasNext()) {
            Pet p = it.next();
            System.out.print(p.id() + ":" + p + " ");
        }
        System.out.println();
    }

    public static void display(Collection<Pet> pets) {
        for (Pet p : pets)
            System.out.print(p.id() + ":" + p + " ");
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

        List<Pet> petList = petCreator.arrayList(8);
        Set<Pet> petSet = new HashSet<>(petList);
        Map<String, Pet> petMap = new LinkedHashMap<>();
        String[] names = ("Ralph, Eric, Robin, Lacey, " +
                "Britney, Sam, Spot, Fluffy").split(", ");
        for (int i = 0; i < names.length; i++)
            petMap.put(names[i], petList.get(i));
        display(petList);
        display(petSet);
        display(petList.iterator());
        display(petSet.iterator());
        System.out.println(petMap);
        System.out.println(petMap.keySet());
        display(petMap.values());
        display(petMap.values().iterator());
    }
}

//Решение с Iterator выглядит привлекательно при написании класса,
// в котором реализация интерфейса Collection затруднена или непрактична.
class CollectionSequence extends AbstractCollection<Pet> {
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
    private Pet[] pets = petCreator.createArray(8);
    public int size() { return pets.length; }
    public Iterator<Pet> iterator() {
        return new Iterator<>() {
            private int index = 0;
            public boolean hasNext() {
                return index < pets.length;
            }
            public Pet next() { return pets[index++]; }
            public void remove() { // Не реализован
                throw new UnsupportedOperationException();
            }
        };
    }
    public static void main(String[] args){
        CollectionSequence c = new CollectionSequence();
        InterfaceVsIterator.display(c);
        InterfaceVsIterator.display(c.iterator());
    }
}

//Получение Iterator обеспечивает связывание последовательности с методом,
// работающим с этой последовательностью, при минимальном уровне логических
// привязок и накладывает гораздо меньше ограничений на класс последовательности,
// чем реализация Collection.
class PetSequence {
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
    protected Pet[] pets = petCreator.createArray(8);
}
class NonCollectionSequence extends PetSequence {
    public Iterator<Pet> iterator() {
        return new Iterator<>() {
            private int index = 0;

            public boolean hasNext() {
                return index < pets.length;
            }

            public Pet next() { return pets[index++]; }
            public void remove () { // Not implemented
                throw new UnsupportedOperationException();
            }
        };
    }
    public static void main(String[] args) {
        NonCollectionSequence nc = new NonCollectionSequence();
        InterfaceVsIterator.display(nc.iterator());
    }
}