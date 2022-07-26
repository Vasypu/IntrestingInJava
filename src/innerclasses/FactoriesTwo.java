package innerclasses;

/**
 *  Фабрика с анонимными классами.
 *
 *  Конструкторы Implementation1 и Implementation2 теперь можно сделать закрытыми, а
 *  фабрику необязательно оформлять в виде класса.
 *  Пример GameFactory так же был упрощен с помощью анонимных классов.
 */

interface Service {
    void method1();
    void method2();
}

interface ServiceFactory {
    Service getService();
}

class Implementation1 implements Service {
    private Implementation1() { } // доступ в пределах пакета
    public void method1() { System.out.println("Implementation1 method1"); }
    public void method2() { System.out.println("Implementation1 method2"); }
    public static ServiceFactory factory = new ServiceFactory() {
        public Service getService() {
            return new Implementation1();
        }
    };
}

class Implementation2 implements Service {
    private Implementation2() { } // доступ в пределах пакета
    public void method1() { System.out.println("Implementation2 method1"); }
    public void method2() { System.out.println("Implementation2 method2"); }
    public static ServiceFactory factory = new ServiceFactory() {
        public Service getService() {
            return new Implementation2();
        }
    };
}

public class FactoriesTwo {
    public static void serviceConsumer(ServiceFactory fact) {
        Service s = fact.getService();
        s.method1();
        s.method2();
    }

    public static void main(String[] args) {
        serviceConsumer(Implementation1.factory);
        serviceConsumer(Implementation2.factory);
    }
}


interface Game {
    boolean move();
}

interface GameFactory {
    Game getGame();
}

class Checkers implements Game {
    private int moves = 0;
    private static final int MOVES = 3;

    public boolean move() {
        System.out.println("Checkers move " + moves);
        return ++moves != MOVES;
    }

    static GameFactory factory = new GameFactory() {
        public Game getGame() { return new Checkers(); }
    };
}

class Chess implements Game {
    private int moves = 0;
    private static final int MOVES = 4;

    public boolean move() {
        System.out.println("Chess move " + moves);
        return ++moves != MOVES;
    }

    static GameFactory factory = new GameFactory() {
        public Game getGame() { return new Chess(); }
    };
}

class Games {
    public static void playGame(GameFactory factory) {
        Game s = factory.getGame();
        while (s.move())
            continue;
    }

    public static void main(String[] args) {
        playGame(Chess.factory);
        playGame(Checkers.factory);
    }
}
