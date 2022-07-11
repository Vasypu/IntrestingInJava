package interfaces;

/**
 * Множественное наследование. При объединении реального класса с интерфейсами на
 * первом месте должен стоять реальный класс, а за ним следуют интерфейсы (иначе будет ошибка).
 *
 */

interface CanFight {
    void fight();           // совпадает с методом fight класса ActionCharacter
}

interface CanFly {
    void fly();
}

interface CanSwim {
    void swim();
}

class ActionCharacter {
    public void fight() {}
}

class Hero extends ActionCharacter
        implements CanFight, CanFly, CanSwim {
    public void swim() {}
    public void fly() {}
}

public class Adventure {
    public static void t(CanFight x) { x.fight(); }
    public static void u(CanSwim x) { x.swim(); }
    public static void v(CanFly x) { x.fly(); }
    public static void w(ActionCharacter x) { x.fight(); }
    public static void main(String[] args) {
        Hero h = new Hero();
        t(h);                                               // передаем в метод t() где у объекта вызывают метод fight()
        u(h);                                               // можем передать в метод так как класс ActionCharacter с методом fight() расширяет интерфейс
        v(h);
        w(h);
    }
}
