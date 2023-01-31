package generics;

import static generics.Tuple.tuple;

/**
 *  Упрощение использования кортежей
 *  <p>
 *  Автоматическое определение аргументов-типов в сочетании со статическим импортированием
 *  позволяет оформить кортежи. Обратите внимание: f() возвращает параметризованный объект
 *  TwoTuple, тогда как f2() возвращает непараметризованный объект TwoTuple. В данном случае
 *  компилятор не выдает предупреждение о f2(), потому что возвращаемое значение не используется
 *  «параметризованным образом»; в некотором смысле оно превращается в непараметризованную
 *  версию TwoTuple восходящим преобразованием.
 */
public class Tuple {
    public static <A,B> TwoTuple<A,B> tuple(A a, B b) { return new TwoTuple<>(a, b); }
    public static <A,B,C> ThreeTuple<A,B,C> tuple(A a, B b, C c) { return new ThreeTuple<>(a, b, c); }
    public static <A,B,C,D> FourTuple<A,B,C,D> tuple(A a, B b, C c, D d) { return new FourTuple<>(a, b, c, d); }
    public static <A,B,C,D,E> FiveTuple<A,B,C,D,E> tuple(A a, B b, C c, D d, E e) { return new FiveTuple<>(a, b, c, d, e); }
}

class TupleTest2 {
    static TwoTuple<String,Integer> f() { return tuple("hi", 47); }
    static TwoTuple f2() { return tuple("hi", 47); }
    static ThreeTuple<Amphibian,String,Integer> g() { return tuple(new Amphibian(), "hi", 47); }
    static FourTuple<Vehicle,Amphibian,String,Integer> h() { return tuple(new Vehicle(), new Amphibian(), "hi", 47); }
    static FiveTuple<Vehicle,Amphibian,String,Integer,Double> k() { return tuple(new Vehicle(), new Amphibian(), "hi", 47, 11.1); }
    public static void main(String[] args) {
        TwoTuple<String, Integer> ttsi = f();
        // При попытке сохранить результат f2() в параметризованном
        // объекте TwoTuple компилятор выдаст предупреждение
        TwoTuple<String, Integer> ttsi2 = f2();
        System.out.println(ttsi);
        System.out.println(f2());
        System.out.println(g());
        System.out.println(h());
        System.out.println(k());
    }
}
