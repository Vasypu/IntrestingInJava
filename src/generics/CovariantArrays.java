package generics;

import java.util.ArrayList;
import java.util.List;

class Fruit {}
class Apple extends Fruit {}
class Jonathan extends Apple {}
class Orange extends Fruit {}

public class CovariantArrays {
    public static void main(String[] args) {
        Fruit[] fruit = new Apple[10];
        fruit[0] = new Apple();    // 0K
        fruit[1] = new Jonathan(); // 0K
        // Тип времени выполнения - Apple[], а не Fruit[] и не Orange[]:
        try {
        // Компилятор позволяет добавить Fruit:
            fruit[0] = new Fruit(); // ArrayStoreException
        } catch(Exception e) { System.out.println(e); }
        try {
        // Компилятор позволяет добавлять Orange:
            fruit[0] = new Orange(); // ArrayStoreException
        } catch(Exception e) { System.out.println(e); }
    }
}

class GenericsAndCovariance {
    public static void main(String[] args) {
        // Маски обеспечивают ковариантность:
        List<? extends Fruit> flist = new ArrayList<Apple>();
        // Ошибка компиляции: добавление объектов невозможно:
        // flist.add(new Apple());
        // flist.add(new Fruit());
        // flist.add(new Object());
        flist.add(null); // Допустимо, но неинтересно
        // Мы знаем, что возвращается как минимум Fruit:
        Fruit f = flist.get(0);
    }
}