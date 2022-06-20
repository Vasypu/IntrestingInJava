package initialization;

public class Soap {
    private String s;
    Soap() {
        System.out.println("Soap()");
        s = "Constructed";                               //Инициализация в конструкторе
    }
    @Override
    public String toString() { return s;};
}

class Bath {
    private String                                       //Инициализация в точке оперделения
    s1 = "Счастливый",
    s2 = "Счастливый",
    s3, s4;
    private Soap castille;
    private int i;
    private float toy;
    Bath() {
        System.out.println("В конструкторе Bath()");
        s3 = "Радостный";
        toy = 3.14f;
        castille = new Soap();
    }
    {i = 47;}                                           //Инициализация экземляра

    @Override
    public String toString() {
        if (s4 == null)
            s4 = "Радостный";                           //Отложенная инициализация
        return "s1 = " + s1 + "\n" +
               "s2 = " + s2 + "\n" +
               "s3 = " + s3 + "\n" +
               "s4 = " + s4 + "\n" +
               "castille = " + castille + "\n" +
               "i = " + i + "\n" +
               "toy = " + toy;
    }

    public static void main(String[] args) {
        Bath bath = new Bath();
        System.out.println(bath);
    }
}