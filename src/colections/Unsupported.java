package colections;

import java.util.*;

/**
 *  Необязательные операции
 *  <p>
 *  Некоторые методы не выполняют осмысленных действий. Вместо этого они возбуждают исключения!
 *  Зачем определять методы как «необязательные»? Это предотвращает размножение интерфейсов в архитектуре.
 *  Во многих библиотеках контейнеров появляется невразумительная куча интерфейсов, описывающих все вариации
 *  на основную тему. В интерфейсах даже невозможно отразить все особые случаи, потому что кто-нибудь всегда
 *  сможет изобрести новый интерфейс.
 *  <p>
 *  Неподдерживаемые операции
 *  <p>
 *  Самая частая причина неподдерживаемых операций — контейнеры, работающие на базе структуры данных с фиксированным
 *  размером. Такие контейнеры создаются в результате преобразования массива в List методом Arrays.asList().
 *  Так как Arrays.asList() создает List на базе массива фиксированного размера, вполне разумно, что поддерживаются
 *  только операции, не изменяющие размер массива. Любой метод, приводящий к изменению размера нижележащей структуры
 *  данных, выдает исключение UnsupportedOperationException для обозначения вызова неподдерживаемого метода (ошибка программирования).
 *  Метод Arrays.asList() возвращает List фиксированного размера, a Collections.unmodifiableList() создает список, который не
 *  может изменяться. Как видно из результатов, изменение элементов контейнера List, возвращаемого Arrays.asList(), не создает
 *  проблем, потому что оно не нарушает характеристики фиксированного размера этого списка. Но при этом очевидно, что результат
 *  unmodifiableList() не должен изменяться никаким образом.
 *  Результат Arrays.asList() всегда можно передать в аргументе конструктора любой реализации Collection (или воспользоваться
 *  методом addAll(), или статическим методом Collections.addAll()) для создания обычного контейнера, допускающего использование
 *  всех методов (продемонстрирована при первом вызове test() в main()).
 *  Если бы использовались интерфейсы, для этого понадобились бы два дополнительных интерфейса: один с рабочим методом set(),
 *  а другой без него.
 */
public class Unsupported {
    static void test(String msg, List<String> list) {
        System.out.println("--- " + msg + " —");
        Collection<String> c = list;
        Collection<String> subList = list.subList(1,8);
        // Копирование подсписки:
        Collection<String> c2 = new ArrayList<String>(subList);
        try { c.retainAll(c2); } catch(Exception e) {
            System.out.println("retainAll(): " + e);
        }
        try { c.removeAll(c2); } catch(Exception e) {
            System.out.println("removeAll(): " + e);
        }
        try { c.clear(); } catch(Exception e) {
            System.out.println("clear(): " + e);
        }
        try { c.add("X"); } catch(Exception e) {
            System.out.println("add(): " + e);
        }
        try { c.addAll(c2); } catch(Exception e) {
            System.out.println("addAll(): " + e);
        }
        try { c.remove("C"); } catch (Exception e) {
            System.out.println("remove(): " + e);
        }
        // Метод List.set() изменяет значение, но не изменяет размер структуры данных:
        try { list.set(0, "X"); } catch (Exception e) {
            System.out.println("List.set(): " + e);
        }
    }
    public static void main(String[] args) {
        List<String> list = Arrays.asList("A В С D E F G H I J К L".split(" "));
        test("Modifiable Copy", new ArrayList<String>(list));
        test("Arrays.asList()", list);
        test("unmodifiableList()",
        Collections.unmodifiableList(new ArrayList<String>(list)));
    }
}