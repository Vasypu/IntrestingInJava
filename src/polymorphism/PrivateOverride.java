package polymorphism;

/**
 * private метод не может быть перегружен так как он не виден за пределами класса.
 * И так как было выполнено восходящее преобразование, вызовется метод из базового класса.
 * Если мы попытаемся вызвать private метод за перделами базового класса, то будет вызываться
 * метод из производного класса.
 */
public class PrivateOverride {
    private void f() { System.out.println("private f()"); }
    public static void main(String[] args) {
        PrivateOverride po = new Derived();
        po.f();
//        Derived der = new Derived();
//        PrivateOverride po2 = der;
//        po2.f();
    }
}

class Derived extends PrivateOverride {
    public void f() { System.out.println("public f()"); }
}

class Some {
    public static void main(String[] args) {
        PrivateOverride po = new Derived();
//        po.f();
        Derived der = new Derived();
        PrivateOverride po2 = der;
//        po2.f();
    }
}