package containers;

import arrays.CollectionData;
import arrays.RandomGenerator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *  Заполнение контейнеров с использованием Generator
 */
public class Government implements Generator<String> {
    String[] foundation = ("strange women lying in ponds " +
            "distributing swords is no basis for a system of " +
            "government").split(" ");
    private int index;
    public String next() { return foundation[index++]; }
}

// Объект Generator используется для включения в контейнер нужного количества объектов. Полученный контейнер
// может быть передан конструктору любого объекта Collection, и этот конструктор скопирует данные в самого себя.
// Метод addAll(), присутствующий в любом подтипе Collection, также может использоваться для заполнения существующих
// объектов Collection.
class CollectionDataTest {
    public static void main(String[] args) {
        Set<String> set = new LinkedHashSet<>(new CollectionData<String>(new Government(), 15));
        // Использование вспомогательного метода:
        set.addAll(CollectionData.list(new Government(), 15));
        System.out.println(set);
    }
}

class CollectionDataGeneration {
    public static void main(String[] args) {
        System.out.println(new ArrayList<>(CollectionData.list(// Convenience method
        new RandomGenerator.String(9), 10)));
        System.out.println(new HashSet<>(new CollectionData<>(new RandomGenerator.Integer(), 10)));
    }
}