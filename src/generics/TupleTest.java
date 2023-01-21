package generics;

/**
 *  На практике при вызове метода часто возникает необходимость вернуть несколько объектов.
 *  Команда return позволяет вернуть только один объект, поэтому задачу приходится решать
 *  созданием объекта, содержащего несколько возвращаемых объектов.
 *  <p>
 *  Группа объектов, «завернутых» в один объект, называется кортежем (tuple). Получатель
 *  объекта может читать элементы, но не может добавлять новые (эта концепция также
 *  называется «объектом передачи данных»).
 *  <p>
 *  Обычно кортеж может иметь произвольную длину, но все объекты кортежа могут относиться
 *  к разным типам.
 */
class TwoTuple<A,B> {
    public final A first;
    public final B second;
    public TwoTuple(A a, B b) { first = a; second = b; }
    public String toString() { return "(" + first + ", " + second + ")"; }
}

class ThreeTuple<A,B,C> extends TwoTuple<A,B> {
    public final C third;
    public ThreeTuple(A a, B b, C c) {
        super(a, b);
        third = c;
    }
    public String toString() { return "(" + first + ", " + second + ", " + third + ")"; }
}

class FourTuple<A, B,C,D> extends ThreeTuple<A,B,C> {
    public final D fourth;
    public FourTuple(A a, B b, C c, D d) {
        super(a, b, c);
        fourth = d;
    }
    public String toString() { return "(" + first + ", " + second + ", " + third + ", " + fourth + ")"; }
}

class FiveTuple<A,B,C,D,E> extends FourTuple<A,B,C,D> {
    public final E fifth;
    public FiveTuple(A a, B b, C c, D d, E e) {
        super(a, b, c, d);
        fifth = e;
    }
    public String toString() {
        return "(" + first + ", " + second + ", " + third + ", " + fourth + ", " + fifth + ")";
    }
}

class Amphibian {}
class Vehicle {}

public class TupleTest {
    static TwoTuple<String,Integer> f() {
        // Автоматическая упаковка преобразует int в Integer:
        return new TwoTuple<String,Integer>("hi", 47);
    }
    static ThreeTuple<Amphibian,String,Integer> g() {
        return new ThreeTuple<Amphibian, String, Integer>(
                new Amphibian(), "hi", 47);
    }
    static FourTuple<Vehicle,Amphibian,String,Integer> h() {
        return new FourTuple<Vehicle, Amphibian, String, Integer>(
                new Vehicle(), new Amphibian(), "hi", 47);
    }
    static FiveTuple<Vehicle,Amphibian,String,Integer,Double> k() {
        return new FiveTuple<Vehicle, Amphibian, String, Integer, Double>(
                new Vehicle(), new Amphibian(), "hi", 47, 11.1);
    }
    public static void main(String[] args) {
        TwoTuple<String, Integer> ttsi = f();
        System.out.println(ttsi);
        // ttsi.first = "there"; // Ошибка компиляции: final
        System.out.println(g());
        System.out.println(h());
        System.out.println(k());
    }
}
