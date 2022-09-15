package colections;

import java.util.*;

/**
 *  Добавление групп элементов.
 */

public class AddingGroups {
    public static void main(String[] args) {
        Collection<Integer> collection = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        Integer[] moreInts = {6,7,8,9,10};
        collection.addAll(Arrays.asList(moreInts));
        Collections.addAll(collection, 11,12,13,14,15);  // Работает намного быстрее, но таким способом невозможно сконструировать Collection
        Collections.addAll(collection, moreInts);
        List<Integer> list = Arrays.asList(16,17,18,19,20);
        list.set(1,99);                                          // Изменение возможно
//        list.add(21);                                          // Ошибка времени выполнения, нельзя изменить массив в размере
        System.out.println(collection);
        System.out.println(list);
    }
}

class Snow {}
class Powder extends Snow {}
class Light extends Powder {}
class Heavy extends Powder {}
class Crusty extends Snow {}
class Slush extends Snow {}

class AsListInference {
    public static void main(String[] args) {
        List<Snow> snow1 = Arrays.asList(new Crusty(), new Slush(), new Powder());

        List<Snow> snow2 = Arrays.asList(new Light(), new Heavy());              // метод Arrays.asList() создает List<Powder>, но
        List<Snow> snow3 = new ArrayList<>();                                    // происходит восходящее преобразование и код работает корректно
        Collections.addAll(snow3, new Light(), new Heavy());

        List<Snow> snow4 = Arrays.<Snow>asList(new Light(), new Heavy());        // так же можно явно указать тип для метода Arrays.asList()
    }
}