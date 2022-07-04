package interfaces;

/**
 * В сравнении с прошлым примером Music2 мы сделали класс Instrument абстрактным,
 * так же сделали метод play() абстрактным. Сделали это так как в нашей
 * программе не требуются экземпляры класса Instrument.
 */

abstract class Instrument {
    private int i;
    abstract void play(String s);
    String what() { return "Instrument"; }
    void adjust() { System.out.println("Adjusting Instrument"); }
}

class Wind extends Instrument {
    void play(String s) { System.out.println("Wind play()" + s); }
    String what() { return "Wind"; }
    void adjust() { System.out.println("Adjusting Wind"); }
}

class Percussion extends Instrument {
    void play(String s) { System.out.println("Percussion play()" + s); }
    String what() { return "Percussion"; }
    void adjust() { System.out.println("Adjusting Percussion"); }
}

class Stringed extends Instrument {
    void play(String s) { System.out.println("Stringed play()" + s); }
    String what() { return "Stringed"; }
    void adjust() { System.out.println("Adjusting Stringed"); }
}

class Brass extends Wind {
    void play(String s) { System.out.println("Brass play()" + s); }
    void adjust() { System.out.println("Adjusting Brass"); }
}

class Woodwind extends Wind {
    void play(String s) { System.out.println("Woodwind play()" + s); }
    void adjust() { System.out.println("Adjusting Woodwind"); }
}

public class Music_3 {
    static void tune(InstrumentTwo i) {
        i.play("Rock");
    }
    public static void tuneAll(InstrumentTwo[] e) {
        for (InstrumentTwo i : e)
            tune(i);
    }
    public static void main(String[] args) {
        InstrumentTwo[] orchestra = {
                new WindTwo(),
                new PercussionTwo(),
                new StringedTwo(),
                new BrassTwo(),
                new WoodwindTwo()
        };
        tuneAll(orchestra);
    }
}
