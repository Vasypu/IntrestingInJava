package initialization.oder_of_initialization;

/**
 *  Последовательность инициализации.
 *
 *  В классе House определения объектов Window намеренно разбросаны, чтобы доказать,
 *  что все они инициализируются перед выполнением конструктора или каким-то другим
 *  действием. Вдобавок ссылка w3 заново проходит инициализацию в конструкторе.
 */

public class Window {
    Window(int marker) {
        System.out.println("Window(" + marker + ")");
    }
}

class House {
    Window w1 = new Window(1);
    House() {
        System.out.println("House()");
        w3 = new Window(33);
    }
    Window w2 = new Window(2);
    void f() {
        System.out.println("f()");
    }
    Window w3 = new Window(3);
}

class OrderOfInitialization {
    public static void main(String[] args) {
        House h = new House();
        h.f();
    }
}