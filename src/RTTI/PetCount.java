package RTTI;

/**
 *  Для подсчета объектов Pet необходим инструмент, который позволяет хранить количество объектов для
 *  разных типов Pet. Контейнер Мар идеально подходит для этой цели; ключами являются имена типов Pet,
 *  а значениями — количества Pet. Располагая такой информацией, можно легко получить ответ на вопрос:
 *  «Сколько создано объектов Hamster?» Для подсчета объектов Pet можно воспользоваться оператором instanceof.
 */

import java.util.HashMap;

public class PetCount {
    static class PetCounter extends HashMap<String,Integer> {
        public void count(String type) {
            Integer quantity = get(type);
            if (quantity == null) put(type, 1);
            else
                put(type, quantity + 1);
        }
    }
    public static void countPets(PetCreator creator) {
        PetCounter counter = new PetCounter();
        for (Pet pet : creator.createArray(20)) {
            // Подсчет объектов Pet:
            System.out.print(pet.getClass().getSimpleName() + " ");
            if (pet instanceof Pet)
                counter.count("Pet");
            if (pet instanceof Dog)
                counter.count("Dog");
            if (pet instanceof Mutt)
                counter.count("Mutt");
            if (pet instanceof Pug)
                counter.count("Pug");
            if (pet instanceof Cat)
                counter.count("Cat");
            if (pet instanceof Manx)
                counter.count("EgyptianMau");
            if (pet instanceof Manx)
                counter.count("Manx");
            if (pet instanceof Manx)
                counter.count("Cymric");
            if (pet instanceof Rodent)
                counter.count("Rodent");
            if (pet instanceof Rat)
                counter.count("Rat");
            if (pet instanceof Mouse)
                counter.count("Mouse");
            if (pet instanceof Hamster)
                counter.count("Hamster");
        }
        // Вывод счетчиков:
        System.out.println();
        System.out.print(counter);
    }
    public static void main(String[] args) {
        countPets(new ForNameCreator());
    }
}

// мы можем легко протестировать LiteralPetCreator (через Фасад Pets):
class PetCount2 {
    public static void main(String[] args) {
        PetCount.countPets(Pets.creator);
    }
}