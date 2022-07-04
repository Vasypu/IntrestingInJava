package polymorphism;

class Grain {
    public String toString() { return "Grain"; }
}

class Wheat extends Grain {
    public String toString() { return "Wheat"; }
}

class Mill {
    Grain process() { return new Grain(); }
}

class WheatMill extends Mill {
    Wheat process() { return new Wheat(); }
}

public class CovariantReturn {
    public static void main(String[] args) {
        Mill m = new Mill();
        Grain g = m.process();  // m.process() возвращает объект Grain
        System.out.println(g);
        m = new WheatMill();    // восходящее преобразование, теперь Mill m ссылается на объект WheatMill
        g = m.process();        // при вызове m.process() вернется экземпляр класса Wheat, опять происходит восходящее преобразование
        System.out.println(g);
    }
}
