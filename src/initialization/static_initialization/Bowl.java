package initialization.static_initialization;

public class Bowl {
    Bowl(int marker) {
        System.out.println("Bowl(" + marker + ")");
    }
    void fl(int marker) {
        System.out.println("fl(" + marker + ")");
    }
}

class Table {
    static Bowl bowl1 = new Bowl(1);
    Table() {
        System.out.println("Table()");
        bowl2.fl(1);
    }
    void f2(int marker) {
        System.out.println("f2(" + marker + ")");
    }
    static Bowl bowl2 = new Bowl(2);
}

class Cupboard {
    Bowl bowl3 = new Bowl(3);
    static Bowl bowl4 = new Bowl(4);
    Cupboard() {
        System.out.println("Cupboard()");
        bowl4.fl(2);
    }
    void f3(int marker) {
        System.out.println("f3(" + marker + ")");
    }
    static Bowl bowl5 = new Bowl(5);
}

class StaticInitialization {
    public static void main(String[] args) {
        System.out.println("Создание нового объекта Cupboard в main()");
        new Cupboard();
        System.out.println("Создание нового объекта Cupboard в main()");
        new Cupboard();
        table.f2(1);
        cupboard.f3(1);
    }
    static Table table = new Table();
    static Cupboard cupboard = new Cupboard();
}

/**
 * Сначла инициализируются static-члены, если они еще не были проинициализированны, и только затем нестатические объекты.
 * Для выполнения main() (а это статический метод!) загружется класс StaticInitialization; затем инициализируются статические поля
 * table и cupboard. вследствии чего загружаются эти классы. И так как они все содержат статические объекты Bowl,
 * загружается класс Bowl. Таким образом, все классы программы загружаются до начала main().
 * При повторном создании экземпляров класса Cupboard в методе main() не инициализируются статические переменные.
 */