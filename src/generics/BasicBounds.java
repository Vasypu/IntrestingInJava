package generics;

import java.util.List;

/**
 *  Ограничения
 *  <p>
 *  Ограничения сужают диапазон параметров-типов, которые могут использоваться с обобщениями. Так как
 *  стирание удаляет информацию типа, для неограничиваемых параметров обобщений можно вызывать только
 *  методы, доступные для Object. Но при ограничении параметра подмножеством типов вы сможете вызывать
 *  методы этого подмножества. Для установления этого ограничения обобщения Java используют ключевое
 *  слово extends. Важно понимать, что в контексте обобщений extends имеет совершенно иной смысл, чем обычно.
 */

interface HasColor { java.awt.Color getColor(); }

class Colored<T extends HasColor> {
    T item;
    Colored(T item) { this.item = item; }
    T getItem() { return item; }
    // Ограничение производит стирание до HasColor и позволяет вызвать метод:
    java.awt.Color color() { return item.getColor(); }
}

class Dimension { public int x, y, z; }
// Не работает -- сначала должен быть указан класс, затем интерфейсы:
// class ColoredDimension<T extends HasColor & Dimension> {

// Множественные ограничения:
class ColoredDimension<T extends Dimension & HasColor> {
    T item;
    ColoredDimension(T item) { this.item = item; }
    T getItem() { return item; }
    java.awt.Color color() { return item.getColor(); }
    int getX() { return item.x; }
    int getY() { return item.y; }
    int getZ() { return item.z; }
}

interface Weight { int weight(); }
// Как и в случае с наследованием, можно указать только один
// конкретный класс, но несколько интерфейсов:
class Solid<T extends Dimension & HasColor & Weight> {
    T item;
    Solid(T item) { this.item = item; }
    T getItem() { return item; }
    java.awt.Color color() { return item.getColor(); }
    int getX() { return item.x; }
    int getY() { return item.y; }
    int getZ() { return item.z; }
    int weight() { return item.weight(); }
}

class Bounded extends Dimension implements HasColor, Weight {
    public java.awt.Color getColor() { return null; }
    public int weight() { return 0; }
}

public class BasicBounds {
    public static void main(String[] args) {
        Solid<Bounded> solid = new Solid<>(new Bounded());  // При создании объекта класса Solid в качестве параметра T
        solid.color();                                      // может использоваться класс, который реализует класс Dimension
        solid.getY();                                       // и интерфейсы HasColor, Weight
        solid.weight();
    }
}

// Пример BasicBounds.java содержит избыточность, которую можно устранить посредством наследования. В следующем
// примере видно, что каждый уровень наследования также добавляет ограничения. Holdltem просто хранит объект;
// это поведение наследуется классом Colored2, который также требует, чтобы его параметр реализовал HasColor.
// ColoredDimension2 и Solid2 расширяют иерархию и добавляют ограничения на каждом уровне. Теперь методы
// наследуются и их не нужно повторять для каждого класса.
class HoldItem<T> {
    T item;
    HoldItem(T item) { this.item = item; }
    T getItem() { return item; }
}

class Colored2<T extends HasColor> extends HoldItem<T> {
    Colored2(T item) { super(item); }
    java.awt.Color color () { return item.getColor(); }
}

class ColoredDimension2<T extends Dimension & HasColor> extends Colored2<T> {
    ColoredDimension2(T item) { super(item); }
    int getX() { return item.x; }
    int getY() { return item.y; }
    int getZ() { return item.z; }
}

class Solid2<T extends Dimension & HasColor & Weight> extends ColoredDimension2<T> {
    Solid2(T item) { super(item); }
    int weight() { return item.weight(); }
}

class InheritBounds {
    public static void main(String[] args) {
        Solid2<Bounded> solid2 = new Solid2<>(new Bounded());
        solid2.color();
        solid2.getY();
        solid2.weight();
    }
}

interface SuperPower {}
interface XRayVision extends SuperPower { void seeThroughWalls(); }
interface SuperHearing extends SuperPower { void hearSubtleNoises(); }
interface SuperSmell extends SuperPower { void trackBySmell(); }

class SuperHero<POWER extends SuperPower> {
    POWER power;
    SuperHero(POWER power) { this.power = power; }
    POWER getPower() { return power; }
}

class SuperSleuth<POWER extends XRayVision> extends SuperHero<POWER> {
    SuperSleuth(POWER power) { super(power); }
    void see() { power.seeThroughWalls(); }
}

class CanineHero<POWER extends SuperHearing & SuperSmell> extends SuperHero<POWER> {
    CanineHero(POWER power) { super(power); }
    void hear() { power.hearSubtleNoises(); }
    void smell() { power.trackBySmell(); }
}

class SuperHearSmell implements SuperHearing, SuperSmell {
    public void hearSubtleNoises() {}
    public void trackBySmell() {}
}

class DogBoy extends CanineHero<SuperHearSmell> {
    DogBoy() { super(new SuperHearSmell()); }
}

class EpicBattle {
// Ограничения в обобщенных методах:
    static <POWER extends SuperHearing>
    void useSuperHearing(SuperHero<POWER> hero) {
        hero.getPower().hearSubtleNoises();
}

    static <POWER extends SuperHearing & SuperSmell>
    void superFind(SuperHero<POWER> hero) {
        hero.getPower().hearSubtleNoises();
        hero.getPower().trackBySmell();
    }

    public static void main(String[] args) {
        DogBoy dogBoy = new DogBoy();
        useSuperHearing(dogBoy);
        superFind(dogBoy);
        // Так поступить можно:
        List<? extends SuperHearing> audioBoys;
        // А так нельзя:
        // List<? extends SuperHearing & SuperSmell> dogBoys;
    }
}