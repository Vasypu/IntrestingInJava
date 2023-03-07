package generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  Динамическая безопасность типов
 *  <p>
 *  BJava SE5 входит библиотека java.util.Collections с набором инструментов для решения проблем проверки
 *  типов в подобных ситуациях: статические методы checkedCollection(), checkedList(), checkedMap(), checkedSet(),
 *  checkedSortedMap() и checkedSortedSet().
 *  Контролируемый контейнер возбуждает исключение ClassCastException при попытке вставки неподходящего объекта
 *  (в отличие от неспециализированного контейнера, который сообщит о проблеме при извлечении объекта). В последнем
 *  случае вы знаете, что проблема существует, но не знаете ее причины; с контролируемыми контейнерами вы будете знать,
 *  кто попытался вставить недопустимый объект.
 */
class Pet2 {
    String name;
    int id = 0;
    public Pet2(String name) {
        this.name = name;
        id++;
    }
    public Pet2() { id++; }
    public int id() { return id; }
}

class Dog extends Pet2 {
    public Dog(String name) { super(name);}
    public Dog() { super(); }
    public String toString() { return "Dog " + name; }
}

class Cat extends Pet2 {
    public Cat(String name) { super(name);}
    public Cat() { super(); }
    public String toString() { return "Cat " + name; }
}

// Вставка Cat проходит незамеченной для dogs1, а dogs2 немедленно возбуждает исключение при вставке неправильного типа.
// Также видно, что объекты производного типа могут помещаться в контейнер с проверкой базового типа.
public class CheckedList {
    @SuppressWarnings("unchecked")
    static void oldStyleMethod(List probablyDogs) {
        probablyDogs.add(new Cat());
    }
    public static void main(String[] args) {
        List<Dog> dogs1 = new ArrayList<Dog>();
        oldStyleMethod(dogs1); // Принимает Cat без возражений
        List<Dog> dogs2 = Collections.checkedList(new ArrayList<Dog>(), Dog.class);
        try {
            oldStyleMethod(dogs2); // Возбуждает исключение
        } catch(Exception e){
            System.out.println(e);
        }
        // Производные типы работают нормально:
        List<Pet2> pets = Collections.checkedList( new ArrayList<Pet2>(), Pet2.class);
        pets.add(new Dog());
        pets.add(new Cat());
    }
}
