package interfaces;

/**
 *  Интерфейсы и фабрики.
 *
 *  Вместо того чтобы вызывать конструктор напрямую, вы вызываете метод объекта-фабрики,
 *  который предоставляет реализацию интерфейса - в этом случае программный код
 *  теоретически отделяется от реализации интерфейса, благодаря чему становится возможной
 *  совершенно прозрачная замена реализации.
 */

interface Service {
    void method1();
    void method2();
}

interface ServiceFactory {
    Service getService();
}

class Implementation1 implements Service {
    Implementation1() {} // доступ в пределах пакета
    public void method1() { System.out.println("Implementation1 method1"); }
    public void method2() { System.out.println("Implementation1 method2"); }
}

class Implementation1Factory implements ServiceFactory {
    public Service getService() { return new Implementation1(); }
}

class Implementation2 implements Service {
    Implementation2() {} // доступ в пределах пакета
    public void method1() { System.out.println("Implementation2 method1"); }
    public void method2() { System.out.println("Implementation2 method2"); }
}

class Implementation2Factory implements ServiceFactory {
    public Service getService() { return new Implementation2(); }
}

public class Factories {
    public static void serviceConsumer(ServiceFactory fact) {
        Service s = fact.getService();
        s.method1();
        s.method2();
    }
    public static void main(String[] args) {
        serviceConsumer(new Implementation1Factory());

        serviceConsumer(new Implementation2Factory());
    }
}

interface Game { boolean move(); }
interface GameFactory { Game getGame(); }

class Checkers implements Game {
    private int moves = 0;
    private static final int MOVES = 3;
    public boolean move() {
        System.out.println("Checkers move " + moves);
        return ++moves != MOVES;
    }
}

class CheckersFactory implements GameFactory {
    public Game getGame() { return new Checkers(); }
}

class Chess implements Game {
    private int moves = 0;
    private static final int MOVES = 4;
    public boolean move() {
        System.out.println("Chess move " + moves);
        return ++moves != MOVES;
    }
}

class ChessFactory implements GameFactory {
    public Game getGame() { return new Chess(); }
}

class Games {
    public static void playGame(GameFactory factory) {
        Game s = factory.getGame();
        while (s.move())
            continue;
    }
    public static void main(String[] args) {
        playGame(new CheckersFactory());
        playGame(new ChessFactory());
    }
}