package polymorphism;

class Instrument {
    public void play(String s) {
        System.out.println("Instrument play()");
    }
}

class Wind extends Instrument {
    @Override
    public void play(String s) {
        System.out.println("Wind play() " + s);
    }
}

public class Music {
    public static void tune(Instrument i) {
        i.play("Rock");
    }

    public static void main(String[] args) {
        Wind flute = new Wind();
        tune(flute);
    }
}
