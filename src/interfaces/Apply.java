package interfaces;

import java.util.Arrays;

/**
 *  Имеется базовый класс Processor с интерфейсными методами, у которо есть дочерние классы со своей реализацией.
 *  Так же в этом файле имеется класс Filter у которого те же интерфейсные элементы, что и у Processor.
 *  Но по скольку он не является производным от Processor, он не может использоваться с методом Apply.process().
 *  Эта проблема была решена в следующем файле Apply_2.
 */

class Processor {
    public String name() { return getClass().getSimpleName(); }
    Object process(Object input) { return input; }
}

class Upcase extends Processor {
    String process(Object input) { return ((String)input).toUpperCase(); }
}

class Downcase extends Processor {
    String process(Object input) { return ((String)input).toLowerCase(); }
}

class Splitter extends Processor {
    String process(Object input) { return Arrays.toString(((String)input).split(" "));
    }
}

public class Apply {
    public static void process(Processor p, Object s) {
        System.out.println("Используем Processor " + p.name());
        System.out.println(p.process(s));
    }
    public static String s = "Disagreement with beliefs is by definition incorrect";
    public static void main(String[] args) {
        process(new Upcase(), s);
        process(new Downcase(), s);
        process(new Splitter(), s);
    }
}

class Waveform {
    private static long counter;
    private final long id = counter++;
    public String toString() { return "Waveform " + id; }
}

class Filter {
    public String name() { return getClass().getSimpleName(); }
    public Waveform process(Waveform input) { return input; }
}

class LowPass extends Filter {
    double cutoff;
    public LowPass(double cutoff) { this.cutoff = cutoff; }
    public Waveform process(Waveform input) { return input; }
}

class HighPass extends Filter {
    double cutoff;
    public HighPass(double cutoff) { this.cutoff = cutoff; }
    public Waveform process(Waveform input) { return input; }
}

class BandPass extends Filter {
    double lowCutoff, highCutoff;
    BandPass(double lowCut, double highCut) {
        lowCutoff = lowCut;
        highCutoff = highCut;
    }
    public Waveform process(Waveform input) { return input; }
}