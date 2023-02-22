package generics;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  Граничные ситуации
 *  <p>
 *  Стирание удаляет информацию о фактическом типе внутри метода или класса, но есть "граничные точки",
 *  в которых объекты входят в метод и покидают его. В этих точках компилятор выполняет проверку типа
 *  во время компиляции и вставляет код приведения типа.
 */

// kind хранится в виде Class<T>, механизм стирания означает, что в действительности хранится
// только Class без параметра. Таким образом, при выполнении любой операции (например, создании
// массива) Array.newInstance() не располагает информацией типа, подразумеваемой для kind;
// следовательно, метод не может выдать конкретный результат, и возникает необходимость в приведении
// типа с выдачей предупреждения. Метод Array.newInstance() рекомендуется использовать для создания
// массивов в обобщениях.
public class ArrayMaker<T> {
    private Class<T> kind;
    public ArrayMaker(Class<T> kind) { this.kind = kind; }
    @SuppressWarnings("unchecked")
    T[] create(int size) {
        return (T[]) Array.newInstance(kind, size);
    }
    public static void main(String[] args) {
        ArrayMaker<String> stringMaker = new ArrayMaker<>(String.class);
        String[] stringArray = stringMaker.create(9);
        System.out.println(Arrays.toString(stringArray));
    }
}

// Компилятор не выдает предупреждений, хотя мы знаем (из-за стирания), что <T> в new ArrayList<T>()
// внутри create() исключается — во время выполнения в классе нет <T>, что выглядит бессмысленно. Но
// если заменить выражение на new ArrayList(), компилятор выдает предупреждение
class ListMaker<T> {
    List<T> create() { return new ArrayList<T>(); }
    public static void main(String[]args) {
        ListMaker<String> stringMaker = new ListMaker<>();
        List<String> stringList = stringMaker.create();
    }
}

// компилятор не может ничего знать о T внутри create(), он все равно может проследить
// (во время компиляции) за тем, что значения, помещаемые в result, относятся к типу Т,
// и поэтому соглашается с ArrayList<T>. Итак, хотя стирание удаляет информацию о фактическом
// типе внутри метода или класса, компилятор все равно может обеспечить внутреннюю целостность
// использования типа в методе или классе
class FilledListMaker<T> {
    List<T> create(T t, int n) {
        List<T> result = new ArrayList<T>();
        for (int i = 0; i < n; i++)
            result.add(t);
        return result;
    }
    public static void main (String[]args) {
        FilledListMaker<String> stringMaker = new FilledListMaker<>();
        List<String> list = stringMaker.create("Hello", 4);
        System.out.println(list);
    }
}

// Так как стирание удаляет информацию о типе в теле метода, во время выполнения важны
// граничные точки, в которых объекты входят в метод и покидают его. В этих точках компилятор
// выполняет проверку типа во время компиляции и вставляет код приведения типа.
class SimpleHolder {
    private Object obj;
    public void set(Object obj) { this.obj = obj; }
    public Object get() { return obj; }

    public static void main(String[] args) {
        SimpleHolder holder = new SimpleHolder();
        holder.set("Item");
        String s = (String) holder.get();
    }
}

// В примерах SimpleHolder и GenericHolder методы get() и set() генерируют одинаковый байт-код, все
// действие происходит в граничных точках — дополнительная проверка входных значений во время компиляции
// и вставляемое приведение типа для выходных значений. Для прояснения путаницы со стиранием полезно
// запомнить, что «все реальные действия происходят в граничных точках»
class GenericHolder<T> {
    private T obj;
    public void set(T obj) { this.obj = obj; }
    public T get() { return obj; }

    public static void main(String[] args) {
        GenericHolder<String> holder = new GenericHolder<>();
        holder.set("Item");
        String s = holder.get();
    }
}