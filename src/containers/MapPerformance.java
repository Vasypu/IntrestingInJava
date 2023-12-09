package containers;

import java.util.*;

/**
 *  Выбор между картами
 *  <p>
 *  Вставка для всех реализаций Мар (кроме IdentityHashMap) существенно замедляется с увеличением размера Мар.
 *  Однако в общем случае поиск требует значительно меньших затрат, чем вставка, — и это хорошо, потому что
 *  находить элементы в контейнере обычно требуется гораздо чаще, чем вставлять их.
 *  Производительность Hashtable примерно равна производительности HashMap. Впрочем, это и не удивительно — реализация
 *  HashMap должна заменить устаревший контейнер Hashtable, поэтому она использует те же механизмы хранения и поиска
 *  данных (о которых будет рассказано позднее).
 *  TreeMap показывает куда более скромные результаты. Как и TreeSet, она предназначена для создания упорядоченных
 *  списков. В древовидной структуре, положенной в основу TreeMap, элементы всегда хранятся в упорядоченном виде,
 *  а значит, их не нужно специально сортировать. После заполнения TreeMap можно использовать методы keySet() для
 *  получения множества (Set) ключей карты и toArray() для создания массива ключей. После этого статический метод
 *  Arrays. binarySearch () позволит быстро найти нужные объекты в отсортированном массиве. Конечно, все это делается
 *  только тогда, когда по каким-либо причинам реализация HashMap вам не подходит, так как именно HashMap обеспечивает
 *  быстрое нахождение объектов. К тому же создать карту HashMap из существующей TreeMap очень просто: для этого нужно
 *  просто определить один объект или вызвать putAll(). Вывод: когда вы используете таблицу, вашим основным выбором
 *  должен стать класс HashMap, и только при необходимости для часто сортируемой карты (Мар) следует выбирать TreeMap.
 *  Реализация LinkedHashMap работает медленнее, чем HashMap, так как ей приходится поддерживать не только хешированный
 *  контейнер, но и связанный список (для сохранения порядка вставки). По этой причине перебор выполняется быстрее.
 *  Производительность IdentityHashMap отличается от других карт, поскольку в ней сравнение выполняется не методом equals(), а оператором ==.
 */
public class MapPerformance {
    static List<Test<Map<Integer,Integer>>> tests = new ArrayList<Test<Map<Integer,Integer>>>();
    static {
        tests.add(new Test<Map<Integer,Integer>>("put") {
            int test(Map<Integer, Integer> map, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    map.clear();
                    for (int j = 0; j < size; j++) map.put(j, j);
                }
                return loops * size;
            }
        });
        tests.add(new Test<Map<Integer, Integer>>("get") {
            int test(Map<Integer,Integer> map, TestParam tp) {
                int loops = tp.loops;
                int span = tp.size * 2;
                for(int i = 0; i < loops; i++)
                    for(int j = 0; j < span; j++)
                        map.get(j); return loops * span;
            }
        });
        tests.add(new Test<Map<Integer,Integer>>("iterate") {
            int test(Map<Integer, Integer> map, TestParam tp) {
                int loops = tp.loops * 10;
                for (int i = 0; i < loops; i++) {
                    Iterator it = map.entrySet().iterator();
                    while (it.hasNext()) it.next();
                }
                return loops * map.size();
            }
        });
    }
    public static void main(String[] args) {
        if (args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        Tester.run(new TreeMap<Integer, Integer>(), tests);
        Tester.run(new HashMap<Integer, Integer>(), tests);
        Tester.run(new LinkedHashMap<Integer, Integer>(), tests);
        Tester.run(new IdentityHashMap<Integer, Integer>(), tests);
        Tester.run(new WeakHashMap<Integer, Integer>(), tests);
        Tester.run(new Hashtable<Integer, Integer>(), tests);
    }
}