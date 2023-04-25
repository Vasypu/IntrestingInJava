package containers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  Заполнение контейнеров
 *  <p>
 *  Пример демонстрирует два способа заполнения Collection ссылками на один объект. Первый,
 *  Collections.nCopies(), создает объект List, который передается конструктору и используется
 *  для заполнения ArrayList.
 *  <p>
 *  Метод toString() в StringAddress вызывает метод Object.toString(), который возвращает имя
 *  класса с шестнадцатеричным беззнаковым представлением хеш-кода объекта (сгенерированным
 *  методом hashCode()). Из результатов видно, что все ссылки указывают на один объект, причем
 *  ситуация не изменяется и после вызова второго метода Collections.fill(). Полезность метода
 *  fill() снижается еще и из-за того, что он может только заменять элементы, уже присутствующие
 *  в списке, и не может добавлять новые элементы.
 */
public class StringAddress {
    private String s;
    public StringAddress(String s) { this.s = s; }
    public String toString () { return super.toString() + " " + s; }
}

class FillingLists {
    public static void main(String[] args) {
        List<StringAddress> list = new ArrayList<>(Collections.nCopies(4, new StringAddress("Hello")));
        System.out.println(list);
        Collections.fill(list, new StringAddress("World!"));
        System.out.println(list);
    }
}
