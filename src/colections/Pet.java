package colections;

public class Pet {
    String name;
    int id = 0;
    public Pet(String name) {
        this.name = name;
        id++;
    }
    public Pet() { id++; }
    public int id() { return id; }
}

class Dog extends Pet {
    public Dog(String name) { super(name);}
    public Dog() { super(); }
    public String toString() { return "Dog " + name; }
}

class Mutt extends Dog {
    public Mutt(String name) { super(name);}
    public Mutt() { super(); }
    public String toString() { return "Mutt " + name; }
}

class Pug extends Dog {
    public Pug(String name) { super(name);}
    public Pug() { super(); }
    public String toString() { return "Pug " + name; }
}

class Cat extends Pet {
    public Cat(String name) { super(name);}
    public Cat() { super(); }
    public String toString() { return "Cat " + name; }
}

class EgyptianMau extends Cat {
    public EgyptianMau(String name) { super(name);}
    public EgyptianMau() { super(); }
    public String toString() { return "EgyptianMau"; }
}

class Manx extends Cat {
    public Manx(String name) { super(name);}
    public Manx() { super(); }
    public String toString() { return "Manx"; }
}

class Cymric extends Manx {
    public Cymric(String name) { super(name);}
    public Cymric() { super(); }
    public String toString() { return "Cymric " + name; }
}

class Rodent extends Pet {
    public Rodent(String name) { super(name);}
    public Rodent() { super(); }
    public String toString() { return "Rodent"; }
}

class Rat extends Rodent {
    public Rat(String name) { super(name);}
    public Rat() { super(); }
    public String toString() { return "Rat " + name; }
}

class Mouse extends Rodent {
    public Mouse(String name) { super(name);}
    public Mouse() { super(); }
    public String toString() { return "Mouse"; }
}

class Hamster extends Rodent {
    public Hamster(String name) { super(name);}
    public Hamster() { super(); }
    public String toString() { return "Humster " + name; }
}