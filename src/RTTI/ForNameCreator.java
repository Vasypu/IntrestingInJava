package RTTI;

/**
 * Метод loader() создает контейнер List с объектами Class, используя метод Class. forName(). При этом
 * может быть возбуждено исключение classNotFoundException, что вполне логично, так как передаваемая
 * строка не может проверяться во время компиляции. Так как объекты Pet находятся в пакете typeinfo,
 * при обращениях к классам должно указываться имя пакета.
 * <p>
 * Для создания типизованного контейнера List с объектами Class необходимо приведение типа, из-за
 * которого компилятор выдает предупреждение. Метод loader() определяется отдельно, а затем помещается
 * в блок статической инициализации, потому что аннотация @SuppressWarnings не может размещаться
 * непосредственно в блоке статической инициализации.
 */

import java.util.List;
import java.util.ArrayList;

public class ForNameCreator extends PetCreator {
    private static List<Class<? extends Pet>> types = new ArrayList<Class<? extends Pet>>();
    // Типы, которые должны создаваться случайным образом:
private static String[] typeNames = {
        "RTTI.Mutt",
        "RTTI.Pug",
        "RTTI.EgyptianMau",
        "RTTI.Manx",
        "RTTI.Cymric",
        "RTTI.Rat",
        "RTTI.Mouse",
        "RTTI.Hamster"
};
    @SuppressWarnings("unchecked")
    private static void loader() {
        try {
            for(String name : typeNames)
                types.add((Class<? extends Pet>) Class.forName(name));
        } catch(ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    static { loader(); }
    public List<Class<? extends Pet>> getTypes() {return types;}
}
