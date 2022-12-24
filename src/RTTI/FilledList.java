package RTTI;

/**
 *  Для ослабления ограничений при использовании параметризованных ссылок на Class применяют метасимвол ?,
 *  который является частью обобщенных типов java и означает «что угодно».
 *  Чтобы создать ссылку на Class, ограниченную типом или любыми его подтипами, метасимвол ? следует
 *  объединить с ключевым словом extends для создания привязки.
 *  Class<? extends Number> любой класс, производный от Number.
 *  <p>
 *  При использовании синтаксиса параметризации для объектов Class происходит нечто интересное:
 *  метод newlnstance() возвращает точный тип объекта вместо базового типа Object, как в примере ToyTest.java.
 *  <p>
 *  При получении объекта суперкласса компилятор только позволит сказать, что ссылка на суперкласс представляет
 *  «некий класс, являющийся суперклассом FancyToy» (выражение Class<? super FancyToy>). Он не примет объявление
 *  Class<Toy>. На первый взгляд это странно, потому что getSuperclass() возвращает базовый класс (не интерфейс),
 *  а компилятору этот класс известен во время компиляции — в данном случае Toy.class, а не «какой-то суперкласс
 *  FancyToy».
 */

import java.util.ArrayList;
import java.util.List;

class CountedInteger {
    private static long counter;
    private final long id = counter++;
    public String toString() { return Long.toString(id); }
}

// Этот класс предполагает, что любой тип, с которым он работает, имеет конструктор по умолчанию
// (вызываемый без аргументов); если это условие нарушится, возникает исключение. Компилятор не
// выдает никаких предупреждений в этой программе.
public class FilledList<T> {
    private Class<T> type;
    public FilledList(Class<T> type) { this.type = type; }

    public List<T> create(int nElements) {
        List<T> result = new ArrayList<>();
        try {
            for (int i = 0; i < nElements; i++)
                result.add(type.newInstance());
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        FilledList<CountedInteger> fl = new FilledList<CountedInteger>(CountedInteger.class);
        System.out.println(fl.create(15));

        Class<FancyToy> ftClass = FancyToy.class;
        // Создает точный тип:
        FancyToy fancyToy = ftClass.newInstance();
        Class<? super FancyToy> up = ftClass.getSuperclass();
        // Не компилируется:
//        Class<Toy> up2 = ftClass.getSuperclass();
        // Создает только Object:
        Object obj = up.newInstance();
    }
}

// Новый синтаксис приведения типа
class Building {}
class House extends Building {}

class ClassCasts {
    public static void main(String[] args) {
        Building b = new House();
        Class<House> houseType = House.class;
        House h = houseType.cast(b);
        h = (House) b; // ... или так.
    }
}
