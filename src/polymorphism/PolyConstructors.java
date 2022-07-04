package polymorphism;

/**
 * Поведение полиморфных методов при вызове из конструктора. Метод Glyph.draw()
 * был предназначен для переопределения. Конструктор Glyph вызывает этот
 * метод, но вызывется метод RoundGlyph.draw(), в это время переменно radius еще
 * присвоено значение даже по умолчанию 1. Переменная равна 0. В итоге класс не выполнит
 * свою задачу. Рекомендация: единственные методы которые можно вызывать в конструкторе,
 * final и private, так как они остаются неизменными.
 */

class Glyph {
    void draw() { System.out.println("Glyph draw()"); }
    Glyph() {
        System.out.println("Glyph() перед вызовом draw()");
        draw();
        System.out.println("Glyph() после вызовом draw()");
    }
}

class RoundGlyph extends Glyph {
    private int radius = 1;
    RoundGlyph(int r) {
        radius = r;
        System.out.println("RoundGlyph RoundGlyph() radius = " + radius);
    }
    void draw() {
        System.out.println("RoundGlyph draw radius = " + radius);
    }
}

public class PolyConstructors {
    public static void main(String[] args) {
        new RoundGlyph(5);
    }
}
