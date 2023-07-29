package colections;

import containers.CountingMapData;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  Производительность Map
 *  <p>
 *  Использование линейного поиска в get() при поиске ключа — достаточно медленный процесс.
 *  Карта HashMap значительно увеличивает скорость такого поиска. Вместо неторопливого поиска
 *  ключа она работает со специальным значением, называемым хеш-кодом (hash code).
 *  Хеш-код — это способ преобразования некоторой информации об объекте в «относительно
 *  уникальное» целое число, связанное с этим объектом. Метод hashCode() встроен в корневой
 *  класс Object, поэтому хеш-код может генерироваться для любых объектов в java. Карта HashMap
 *  задействует метод hashCode() для быстрого поиска ключа.
 *  <p>
 *  Заметьте, что ключи карты должны быть уникальны, в то время как значения могут повторяться.
 *  Так как полученные коллекции значений и ключей неразрывно связаны с картой, любое проведенное
 *  в них изменение немедленно отразится на ней.
 *  <p>
 *  WeakHashMap
 *  Карта, состоящая из «слабых» ключей, которые не препятствуют освобождению объектов, на которые
 *  сылается карта; разработана для решения определенного класса задач. Если за пределами карты ссылок
 *  на ключ нет, он может быть удален уборщиком мусора
 *  <p>
 *  ConcurrentHashMap
 *  Потоково-безопасная версия Мар, не использующая синхронизационную блокировку
 *  <p>
 *  IdentityHashMap
 *  Хеш-таблица, использующая для сравнения ключей оператор == вместо метода equals(). Применяется в особых
 *  случаях, не для рядовых целей.
 */
public class Maps {
    public static void printKeys(Map<Integer, String> map) {
        System.out.print("Size = " + map.size() + ", ");
        System.out.print("Keys: ");
        System.out.println(map.keySet()); // Создание множества ключей
    }
    public static void test(Map<Integer, String> map) {
        System.out.println(map.getClass().getSimpleName());
        map.putAll(new CountingMapData(25));
        // Map реализует поведение 'Set' для ключей:
        map.putAll(new CountingMapData(25));
        printKeys(map);
        // Создание коллекции значений:
        System.out.print("Values: ");
        System.out.println(map.values());
        System.out.println(map);
        System.out.println("map.containsKey(11): " + map.containsKey(11));
        System.out.println("map.get(11): " + map.get(11));
        System.out.println("map.containsValue(\"F0\"): " + map.containsValue("F0"));
        Integer key = map.keySet().iterator().next();
        System.out.println("First key in map: " + key);
        map.remove(key);
        printKeys(map);
        map.clear();
        System.out.println("map.isEmpty(): " + map.isEmpty());
        map.putAll(new CountingMapData(25));
        // Операции с Set изменяют Мар:
        map.keySet().removeAll(map.keySet());
        System.out.println("map.isEmpty(): " + map.isEmpty());
    }

    public static void main(String[] args) {
        test(new HashMap<Integer, String>());
        test(new TreeMap<Integer, String>());
        test(new LinkedHashMap<Integer, String>());
        test(new IdentityHashMap<Integer, String>());
        test(new ConcurrentHashMap<Integer, String>());
        test(new WeakHashMap<Integer, String>());
    }
}