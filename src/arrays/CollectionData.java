package arrays;

import containers.Generator;
import java.util.ArrayList;

// Практически все подтипы Collection имеют конструктор, который получает другой объект Collection,
// используемый для заполнения нового контейнера. Таким образом, для простого создания тестовых данных
// достаточно построить класс с конструктором, в аргументах которого передается объект Generator и
// количество объектов quantity:
public class CollectionData<T> extends ArrayList<T> {
    public CollectionData(Generator<T> gen, int quantity) {
        for(int i = 0; i < quantity; i++)
            add(gen.next());
        }
    // Обобщенный вспомогательный метод:
    public static <T> CollectionData<T> list(Generator<T> gen, int quantity) {
        return new CollectionData<T>(gen, quantity);
    }
}