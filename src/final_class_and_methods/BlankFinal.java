package final_class_and_methods;

// Пустые константы, поле final в классе может быть разным для каждого объекта,
// и при этом оно сохраняет свою неизменность
public class BlankFinal {
    private final int i = 0;
    private final int j;
    private final Poppet p;

    BlankFinal() {
        j = 1;
        p = new Poppet(1);
    }

    BlankFinal(int x) {
        j = x;
        p = new Poppet(x);
    }

    public static void main(String[] args) {
        new BlankFinal();
        new BlankFinal(47);
    }
}

class Poppet {
    private int i;
    Poppet(int i) { this.i = i; }
}
