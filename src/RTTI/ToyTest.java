package RTTI;

/**
 * Для каждого класса, использующегося вашей программой, существует свой объект Class.
 * То есть каждый раз при написании и последующей компиляции нового класса для него создается
 * объект Class (который затем сохраняется в одноименном файле с расширением .class).
 * Чтобы создать объект этого класса, виртуальная машина Java (JVM) использует подсистему,
 * называемую загрузчиком классов.
 * <p>
 * Один из способов получить ссылку на объект Class — вызвать метод forName(), передав ему
 * в качестве аргумента строку (String) с именем (следите за правильностью написания!)
 * определенного класса. Этот метод возвращает ссылку на объект типа Class.
 * <p>
 * Каждый раз, когда вы хотите использовать информацию о типе во время выполнения, сначала
 * необходимо получить ссылку на соответствующий объект Class. Для этого удобно
 * воспользоваться методом class.forName(), потому что для получения ссылки на Class объект
 * не нужен. Но если у вас уже имеется объект интересующего вас типа, для получения ссылки
 * на Class можно вызвать метод, являющийся частью корневого класса Object: getClass().
 * Метод возвращает ссылку на объект Class, представляющий фактический тип объекта.
 * <p>
 * Пример некоторых методов из класса Class.
 */

interface HasBatteries {}
interface Waterproof {}
interface Shoots {}

class Toy {
    // Закомментируйте следующий конструктор по умолчанию,
    // чтобы увидеть ошибку NoSuchMethodError ив (*1*)
    Toy() {}
    Toy(int i) {}
}

class FancyToy extends Toy implements HasBatteries, Waterproof, Shoots {
    FancyToy() { super(1); }
}

public class ToyTest {
    static void printInfo(Class cc) {
        System.out.println("Имя класса: " + cc.getName() +
                " является интерфейсом? [" + cc.isInterface() + "]");
        System.out.println("простое имя: " + cc.getSimpleName());
        System.out.println("Каноническое имя : " + cc.getCanonicalName());
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Class c = null;
        try {
            c = Class.forName("RTTI.FancyToy");
        } catch (ClassNotFoundException e) {
            System.out.println("He удается найти FancyToy");
            System.exit(1);
        }
        printInfo(c);
        for (Class face : c.getInterfaces()) printInfo(face);
        Class up = c.getSuperclass();
        Object obj = null;
        try {
            // Необходим конструктор по умолчанию:
            // Метод newlnstance() класса Class может использоваться для реализации
            // «виртуального конструктора», работающего по принципу: «Я не знаю точно,
            // к какому типу ты относишься, но все равно создай себя так, как положено».
            obj = up.newInstance();
        } catch (InstantiationException e) {
            System.out.println("He удалось создать экземпляр");
            System.exit(1);
        } catch (IllegalAccessException e) {
            System.out.println("Отказано в доступе");
            System.exit(1);
        }
        printInfo(obj.getClass());
    }
}
