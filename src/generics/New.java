package generics;

import java.util.*;

/**
 *  Использование автоматического определения аргументов-типов (не актуален после обновления java)
 *  <p>
 *  Обобщения часто приводят к увеличению объема кода. Создается впечатление, что мы повторяемся, а
 *  компилятор мог бы определить один из списков обобщенных аргументов по другому. К сожалению, он
 *  этого сделать не может, но механизм автоматического определения аргументов-типов в обобщенных
 *  методах способен привести к некоторому упрощению. Например, можно создать вспомогательный класс
 *  с различными статическими методами, который предоставляет наиболее часто используемые реализации
 *  различных контейнеров.
 */
public class New {
    public static <K,V> Map<K,V> map() { return new HashMap<K, V>(); }
    public static <T> List<T> list() { return new ArrayList<T>(); }
    public static <T> LinkedList<T> lList() { return new LinkedList<T>(); }
    public static <T> Set<T> set() { return new HashSet<T>(); }
    public static <T> Queue<T> queue() { return new LinkedList<T>(); }
    // Примеры:
    public static void main(String[] args) {
        Map<String, List<String>> sls = New.map();
        List<String> ls = New.list();
        LinkedList<String> lls = New.lList();
        Set<String> ss = New.set();
        Queue<String> qs = New.queue();
    }
}

// Автоматическое определение аргументов-типов устраняет необходимость в повторении списка
// параметров обобщения.
class SimplerPets {
    public static void main(String[] args) {
        Map<Person, List<? extends Pet>> petPeople = new HashMap<Person, List<? extends Pet>>();

        Map<Person, List<? extends Pet>> petPeopleTwo = New.map();
        // Остальной код остается без изменений...
    }
}

class Person {}
class Pet {}