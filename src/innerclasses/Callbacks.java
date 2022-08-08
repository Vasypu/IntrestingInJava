package innerclasses;

/**
 *  Замыкания и обратные вызовы.
 *
 *  Еще один пример различной реализации интерфейса. Класс Callee1 реализует
 *  интерфейс очевидным решением, класс Callee2 реализует это через внутренний класс.
 *
 *  В книге указано, что с помощью обратного вызова решение становится более гибким и
 *  безопасным.
 */

interface Incrementable {
    void increment();
}

class Callee1 implements Incrementable {
    private int i = 0;
    public void increment() {
        i++;
        System.out.println(i);
    }
}

class MyIncrement {
    public void increment() { System.out.println("Другая операция"); }
    public static void f(MyIncrement mi) { mi.increment(); }
}

class Callee2 extends MyIncrement {
    private int i = 0;
    public void increment() {
        super.increment();
        i++;
        System.out.println(i);
    }
    private class Closure implements Incrementable {      // замыкание предоставляет внутренний класс, реализует интерфейс
        public void increment() {                         // предоставляя при этом связь с объектом Callee2, но эта связь безопасна,
            Callee2.this.increment();                     // так как можно будет вызвать только метод increment()
        }
    }
    Incrementable getCallbackReference() {
        return new Closure();
    }
}

class Caller {
    private Incrementable callbackReference;
    Caller(Incrementable cbh) { callbackReference = cbh; }  // передается ссылка на Incrementable, для
    void go() { callbackReference.increment(); }            // обратного вызова объекта Callee
}

public class Callbacks {
    public static void main(String[] args) {
        Callee1 c1 = new Callee1();
        Callee2 c2 = new Callee2();
        MyIncrement.f(c2);
        Caller caller1 = new Caller(c1);
        Caller caller2 = new Caller(c2.getCallbackReference());
        caller1.go();
        caller1.go();
        caller2.go();
        caller2.go();
    }
}
