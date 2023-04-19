package generics;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *  Массивы обобщений
 */

// Как было показано в Erased.java, создать массив обобщений невозможно. Как правило,
// проблема решается использованием ArrayList везде, где возникает потребность в создании
// массива обобщений. Здесь мы получаем поведение массива, но с безопасностью типов времени
// компиляции, которую предоставляют обобщения.
public class ListOfGenerics<T> {
    private List<T> array = new ArrayList<>();
    public void add(T item) { array.add(item); }
    public T get(int index) { return array.get(index); }
}

// Время от времени все равно появляется необходимость создания массива обобщенных типов.
// Интересно, что ссылку можно определить так, чтобы компилятор не жаловался.
// Компилятор принимает этот фрагмент, не выдавая никаких предупреждений. Но вам никогда не
// удастся создать массив этого конкретного типа (включая параметры-типы), так что это
// обстоятельство немного сбивает с толку. Компилятор принимает этот фрагмент, не выдавая никаких
// предупреждений. Но вам никогда не удастся создать массив этого конкретного типа (включая
// параметры-типы), так что это обстоятельство немного сбивает с толку.
class Generic<T> {}
class ArrayOfGenericReference {
    static Generic<Integer>[] gia;
}

// Так как все массивы имеют одинаковую структуру (размер каждой ячейки массива и размещение в памяти)
// независимо от хранимого типа, казалось бы, вы можете создать массив Object и привести его к нужному типу.
// Такое решение компилируется, но не работает, выдавая исключение ClassCastException.
// Проблема в том, что массивы хранят свой фактический тип, который устанавливается в момент создания массива.
// Итак, хотя ссылка gia была приведена к Generic<Integer>[], эта информация существует только во время компиляции
// (и без аннотации @SuppressWarnings вы получите предупреждение). Во время выполнения она остается массивом
// Object, и это создает проблемы.
class ArrayOfGeneric {
    static final int SIZE = 100;
    static Generic<Integer>[] gia;
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
// Компилируется; выдает ClassCastException:
//! gia = (Generic<Integer>[])new Object[SIZE];
// Тип времени выполнения - низкоуровневый (стертый) тип:
        gia = (Generic<Integer>[])new Generic[SIZE];
        System.out.println(gia.getClass().getSimpleName());
        gia[0] = new Generic<Integer>();
// Ошибка времени компиляции
//! gia[1] = new Object();
// Обнаруживается несоответствие типов:
//! gia[2] = new Generic<Double>();
    }
}

// Единственный успешный способ создания массива обобщенного типа — создание нового массива «стертого» типа с
// последующим приведением типа.
// Метод rep() возвращает T[], что для gai в main() соответствует Integer[], но при попытке вызвать его и
// сохранить результат по ссылке на Integer[] выдается исключение ClassCastException — снова потому, что
// фактическим типом времени выполнения является Object[].
class GenericArray<T> {
    private T[] array;
    @SuppressWarnings("unchecked")
    public GenericArray(int sz) { array = (T[]) new Object[sz]; }
    public void put(int index, T item) { array[index] = item; }
    public T get(int index) { return array[index]; }
// Предоставляет нижележащее представление:
    public T[] rep() { return array; }
    public static void main(String[] args) {
        GenericArray<Integer> gai = new GenericArray<>(10);
// Приводит к возбуждению ClassCastException:
//! Integer[] ia = gai.rep();
// А это допустимо:
        Object[] oa = gai.rep();
    }
}

// Из-за стирания типом массива во время выполнения может быть только Object[]. Если немедленно привести его к T[],
// то при компиляции фактический тип массива теряется, и компилятор может упустить некоторые потенциальные проверки
// ошибок. По этой причине в коллекции лучше использовать Object[], а выполнять приведение к Т при использовании
// элемента массива. Внутренним представлением теперь является Object[], а не T[]. При вызове get() метод преобразует
// объект к T, т.e. правильному типу, так что операция безопасна. Однако при вызове rep() снова совершается попытка
// преобразования Object[] в т[] — по-прежнему некорректная, поэтому во время компиляции выдается предупреждение, а
// во время выполнения возбуждается исключение. «Подделать» тип нижележащего массива невозможно — им может быть только
// Object[].
class GenericArray2<T> {
    private Object[] array;
    public GenericArray2(int sz) { array = new Object[sz]; }
    public void put(int index, T item) { array[index] = item; }
    @SuppressWarnings("unchecked")
    public T get(int index) { return (T)array[index]; }
    @SuppressWarnings("unchecked") public T[] rep() {
        return (T[]) array; // Предупреждение: неконтролируемое приведение типа
    }
    public static void main(String[] args) {
        GenericArray2<Integer> gai = new GenericArray2<>(10);
        for(int i = 0; i < 10; i ++) gai.put(i, i);
        for(int i = 0; i < 10; i ++)
            System.out.print(gai.get(i) + " ");
        System.out.println();
        try {
            Integer[] ia = gai.rep();
        } catch(Exception e) { System.out.println(e); }
    }
}

// Маркер типа class<T> передается конструктору для восстановления последствий стирания, чтобы мы могли создать фактический
// тип нужного массива, хотя предупреждения от приведения типа приходится подавлять аннотацией @SuppressWarnings. После получения
// фактического типа можно вернуть его и получить нужные результаты, как показано в main(). Типом массива на стадии выполнения
// является точный тип T[].
class GenericArrayWithTypeToken<T> {
    private T[] array;
    @SuppressWarnings("unchecked")
    public GenericArrayWithTypeToken(Class<T> type, int sz) {
        array = (T[]) Array.newInstance(type, sz);
    }
    public void put(int index, T item) { array[index] = item; }
    public T get(int index) { return array[index]; }
    // Предоставляет нижележащее представление:
    public T[] rep() { return array; }
    public static void main(String[] args) {
        GenericArrayWithTypeToken<Integer> gai = new GenericArrayWithTypeToken<>(Integer.class, 10);
        // Теперь работает:
        Integer[] ia = gai.rep();
    }
}