package inheritance;

// Вызов конструкторов при проведени наследования.
public class Art {
    Art () { System.out.println("Конструктор Art"); }
    public String s;
}

class Drawing extends Art {
    Drawing () { System.out.println("Конструктор Drawing"); }
}

class Cartoon extends Drawing {
    public Cartoon () { System.out.println("Конструктор Cartoon"); }

    public static void main(String[] args) {
        Cartoon cartoon = new Cartoon();
    }
}

// Наследование, конструкторы и аргументы.
class Game {
    Game(int i) {
        System.out.println("Конструктор Game");
    }
}

class BoardGame extends Game {
    BoardGame(int i) {
        super(i);
        System.out.println("Конструктор BoardGame");
    }
}

class Chess extends BoardGame {
    Chess() {
        super(11);
        System.out.println("Конструктор Chess");
    }

    public static void main(String[] args) {
        Chess chess = new Chess();
    }
}
