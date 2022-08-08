package innerclasses;

/**
 *  Разница анонимного и локального класса.
 *
 *  - Локальные классы могут использовать final поля внешнего класса.
 *
 *  Причина для использования локального класса вместо анонимного это необходимость в
 *  именованном конструкторе и (или) перегруженных конструкторах.
 *  Анонимные классы допускают только инициализацию в блоках инициализации.
 */

interface Counter{
    int next();
}

public class LocalInnerClass {
    private int count = 0;
    final int x = 10;

    Counter getCounter(final String name) {
        class LocalCounter implements Counter {         // локальный класс
            public LocalCounter() {
                System.out.println("LocalCounter() " + x);
            }
            public int next() {
                System.out.println(name);
                return count++;
            }
        }
        return new LocalCounter();
    }

    Counter getCounter2(final String name) {
        return new Counter() {                          // анонимный класс
            {
                System.out.println("Counter() " + x);
            }
            public int next() {
                System.out.println(name);
                return count++;
            }
        };
    }
    public static void main(String[] args) {
        LocalInnerClass lic = new LocalInnerClass();
        Counter
                c1 = lic.getCounter("локальный"),
                c2 = lic.getCounter2("анонимный");
        for (int i = 0; i < 5; i++)
            System.out.print(c1.next());
        for (int i = 0; i < 5; i++)
            System.out.print(c2.next());

        Counter c3 = lic.getCounter("новый локальный");
        Counter c4 = lic.getCounter2("новый анонимный");
        c3.next();
        c4.next();
        c1.next();
        c2.next();
    }
}
