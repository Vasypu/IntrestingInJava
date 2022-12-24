package RTTI;

/**
 * BJava есть еще один способ получить ссылку на объект Class — посредством литерала class.
 * В рассмотренной программе мы бы получили ее таким образом: Gum.class;
 * Это не только проще, но еще и безопаснее, поскольку проверка осуществляется еще во время
 * компиляции. К тому же не нужно вызывать метод, а значит, такой подход более эффективен.
 * <p>
 * Если переписать PetCreator с использованием литералов class, программа получается более компактной.
 */

import java.util.*;

public class LiteralPetCreator extends PetCreator {
// Блок try не нужен.
@SuppressWarnings("unchecked")
public static final List<Class<? extends Pet>> allTypes =
        Collections.unmodifiableList(Arrays.asList(
                Pet.class, Dog.class, Cat.class, Rodent.class,
                Mutt.class, Pug.class, EgyptianMau.class, Manx.class,
                Cymric.class, Rat.class, Mouse.class,Hamster.class));

// Типы, которые должны создаваться случайным образом:
private static final List<Class<? extends Pet>> types = allTypes.subList(allTypes.indexOf(Mutt.class), allTypes.size());
    public List<Class<? extends Pet>> getTypes() { return types; }

    public static void main(String[] args) {
        System.out.println(types);
    }
}

// Теперь у нас содержится две реализации PetCreator. Чтобы вторая реализация использовалась
// по умолчанию, мы можем предоставить реализацию паттерна Фасад, использующую LiteralPetCreator:
class Pets {
    public static final PetCreator creator = new LiteralPetCreator();
    public static Pet randomPet() { return creator.randomPet(); }
    public static Pet[] createArray(int size) { return creator.createArray(size); }
    public static ArrayList<Pet> arrayList(int size) { return creator.arrayList(size); }
}
