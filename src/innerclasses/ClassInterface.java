package innerclasses;

/**
 *  Классы внутри интерфейсов.
 *
 *
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
