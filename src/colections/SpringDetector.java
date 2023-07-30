package colections;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *  Хеширование и хеш-коды
 *  <p>
 *  Часто ошибки происходят, когда разработчик сам пишет класс, который должен стать ключом HashMap,
 *  и забывает реализовать весь необходимый служебный код.
 *  Проблема состоит в том, что класс Groundhog унаследован от общего корневого класса Object, а для генерирования
 *  хеш-кода объекта используется реализация hashCode() этого класса. По умолчанию этот метод возвращает адрес
 *  объекта. Таким образом, хеш-код первого экземпляра Groundhog(3) не совпадает с хеш-кодом второго экземпляра
 *  Groundhog(3), который применялся в качестве ключа при поиске.
 *  Для решения проблемы необходимо переопределить метод hashCode() и метод equals(), который также является частью
 *  класса Object. Этот метод используется картой HashMap для проверки того, равен ли ваш ключ какому-либо ключу из
 *  содержащихся в ней.
 */
class Groundhog {
    protected int number;
    public Groundhog(int n) { number = n; }
    public String toString() { return "Groundhog #" + number; }
}

class Prediction {
    private static Random rand = new Random(47);
    private boolean shadow = rand.nextDouble() > 0.5;
    public String toString() {
        if (shadow)
            return "Six more weeks of Winter!";
        else
            return "Early Spring!";
    }
}

public class SpringDetector {
    // Используем Groundhog или класс, производный от него:
    public static <T extends Groundhog> void detectSpring(Class<T> type) throws Exception {
        Constructor<T> ghog = type.getConstructor(int.class);
        Map<Groundhog,Prediction> map = new HashMap<Groundhog,Prediction>();
        for(int i = 0; i < 10; i++)
            map.put(ghog.newInstance(i), new Prediction());
        System.out.println("map = " + map);
        Groundhog gh = ghog.newInstance(3);     // данный ключ не найден в map
        System.out.println("Looking up prediction for " + gh);
        if(map.containsKey(gh))
            System.out.println(map.get(gh));
        else
            System.out.println("Key not found: " + gh);
    }

    public static void main(String[] args) throws Exception {
        detectSpring(Groundhog.class);
    }
}

// переопределение методов hashCode() и equals()
class Groundhog2 extends Groundhog {
    public Groundhog2(int n) { super(n); }

    public int hashCode() { return number; }

    public boolean equals(Object o) {
        return o instanceof Groundhog2 && (number == ((Groundhog2) o).number);
    }
}

class SpringDetector2 {
    public static void main(String[] args) throws Exception {
        SpringDetector.detectSpring(Groundhog2.class);
    }
}
