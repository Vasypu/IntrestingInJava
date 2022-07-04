package interfaces;

import java.util.Arrays;

/**
 * Для того чтобы использовать FilterTwo в методе Apply_2.process() сделаем ProcessorTwo интерфейсом.
 * Для работы прошлого кода на участке №1 создадим абстракный класс StringProcessor с реализацией
 * интерфейса и наследуемся от него. Для работы класса FilterTwo в методе Apply_2.process()
 * создадим адаптер FilterAdapter который реализует интерфейс ProcessorTwo. Таким образом отделив интерфейс
 * от реализации, повявилась возможность использовать его с разными реализациями, а следовательно появилась
 * возможность повторного использования кода.
 */

interface ProcessorTwo {
    public String name();
    Object process(Object input);
}

public class Apply_2 {
    public static void process(ProcessorTwo p, Object s) {
        System.out.println("Используем Processor " + p.name());
        System.out.println(p.process(s));
    }
}

// участок №1
abstract class StringProcessor implements ProcessorTwo {
    public String name() { return getClass().getSimpleName(); }
    public abstract String process(Object input);
    public static String s = "If she weighs the same as a duck, she's made of wood";

    public static void main(String[] args) {
        Apply_2.process(new UpcaseTwo(), s);
        Apply_2.process(new DowncaseTwo(), s);
        Apply_2.process(new SplitterTwo(), s);
    }
}

class UpcaseTwo extends StringProcessor {
    public String process(Object input) { return ((String)input).toUpperCase(); }
}

class DowncaseTwo extends StringProcessor {
    public String process(Object input) { return ((String)input).toLowerCase(); }
}

class SplitterTwo extends StringProcessor {
    public String process(Object input) { return Arrays.toString(((String)input).split(" ")); }
}
// участок №1

class FilterAdapter implements ProcessorTwo {
    FilterTwo filterTwo;
    public FilterAdapter(FilterTwo filterTwo) {
        this.filterTwo = filterTwo;
    }
    public String name() { return filterTwo.name(); }
    // допустим возвращаемый тип WaveformTwo, так как наследуется от Object в интерфейсе.
    public WaveformTwo process(Object input) { return filterTwo.process((WaveformTwo) input); }
}

class FilterProcessor {
    public static void main(String[] args) {
        WaveformTwo w = new WaveformTwo();
        Apply_2.process(new FilterAdapter(new LowPassTwo(1.0)), w);
        Apply_2.process(new FilterAdapter(new HighPassTwo(2.0)), w);
        Apply_2.process(new FilterAdapter(new BandPassTwo(3.0, 4.0)), w);
    }
}

class WaveformTwo {
    private static long counter;
    private final long id = counter++;
    public String toString() { return "Waveform " + id; }
}

class FilterTwo {
    public String name() {
        return getClass().getSimpleName();
    }
    public WaveformTwo process(WaveformTwo input) { return input; }
}

class LowPassTwo extends FilterTwo {
    double cutoff;
    public LowPassTwo(double cutoff) { this.cutoff = cutoff; }
    public WaveformTwo process(WaveformTwo input) { return input; }
}

class HighPassTwo extends FilterTwo {
    double cutoff;
    public HighPassTwo(double cutoff) { this.cutoff = cutoff; }
    public WaveformTwo process(WaveformTwo input) { return input; }
}

class BandPassTwo extends FilterTwo {
    double lowCutoff, highCutoff;
    BandPassTwo(double lowCut, double highCut) {
        lowCutoff = lowCut;
        highCutoff = highCut;
    }
    public WaveformTwo process(WaveformTwo input) { return input; }
}