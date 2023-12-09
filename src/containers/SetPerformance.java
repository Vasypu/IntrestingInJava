package containers;

import java.util.*;

/**
 *  Выбор между множествами
 *  <p>
 *  Множество HashSet превосходит по скоростным показателям TreeSet при проведении практически всех
 *  операций (в особенности добавления и поиска элементов, которые являются важнейшими операциями).
 *  Множество TreeSet создано только потому, что поддерживает хранимые элементы в упорядоченном виде,
 *  и имеет смысл к нему обращаться, только если вам понадобится отсортированное множество. Из-за
 *  внутренней структуры, необходимой для поддержки сортировки, а также потому, что перебор в таких
 *  реализациях является более вероятной операцией, TreeSet обычно превосходит HashSet по скорости
 *  перебора.
 *  <p>
 *  Заметьте, что класс LinkedHashSet вставляет элементы немного медленнее, чем HashSet; так происходит
 *  из-за необходимости поддерживать не только хешированный контейнер, но и связанный список.
 */
public class SetPerformance {
    static List<Test<Set<Integer>>> tests = new ArrayList<>();
    static {
        tests.add(new Test<Set<Integer>>("add") {
            int test(Set<Integer> set, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    set.clear();
                    for (int j = 0; j < size; j++)
                        set.add(j);
                }
                return loops * size;
            }
        });
        tests.add(new Test<Set<Integer>>("contains") {
            int test(Set<Integer> set, TestParam tp) {
                int loops = tp.loops;
                int span = tp.size * 2;
                for (int i = 0; i < loops; i++)
                    for (int j = 0; j < span; j++)
                        set.contains(j);
                return loops * span;
            }
        });
        tests.add(new Test<Set<Integer>>("iterate") {
            int test(Set<Integer> set, TestParam tp) {
                int loops = tp.loops * 10;
                for (int i = 0; i < loops; i++) {
                    Iterator<Integer> it = set.iterator();
                    while (it.hasNext()) it.next();
                }
                return loops * set.size();
            }
        });
    }
    public static void main(String[] args) {
        if (args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        Tester.fieldWidth = 10;
        Tester.run(new TreeSet<Integer>(), tests);
        Tester.run(new HashSet<>(), tests);
        Tester.run(new LinkedHashSet<Integer>(), tests);
    }
}