package interfaces;

/**
 * В сравнении с прошлым примером Music3 мы сделали Instrument интерфейсом.
 * У интерфейса могут присутствовать поля, так поле VALUE автоматически
 * является static и final. Методы интерфейса всегда public, даже если это
 * не указано явно, при реализации эти методы должны быть объявлены как
 * public.
 */

interface InstrumentTwo {
//  Константа времени компиляции.
    int VALUE = 5; // является static и final
//  Определение методов недопустимы
    void play(String s); // Автоматически объявлен как public
    void adjust();
}

class WindTwo implements InstrumentTwo {
    public void play(String s) { System.out.println("Wind play()" + s); }
    String what() { return "Wind"; }
    public void adjust() { System.out.println("Adjusting Wind"); }
}

class PercussionTwo implements InstrumentTwo {
    public void play(String s) { System.out.println("Percussion play()" + s); }
    String what() { return "Percussion"; }
    public void adjust() { System.out.println("Adjusting Percussion"); }
}

class StringedTwo implements InstrumentTwo {
    public void play(String s) { System.out.println("Stringed play()" + s); }
    String what() { return "Stringed"; }
    public void adjust() { System.out.println("Adjusting Stringed"); }
}

class BrassTwo extends WindTwo {
    public void play(String s) { System.out.println("Brass play()" + s); }
    public void adjust() { System.out.println("Adjusting Brass"); }
    public String toString() { return "BrassTwo"; }
}

class WoodwindTwo extends WindTwo {
    public void play(String s) { System.out.println("Woodwind play()" + s); }
    public void adjust() { System.out.println("Adjusting Woodwind"); }
    public String toString() { return "WoodwindTwo"; }
    void printMsg(String some) {
        System.out.println(some);
    }
}

public class Music_4 {
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
        WoodwindTwo woodwindTwo = new WoodwindTwo();
        woodwindTwo.printMsg("Пишу что-то");
    }
}
