package RTTI;

import java.util.Random;

class Initable {
    static final int staticFinal = 47;
    static final int staticFinal2 = ClassInitialization.rand.nextInt(1000);
    static { System.out.println("Инициализация Initable"); }
}
class Initable2 {
    static int staticNonFinal = 147;
    static { System.out.println("Инициализация Initable2"); }
}
class Initable3 {
    static int staticNonFinal = 74;
    static { System.out.println("Инициализация Initable3"); }
}

public class ClassInitialization {
    public static Random rand = new Random(47);
    public static void main(String[] args) throws Exception {
        Class initable = Initable.class;
        System.out.println("после создания ссылки на Initable");
        // Не запускает процесс инициализации:
        System.out.println(Initable.staticFinal);
        // Запускает процесс инициализации:
        System.out.println(Initable.staticFinal2);
        // Запускает процесс инициализации:
        System.out.println(Initable2.staticNonFinal);
        Class initable3 = Class.forName("RTTI.Initable3");
        System.out.println("после создания ссылки на Initable3");
        System.out.println(Initable3.staticNonFinal);
    }
}
