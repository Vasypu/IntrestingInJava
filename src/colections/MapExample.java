package colections;

import java.util.*;

/**
 *  Map
 *  <p>
 *  Структура данных, которая содержит набор пар “ключ-значение”.
 *  По своей структуре данных напоминает словарь, поэтому ее часто
 *  так и называют.
 *  <p>
 *  Контейнеры Мар, как и массивы и Collection, легко расширяются до нескольких измерений.
 *  <p>
 *  Методы containsKey() и containsValue() помогают проверить
 *  присутствие ключа или значения.
 *  Метод keySet() возвращает контейнер Set, содержащий все ключи из petPeople.
 *  Метод values() возвращает все значения из petPeople.
 */

public class MapExample {
    public static void main(String[] args) {
        Random rand = new Random(47);
        Map<Integer,Integer> m = new HashMap<>();
        for(int i = 0; i < 10000; i++) {
            // Produce a number between 0 and 20:
            int r = rand.nextInt(20);
            Integer freq = m.get(r);
            m.put(r, freq == null ? 1 : freq + 1);
        }
        System.out.println(m);
    }
}

class PetMap {
    public static void main(String[] args) {
        Map<String,Pet> petMap = new HashMap<>();
        petMap.put("My Cat", new Cat("Molly"));
        petMap.put("My Dog", new Dog("Ginger"));
        petMap.put("My Hamster", new Hamster("Bosco"));
        System.out.println(petMap);
        Pet dog = petMap.get("My Dog");
        System.out.println(dog);
        System.out.println(petMap.containsKey("My Dog"));
        System.out.println(petMap.containsValue(dog));
    }
}

class MapOfList {
    public static Map<Person, List<? extends Pet>> petPeople = new HashMap<>();
    static {
        petPeople.put(new Person("Dawn"), Arrays.asList(new Cymric("Molly"), new Mutt("Spot")));
        petPeople.put(new Person("Kate"), Arrays.asList(new Cat("Shackleton"), new Cat("Elsie May"),
                        new Dog("Margrett")));
        petPeople.put(new Person("Marilyn"), Arrays.asList(new Pug("Louie aka Louis Snorkelstein Dupree"),
                new Cat("Stanford aka Stinky el Negro"),
                new Cat("Pinkola")));
        petPeople.put(new Person("Luke"), Arrays.asList(new Rat("Fuzzy"), new Rat("Fizzy")));
        petPeople.put(new Person("Isaac"), Arrays.asList(new Rat("Freckly")));
    }
    public static void main(String[] args) { System.out.println("People: " + petPeople.keySet());
        System.out.println("Pets: " + petPeople.values());
        for(Person person : petPeople.keySet()) {
            System.out.println(person + " has:");
            for(Pet pet : petPeople.get(person))
                System.out.println(" " + pet);
        }
    }
}

class Person {
    private String name;
    Person(String name) {
        this.name = name;
    }

    public String toString() {
        return "Person " + name;
    }
}
