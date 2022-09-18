package colections;

import java.util.*;

/**
 *  Коллекции List
 *
 *  ArrayList оптимизирован для произвольного доступа к элементам, но с относительно медленными
 *  операциями вставки (удаления) элементов в середине списка.
 *
 *  LinkedList оптимизирован для последовательного доступа с быстрыми операциями вставки (удаления)
 *  в середине списка, но доступ к элементам производится относительно медленно.
 *
 *  ? Для метода Collections.sort() нужно реализовывать Comparable интерфейс
 */

public class ListFeatures {
    public static void main(String[] args) {
        Random rand = new Random(47);
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
        List<Pet> pets = petCreator.arrayList(7);
        System.out.println("1: " + pets);
        Hamster h = new Hamster();
        pets.add(h);                                            // Автоматическое изменение размера
        System.out.println("2: " + pets);
        System.out.println("3: " + pets.contains(h));
        pets.remove(h);                                         // Удаление объекта
        Pet p = pets.get(2);
        System.out.println("4: " + p + " " + pets.indexOf(p));
        Pet cymric = new Cymric();
        System.out.println("5: " + pets.indexOf(p));
        System.out.println("6: " + pets.remove(cymric));        // Не может удалить так как передан только что созданный
        System.out.println("7: " + pets.remove(p));             // Точно заданный объекты
        System.out.println("8: " + pets);
        pets.add(3, new Mouse());
        System.out.println("9: " + pets);
        List<Pet> sub = pets.subList(1,4);
        System.out.println("subList: " + sub);
        System.out.println("10: " + pets.containsAll(sub));
//        Collections.sort(sub);
        System.out.println("sorted subList: " + sub);
        System.out.println("11: " + pets.containsAll(sub));
        Collections.shuffle(sub, rand);                         // Случайная перестановка
        System.out.println("shuffled subList: " + sub);
        System.out.println("12: " + pets.containsAll(sub));
        List<Pet> copy = new ArrayList<>(pets);
        sub = Arrays.asList(pets.get(1), pets.get(4));
        System.out.println("sub: " + sub);
        copy.retainAll(sub);
        System.out.println("13: " + copy);
        copy = new ArrayList<>(pets);
        copy.remove(2);
        System.out.println("14: " + copy);
        copy.removeAll(sub);
        System.out.println("15: " + copy);
        copy.set(1, new Mouse());
        System.out.println("16: " + copy);
        copy.addAll(2, sub);
        System.out.println("17: " + copy);
        System.out.println("18: " + pets.isEmpty());
        pets.clear();
        System.out.println("19: " + pets);
        System.out.println("20: " + pets.isEmpty());
        pets.addAll(petCreator.arrayList(4));
        System.out.println("21: " + pets);
        Object[] o = pets.toArray();
        System.out.println("22: " + o[3]);
        Pet[] pa = pets.toArray(new Pet[0]);                // передает содержимое массива pets массиву, который передан в качестве аргумента метода (new Pet[0])
        System.out.println("23: " + pa[3]);
        System.out.println("24: " + Arrays.toString(pa));

        System.out.println("pets: " + pets);
        System.out.println("copy: " + copy);
        pets.retainAll(copy);                               // удаляет все объекты из коллекции если таких нет в переданной коллекции
        System.out.println(pets);
    }
}
