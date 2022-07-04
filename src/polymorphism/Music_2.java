package polymorphism;

/**
 * В сравнении с классом Music из этого же пакета мы смогли добавить еще методов
 * и реализующих классов, при этом не затронули прошлую реализацию.
 */

class InstrumentTwo {
    void play(String s) { System.out.println("InstrumentTwo play()" + s); }
    String what() { return "InstrumentTwo"; }
    void adjust() { System.out.println("Adjusting InstrumentTwo"); }
}

class WindTwo extends InstrumentTwo {
    void play(String s) { System.out.println("WindTwo play()" + s); }
    String what() { return "WindTwo"; }
    void adjust() { System.out.println("Adjusting WindTwo"); }
}

class Percussion extends InstrumentTwo {
    void play(String s) { System.out.println("Percussion play()" + s); }
    String what() { return "Percussion"; }
    void adjust() { System.out.println("Adjusting Percussion"); }
}

class Stringed extends InstrumentTwo {
    void play(String s) { System.out.println("Stringed play()" + s); }
    String what() { return "Stringed"; }
    void adjust() { System.out.println("Adjusting Stringed"); }
}

class Brass extends WindTwo {
    void play(String s) { System.out.println("Brass play()" + s); }
    void adjust() { System.out.println("Adjusting Brass"); }
}

class Woodwind extends WindTwo {
    void play(String s) { System.out.println("Woodwind play()" + s); }
    void adjust() { System.out.println("Adjusting Woodwind"); }
}

public class Music_2 {
    public static void tune(InstrumentTwo i) {
        i.play("Rock");
    }
    public static void tuneAll(InstrumentTwo[] e) {
        for (InstrumentTwo i : e)
            tune(i);
    }
    public static void main(String[] args) {
        InstrumentTwo[] orchestra = {
                new WindTwo(),
                new Percussion(),
                new Stringed(),
                new Brass(),
                new Woodwind()
        };
        tuneAll(orchestra);
    }
}