package generics;

import java.lang.reflect.Method;

/**
 *  Компенсация отсутствия латентной типизации
 *  <p>
 *  Хотя в Java латентная типизация не поддерживается, это не значит, что ограниченный обобщенный код не может
 *  применяться между разными иерархиями типов.
 *  <p>
 *  Рефлексия
 *  <p>
 *  Первый способ основан на использовании рефлексии. Вот как выглядит метод perform(), использующий латентную
 *  типизацию. Здесь классы полностью разъединены и не имеют общих базовых классов (кроме Object) или интерфейсов.
 *  Используя отражение, метод CommunicateReflectively.perform() может динамически проверить доступность нужных
 *  методов и вызвать их. Он даже может обработать ситуацию, в которой Mime содержит только один из необходимых
 *  методов, и частично решает свою задачу.
 */
class Mime {
    public void walkAgainstTheWind() {}
    public void sit() { System.out.println("Pretending to sit"); }
    public void pushInvisibleWalls() {}
    public String toString() { return "Mime"; }
}

// Не реализует Performs:
class SmartDog {
    public void speak() { System.out.println("Woof!"); }
    public void sit() { System.out.println("Sitting"); }
    public void reproduce() {}
}

class CommunicateReflectively {
    public static void perform(Object speaker) {
        Class<?> spkr = speaker.getClass();
        try {
            try {
                Method speak = spkr.getMethod("speak");
                speak.invoke(speaker);
            } catch(NoSuchMethodException e) {
                System.out.println(speaker + " cannot speak");
            }
            try {
                Method sit = spkr.getMethod("sit");
                sit.invoke(speaker);
            } catch (NoSuchMethodException e) {
                System.out.println(speaker + " cannot sit");
            }
        } catch(Exception e) {
            throw new RuntimeException(speaker.toString(), e);
        }
    }
}

public class LatentReflection {
    public static void main(String[] args) {
        CommunicateReflectively.perform(new SmartDog());
        CommunicateReflectively.perform(new Robot());
        CommunicateReflectively.perform(new Mime());
    }
}
