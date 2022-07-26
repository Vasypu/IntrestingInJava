package innerclasses;

/**
 *  Восходящее преобразование внутренних классов.
 *
 *  С помощью внутренних классов можно создавать закрытую от внешних классов реализацию интерфейсов.
 *  Классы PContents и PDestination реализуют интерфейсы, так как они являются private и protected
 *  реализацию интерфейсов нельзя изменить.
 */

public interface Destination {
    String readLable();
}

interface Contents {
    int value();
}

class Parcel4 {
    private class PContents implements Contents {
        private int i = 11;
        public int value() { return i; }
    }
    protected class PDestination implements Destination {
        private String lable;
        private PDestination(String whereTo) {
            lable = whereTo;
        }
        public String readLable() { return lable; }
    }
    public Destination destination(String s) {
        return new PDestination(s);
    }
    public Contents contents() {
        return new PContents();
    }
}

class TestParcel {
    public static void main(String[] args) {
        Parcel4 p = new Parcel4();
        Contents c = p.contents();
        Destination d = p.destination("Тасмания");
        System.out.println(d.readLable() + " " + c.value());
//      не можем создать так как у нас private конструктор
//        Parcel4.PDestination pd = p.new PDestination();
//      Нет доступа к private классу
//        Parcel4.PContents pc = p.new PContents();
    }
}
