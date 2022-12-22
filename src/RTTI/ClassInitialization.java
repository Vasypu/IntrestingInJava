package RTTI;

/**
 * B Java есть еще один способ получить ссылку на объект Class — посредством литерала class. В рассмотренной
 * программе мы бы получили ее таким образом:
 * Gum.class;
 * Литералы class работают со всеми обычными классами, так же как и с интерфейсами, массивами и даже с примитивами.
 * Вдобавок во всех классах-оболочках для простейших типов имеется поле с именем TYPE. Это поле содержит ссылку
 * на объект Class для ассоциированного с ним простейшего типа.
 * <p>
 * Инициализация откладывается до первого обращения к статическому методу (конструктор неявно является статическим)
 * или не-константному статическому полю. По сути инициализация откладывается настолько, насколько возможно.
 * Из создания ссылки initable мы видим, что простое использование синтаксиса .class для получения ссылки на класс
 * не приводит к инициализации. С другой стороны, вызов Class.forName() немедленно инициализирует класс для получения
 * ссылки на Class, как мы видим на примере создания initable3.
 * <p>
 * Если статическое неизменное (static final) значение является «константой времени компиляции», как lnitable.staticFinal,
 * оно может читаться без инициализации класса Initable. Впрочем, объявление поля со спецификаторами static и final
 * не гарантирует этого поведения; обращение к lnitable.staticFinal2 запускает инициализацию класса, потому что это
 * значение не может быть константой времени компиляции.
 * <p>
 * Если static-поле не объявлено с ключевым словом final, то при обращении к нему чтение возможно только после проведения
 * компоновки (для выделения памяти) и инициализации (для инициализации выделенной памяти), как мы видим на примере
 * Initable2.staticNonFinal.
 */

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
