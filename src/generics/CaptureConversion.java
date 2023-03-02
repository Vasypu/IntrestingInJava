package generics;

/**
 *  Фиксация
 *  <p>
 *  Если передать неспециализированный тип методу, использующему<?>, компилятор может автоматически вычислить
 *  фактический параметр-тип и вызвать другой метод, использующий точный тип. Этот механизм, называется фиксацией
 *  (capture conversion), потому что неуказанный тип маски фиксируется и преобразуется к точному типу, поэтому в
 *  методе f2() не выдается предупреждение.
 *  Фиксация работает только в ситуациях, при которых внутри метода необходимо работать с точным типом. Учтите,
 *  что вы не можете вернуть T из f2(), потому что тип T неизвестен для f2().
 */
public class CaptureConversion {
    static <T> void f1(Holder<T> holder) {
        T t = holder.get();
        System.out.println(t.getClass().getSimpleName());
    }
    static void f2(Holder<?> holder) {
        f1(holder); // Вызов с зафиксированным типом
    }
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Holder raw = new Holder<Integer>(1);
        f1(raw); // Выдает предупреждения
        f2(raw); // Без предупреждений
        Holder rawBasic = new Holder();
        rawBasic.set(new Object()); // Предупреждение
        f2(rawBasic); // Без предупреждений
        // Восходящее преобразование к Holder<?>,
        // тип все равно определяется правильно:
        Holder<?> wildcarded = new Holder<Double>(1.0);
        f2(wildcarded);
    }
}
