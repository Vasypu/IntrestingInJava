package RTTI;

import java.util.HashMap;
import java.util.Map;

/**
 *  Рекурсивный подсчет
 *  <p>
 *  Вместо предварительного заполнения также можно воспользоваться методом Class.isAssignableFrom() и
 *  создать инструмент общего назначения, который не ограничивается подсчетом Pet.
 *  <p>
 *  countClass() сначала подсчитывает экземпляры реального типа класса. Затем, если baseType допускает
 *  присваивание из суперкласса, то countclass() вызывается рекурсивно для суперкласса.
 *  <p>
 *  isAssignableFrom() проверяет во время выполнения, что переданный объект действительно принадлежит
 *  нужной иерархии
 */

public class TypeCounter extends HashMap<Class<?>,Integer> {
    private Class<?> baseType;
    public TypeCounter(Class<?> baseType) {
        this.baseType = baseType;
    }
    public void count(Object obj) {
        Class<?> type = obj.getClass();
        if (!baseType.isAssignableFrom(type))
            throw new RuntimeException(obj + " неправильный тип: "
                    + type + ", должен быть подтипом "
                    + baseType);
        countClass(type);
    }
    private void countClass(Class<?> type) {
        Integer quantity = get(type);
        put(type, quantity == null ? 1 : quantity + 1);
        Class<?> superClass = type.getSuperclass();
        if (superClass != null && baseType.isAssignableFrom(superClass)) // проверка того что у класса может быть еще один родитель
            countClass(superClass);                                      // и тогда рекурсивно вызывается метод подсчета
    }
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        for(Map.Entry<Class<?>, Integer> pair : entrySet()) {
            result.append(pair.getKey().getSimpleName());
            result.append("=");
            result.append(pair.getValue());
            result.append(", ");
        }
        result.delete(result.length()-2, result.length());
        result.append("}");
        return result.toString();
    }
}

class PetCount4 {
    public static void main(String[] args) {
        TypeCounter counter = new TypeCounter(Pet.class);
        for(Pet pet : Pets.createArray(20)) {
            System.out.print(pet.getClass().getSimpleName() + " ");
            counter.count(pet);
        }
        System.out.println();
        System.out.print(counter);
    }
}
