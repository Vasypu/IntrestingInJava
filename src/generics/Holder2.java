package generics;

/**
 *  Простые обобщения
 */

class Automobile {}

// Рассмотрим класс, предназначенный для хранения одного объекта.
// Этот класс нельзя назвать универсальным, потому что никакие
// другие данные в нем храниться не могут.
class Holder1 {
    private Automobile a;
    public Holder1(Automobile a) { this.a = a; }
    Automobile get() { return a; }
}

// До выхода Java SE5 можно было бы просто хранить поле Object.
public class Holder2 {
    private Object a;
    public Holder2(Object a) { this.a = a; }
    public void set(Object a) { this.a = a; }
    public Object get() { return a; }

    public static void main(String[] args) {
        Holder2 h2 = new Holder2(new Automobile());
        Automobile a = (Automobile)h2.get();
        h2.set("Not an Automobile");
        String s = (String)h2.get();
        h2.set(1); // Автоматическая упаковка в Integer
        Integer x = (Integer)h2.get();
    }
}

// В этом примере параметр-тип обозначается идентификатором T:
class Holder3<T> {
    private T a;
    public Holder3(T a) { this.a = a; }
    public void set(T a) { this.a = a; }
    public T get() { return a; }
    public static void main(String[] args) {
        Holder3<Automobile> h3 = new Holder3<Automobile>(new Automobile());
        Automobile a = h3.get(); // Преобразование не требуется
//         h3.set("Not an Automobile"); // Ошибка
//         h3.set(1); // Ошибка
    }
}
