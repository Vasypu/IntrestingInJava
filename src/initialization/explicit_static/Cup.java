package initialization.explicit_static;

/**
 *  Статический инициализатор класса Cups выполняется либо при обращении к статическому объекту с1 в строке с пометкой (1),
 *  либо если строка (1) закоментирована - в строках (2) после снятия комментариев. Если же и стрпока (1), и строки (2)
 *  закоментированны, static-инициализация класса Cups никогда не выполнится. Также невыжно, будут ли исполнены одна или обе строки
 *  (2) программы - static-инициализация все равно выполняется только один раз.
 */

// явная инициализация с помощью конструкции "static"
public class Cup {
    Cup(int marker) {
        System.out.println("Cup(" + marker + ")");
    }
    void f(int marker) {
        System.out.println("f(" + marker + ")");
    }
}

class Cups {
    static Cup cup1;
    static Cup cup2;
    static {
        cup1 = new Cup(1);
        cup2 = new Cup(2);
    }
    Cups() {
        System.out.println("Cups()");
    }
}

class ExplicitStatic {
    public static void main(String[] args) {
        System.out.println("Inside main()");
        Cups.cup1.f(99); // (1)
    }
    static Cups cups1 = new Cups(); // (2)
    static Cups cups2 = new Cups(); // (2)
}