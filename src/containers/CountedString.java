package containers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Переопределение hashCode()
 *  <p>
 *  Важная характеристика метода hashCode() — возвращение одного и того же хеш-кода для определенного объекта
 *  при каждом вызове метода. Если ваш объект при помещении в HashMap методом put() возвращает один хеш-код,
 *  а при получении значения из таблицы методом get() — другой, выборка объектов из таблицы станет невозможной.
 *  <p>
 *  У строк String есть одна особенность: если в программе существует несколько объектов String, содержащих одинаковые
 *  последовательности символов, то все ссылки на эти строки указывают на один и тот же участок памяти. Поэтому важно,
 *  чтобы метод hashCode() у двух разных экземпляров строк, созданных выражением new string( "Hello"), возвращал
 *  одинаковые хеш-коды.
 *
 */
class StringHashCode {
    public static void main(String[] args) {
        String[] hellos = "Hello Hello".split(" ");
        System.out.println(hellos[0].hashCode());
        System.out.println(hellos[1].hashCode());
    }
}

// Оба важных метода класса — и hashCode(), и equals() — дают результаты, основанные на значимых полях; если бы они
// опирались только на данные строки или только на переменную id, даже для различных объектов могли бы получиться
// одни и те же значения.
public class CountedString {
    private static List<String> created = new ArrayList<String>();
    private String s;
    private int id = 0;
    public CountedString(String str) {
        s = str;
        created.add(s);
        // id - общее количество экземпляров данной строки, используемых классом CountedString:
        for (String s2 : created)
            if (s2.equals(s)) id++;
    }
    public String toString() {
        return "String: " + s + " id: " + id +
                " hashCode(): " + hashCode();
    }
    public int hashCode() {
        // Очень простая схема:
        // вернуть s.hashCode() * id
        // по рецепту Джошуа Блоша:
        int result = 17;
        result = 37 * result + s.hashCode();
        result = 37 * result + id;
        return result;
    }
    public boolean equals(Object o) {
        return o instanceof CountedString &&
                s.equals(((CountedString) o).s) &&
                id == ((CountedString) o).id;
    }
    public static void main(String[] args) {
        Map<CountedString,Integer> map = new HashMap<>();
        CountedString[] cs = new CountedString[5];
        for(int i = 0; i < cs.length; i++) {
            cs[i] = new CountedString("hi");
            map.put(cs[i], i); // Упаковка int -> Integer
        }
        System.out.println(map);
        for(CountedString cstring : cs) {
            System.out.println("Looking up " + cstring);
            System.out.println(map.get(cstring));
        }
    }
}