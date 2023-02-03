package RTTI;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  Метод Class.isInstance() предоставляет возможность динамической проверки типа объекта. С помощью этого
 *  метода в примере PetCount наконец-то можно будет избавиться от громоздких конструкций instanceof.
 *  <p>
 *  Для подсчета всех разновидностей Pet карта PetCounter заполняется типами из Literal- PetCreator.allTypes.
 *  При этом используется класс MapData, который получает Iterable (контейнер allTypes) и константу
 *  (нуль в данном случае) и заполняет карту ключами из allTypes, ассоциированными со значением 0.
 */

public class PetCount3 {
    static class PetCounter extends LinkedHashMap<Class<? extends Pet>,Integer> {
        public PetCounter() {
            super(MapData.map(LiteralPetCreator.allTypes, 0));      // создает мапу со всеми ссылками на классы животных
        }
        public void count(Pet pet) {
            // Class.isInstance() избавляет от цепочки instanceof:
            for(Map.Entry<Class<? extends Pet>,Integer> pair : entrySet())  // проходит по всем ссылкам на классы животных и определяет
                if (pair.getKey().isInstance(pet))                          // является ли переданный объект животным
                    put(pair.getKey(), pair.getValue() + 1);
        }
        public String toString() {
            StringBuilder result = new StringBuilder("{");
            for (Map.Entry<Class<? extends Pet>, Integer> pair : entrySet()) {
                result.append(pair.getKey().getSimpleName());
                result.append("=");
                result.append(pair.getValue());
                result.append(", ");
            }
            result.delete(result.length() - 2, result.length());
            result.append("}");
            return result.toString();
        }
    }
    public static void main(String[] args) {
        PetCounter petCount = new PetCounter();
        for(Pet pet : Pets.createArray(20)) {
            System.out.print(pet.getClass().getSimpleName() + " ");
            petCount.count(pet);
        }
        System.out.println();
        System.out.print(petCount);
    }
}
