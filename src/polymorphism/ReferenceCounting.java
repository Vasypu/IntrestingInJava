package polymorphism;

/**
 * Так как встроенный объект класса Shared используется совместно с другими объектами класса Composing,
 * используется подсчет ссылок. Метод dispose внутри класса Shared вызывается для своей собственной ссылки.
 */

class Shared {
    private int refcount = 0;
    private static long counter = 0;
    private final long id = counter++;

    public Shared() {
        System.out.println("Создаем " + this);
    }

    public void addRef() {
        refcount++;
    }

    protected void dispose() {
        if (--refcount == 0)
            System.out.println("Disposing " + this);
    }

    public String toString() {
        return "Shared " + id;
    }
}

class Composing {
    private Shared shared;
    private static long counter = 0;
    private final long id = counter++;

    public Composing(Shared shared) {
        System.out.println("Создаем " + this);
        this.shared = shared;
        this.shared.addRef();
    }

    protected void dispose() {
        System.out.println("disposing " + this);
        shared.dispose();
    }

    public String toString() {
        return "Composing " + id;
    }
}

public class ReferenceCounting {
    public static void main(String[] args) {
        Shared shared = new Shared();
        Composing[] composing = {new Composing(shared),
                new Composing(shared), new Composing(shared),
                new Composing(shared), new Composing(shared)};
        for (Composing c : composing)
            c.dispose();
    }
}

/**
 * Наследование и завершающее действие. Видно что все части Frog будут финализированы в порядке,
 * противоположном очередности их создания. Объект Frog является владельцем встроенных объектов,
 * он создает их, оперделяет продолжительность существования и знает, когда вызвать dispose() для
 * встроенных объектов.
 */
class Characteristic {
    private String s;

    Characteristic(String s) {
        this.s = s;
        System.out.println("Создаем Characteristic " + s);
    }

    protected void dispose() {
        System.out.println("Завершаем Characteristic " + s);
    }
}

class Description {
    private String s;

    Description(String s) {
        this.s = s;
        System.out.println("Создаем Description " + s);
    }

    protected void dispose() {
        System.out.println("Завершаем Description " + s);
    }
}

class LivingCreature {
    private Characteristic p = new Characteristic("живое существо");
    private Description t = new Description("обычное живое существо");

    LivingCreature() {
        System.out.println("LivingCreature()");
    }

    protected void dispose() {
        System.out.println("dispose() в LivingCreature");
        t.dispose();
        p.dispose();
    }
}

class Animal extends LivingCreature {
    private Characteristic p = new Characteristic("имеет сердце");
    private Description t = new Description("животное, не растение");

    Animal() {
        System.out.println("Animal()");
    }

    protected void dispose() {
        System.out.println("dispose() в Animal ");
        t.dispose();
        p.dispose();
        super.dispose();
    }
}

class Amphibian extends Animal {
    private Characteristic p = new Characteristic("может жить в воде");
    private Description t = new Description("и в воде, и на земле");

    Amphibian() {
        System.out.println("Amphibian()");
    }

    protected void dispose() {
        System.out.println("dispose() в Amphibian ");
        t.dispose();
        p.dispose();
        super.dispose();
    }
}

class Frog extends Animal {
    private Characteristic p = new Characteristic("квакает");
    private Description t = new Description("ест жуков");

    Frog() {
        System.out.println("Frog()");
    }

    protected void dispose() {
        System.out.println("dispose() в Frog ");
        t.dispose();
        p.dispose();
        super.dispose();
    }

    public static void main(String[] args) {
        Frog frog = new Frog();
        System.out.println("Пока!");
        frog.dispose();
    }
}
