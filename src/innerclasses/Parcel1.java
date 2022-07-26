package innerclasses;

/**
 *  Внутренние классы.
 *
 *  Внутренние классы имеют доступ к полям, в том числе private. Так
 *  внутренний класс SequenceSelector имеет доступ к private массиву
 *  objects. Объект внутреннего класса можно создавать только в
 *  сочетании с объектом внешнего класса, если внутренний класс не
 *  является статическим.
 */

public class Parcel1 {
    class Contents {
        private int i = 11;
        public int value() { return i; }
    }
    class Destination {
        private String lable;
        Destination(String whereTo) {
            lable = whereTo;
        }
        String readLable() { return lable; }
    }

    public void ship(String dest) {
        Contents c = new Contents();
        Destination d = new Destination(dest);
        System.out.println(d.readLable());
    }
    public static void main(String[] args) {
        Parcel1 p = new Parcel1();
        p.ship("Тасмания");
        Parcel1.Contents contents = p.new Contents();  // создание объекта внутреннего класса
        System.out.println(contents.value());
    }
}

interface Selector {
    boolean end();
    Object current();
    void next();
}

class Sequence {
    private Object[] objects;
    private int next = 0;
    public Sequence(int size) { objects = new Object [size]; }
    public void add(Object x) {
        if (next < objects.length)
            objects[next++] = x;
    }
    private class SequenceSelector implements Selector {
        private int i = 0;
        public boolean end() { return i == objects.length; }
        public Object current() { return objects[i]; }
        public void next() { if (i < objects.length) i++; }
    }
    public Selector getSelector() { return new SequenceSelector(); }

    public static void main(String[] args) {
        Sequence sequence = new Sequence(10);
        for (int i = 0; i < 10; i++)
            sequence.add(Integer.toString(i));
        Selector selector = sequence.getSelector();
        while (!selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }
    }
}