package colections;

import containers.Countries;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *  Функциональность List
 *  <p>
 *  Методы следующего примера охватывают совокупность различных видов действий: то, что может каждый список (метод basicTest()),
 *  перемещение по списку посредством итератора (iterMotion()) в сравнении с изменением списка с помощью итератора (iterManipulation()),
 *  просмотр результатов операций со списками (testVisual()) и операции, доступные только в классе LinkedList.
 */
public class Lists {
    private static boolean b;
    private static String s;
    private static int i;
    private static Iterator<String> it;
    private static ListIterator<String> lit;
    public static void basicTest(List<String> a) {
        a.add(1, "x"); // Добавление в позиции 1
        a.add("x"); // Добавление в конце
        // Добавление коллекции:
        a.addAll(Countries.names(25));
        // Добавление коллекции с позиции 3:
        a.addAll(3, Countries.names(25));
        b = a.contains("1"); // Значение присутствует?
        // Вся коллекция?
        b = a.containsAll(Countries.names(25));
        // Списки обеспечивают произвольный доступ - быстрый для ArrayList, медленный для LinkedList:
        s = a.get(1); // Получение (типизованного) объекта в позиции 1
        i = a.indexOf("1"); // Получение индекса объекта
        b = a.isEmpty(); // Список содержит элементы?
        it = a.iterator(); // Обычный Iterator
        lit = a.listIterator(); // ListIterator
        lit = a.listIterator(3); // Начать с позиции 3
        i = a.lastIndexOf("1"); // Последнее вхождение
        a.remove(1); // Удаление в позиции 1
        a.remove("3"); // Удаление заданного объекта
        a.set(1, "y"); // Записать "у" в позицию 1
        // Оставить все элементы, присутствующие в аргументе (пересечение двух множеств):
        a.retainAll(Countries.names(25));
        // Удаление всех элементов, присутствующих в аргументе:
        a.removeAll(Countries.names(25));
        i = a.size(); // Определение размера
        a.clear(); // Удаление всех элементов
    }
    public static void iterMotion(List<String> a) {
        ListIterator<String> it = a.listIterator();
        b = it.hasNext();
        b = it.hasPrevious();
        s = it.next();
        i = it.nextIndex();
        s = it.previous();
        i = it.previousIndex();
    }
    public static void iterManipulation(List<String> a) {
        ListIterator<String> it = a.listIterator();
        it.add("47");
        it.next(); // Переход к элементу после add()
        it.remove(); // Удаление элемента за вновь созданным
        it.next(); // Переход к элементу после remove()
        it.set("47"); // Изменение элемента после удаленного
    }
    public static void testVisual(List<String> a) {
        System.out.println(a);
        List<String> b = Countries.names(25);
        System.out.println("b = " + b);
        a.addAll(b);
        a.addAll(b);
        System.out.println(a);
        // Вставка, удаление и замена элементов
        // с использованием ListIterator:
        ListIterator<String> x = a.listIterator(a.size() / 2);
        x.add("one");
        System.out.println(a);
        System.out.println(x.next());
        x.remove();
        System.out.println(x.next());
        x.set("47");
        System.out.println(a);
        // Перебор списка в обратном направлении:
        x = a.listIterator(a.size());
        while (x.hasPrevious())
            System.out.print(x.previous() + " ");
        System.out.println();
        System.out.println("testVisual finished");
    }
    // Некоторые операции, поддерживаемые только для LinkedList:
    public static void testLinkedList() {
        LinkedList <String> ll = new LinkedList<String>();
        ll.addAll(Countries.names(25));
        System.out.println(ll);
        // Интерпретировать как стек - занесение:
        ll.addFirst("one");
        ll.addFirst("two");
        System.out.println(ll);
        // Аналог чтения с вершины стека (без извлечения):
        System.out.println(ll.getFirst());
        // Аналог извлечения из стека:
        System.out.println(ll.removeFirst());
        System.out.println(ll.removeFirst());
        // Интерпретировать как очередь с извлечением элементов с конца:
        System.out.println(ll.removeLast());
        System.out.println(ll);
    }
    public static void main(String[] args) {
        // Каждый раз создается и заполняется новый список:
        basicTest(new LinkedList<String>(Countries.names(25)));
        basicTest(new ArrayList<String>(Countries.names(25)));
        iterMotion(new LinkedList<String>(Countries.names(25)));
        iterMotion(new ArrayList<String>(Countries.names(25)));
        iterManipulation(new LinkedList<String>(Countries.names(25)));
        iterManipulation(new ArrayList<String>(Countries.names(25)));
        testVisual(new LinkedList<String>(Countries.names(25)));
        testLinkedList();
    }
}