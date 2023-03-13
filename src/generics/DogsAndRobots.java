package generics;

/**
 *  Латентная типизация
 *  <p>
 *  Язык с латентной типизацией ослабляет это ограничение (и порождает более универсальный код), требуя лишь
 *  реализации подмножества методов, а не конкретного класса или интерфейса. Таким образом, фрагмент кода может
 *  по сути означать: «Меня не интересует, к какому типу ты относишься, — главное, что ты можешь вызвать speak() и sit()».
 *  <p>
 *  Поскольку обобщения были добавлены B Java на поздней стадии, ни малейшей возможности реализовать
 *  латентную типизацию нё было, поэтому в Java данная возможность не поддерживается. В результате на первый
 *  взгляд кажется, что механизм обобщений Java «менее обобщен», чем в языках с поддержкой латентной
 *  типизации. Например, при попытке реализовать приведенный пример HaJava придется использовать класс
 *  или интерфейс и указать его в выражении ограничения.
 */

interface Performs {
    void speak();

    void sit();
}

class PerformingDog extends Dog implements Performs {
    public void speak() {
        System.out.println("Woof!");
    }

    public void sit() {
        System.out.println("Sitting");
    }

    public void reproduce() {
    }
}

class Robot implements Performs {
    public void speak() {
        System.out.println("Click!");
    }

    public void sit() {
        System.out.println("Clank!");
    }

    public void oilChange() {
    }
}

class Communicate {
    public static <T extends Performs> void perform(T performer) {
        performer.speak();
        performer.sit();
    }
}

public class DogsAndRobots {
    public static void main(String[] args) {
        PerformingDog d = new PerformingDog();
        Robot r = new Robot();
        Communicate.perform(d);
        Communicate.perform(r);
    }
}

// Однако следует заметить, что для работы perform() не обязательно использовать обобщения.
// Можно просто передать объект Performs
class CommunicateSimply {
    static void perform(Performs performer) {
        performer.speak();
        performer.sit();
    }
}

class SimpleDogsAndRobots {
    public static void main(String[] args) {
        CommunicateSimply.perform(new PerformingDog());
        CommunicateSimply.perform(new Robot());
    }
}