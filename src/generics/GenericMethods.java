package generics;

/**
 *  Обобщенные методы
 *  <p>
 *  Чтобы определить обобщенный метод, следует поместить список параметров-типов
 *  перед возвращаемым значением.
 */
public class GenericMethods {
    public <T> void f(T x) { System.out.println(x.getClass().getName()); }

    public static void main(String[] args) {
        GenericMethods gm = new GenericMethods();
        gm.f("");
        gm.f(1);
        gm.f(1.0);
        gm.f(1.0F);
        gm.f('c');
        gm.f(gm);
    }
}
