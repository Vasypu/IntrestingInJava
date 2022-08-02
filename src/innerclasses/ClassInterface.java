package innerclasses;

/**
 *  Классы внутри интерфейсов.
 *
 *  Вложенный класс может стать частью интерфейса, так же он становится автоматически
 *  public и static внутри интерфейса. Во внутреннем классе можно даже реализовать интерфейс.
 *
 *  При создании вложенного класса Tester компилятор создает отдельный файл
 *  с именем TestBed$Tester. Для запуска данного класса java innerclasses.TestBed$Tester
 */

public interface ClassInterface {
    void howdy();
    class Test implements ClassInterface {
        public void howdy() {
            System.out.println("Привет!");
        }
        public static void main(String[] args) {
            new Test().howdy();
        }
    }
}

class TestBed {
    public void f() { System.out.println("f()"); }
    public static class Tester {
        public static void main(String[] args) {
            TestBed t = new TestBed();
            t.f();
        }
    }
}
