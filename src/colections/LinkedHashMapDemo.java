package colections;

import containers.CountingMapData;

import java.util.LinkedHashMap;

/**
 *  LinkedHashMap
 *  <p>
 *  Похожа на HashMap, однако при переборе выдает пары в порядке их добавления, или согласно принципу LRU
 *  («наименее используемые идут первыми», least-recendy-used). Лишь немногим медленнее HashMap, за исключением
 *  процесса перечисления элементов, где она быстрее за счет внутреннего связанного списка, отвечающего за хранение
 *  порядка добавления элементов.
 *  <p>
 *  Карта LinkedHashMap производит хеширование для ускорения работы, но при этом также поддерживает порядок
 *  вставки пар объектов при переборе (HanpnMep,System. out. println() выполняет перебор, и вы видите его результаты).
 *  Вдобавок конструктор позволяет настроить LinkedHashMap в соответствии с принципом выборки «наименее используемые
 *  идут первыми» (LRU, least-recently-used). Алгоритм учитывает частоту доступа к элементам, и те из них, что
 *  востребовались реже других, помещаются в начало списка. Это помогает создавать программы, которые в целях
 *  экономии периодически высвобождают ресурсы.
 *  <p>
 *  Результат работы программы показывает, что пары действительно перебираются в порядке их вставки, даже при
 *  использовании LRU. Однако после обращения только к первым шести элементам последние три сместились в начало
 *  списка. Когда затем был еще раз запрошен элемент «0», он оказался в самом конце списка.
 */
public class LinkedHashMapDemo {
    public static void main(String[] args) {
        LinkedHashMap<Integer, String> linkedMap = new LinkedHashMap<Integer, String>(new CountingMapData(9));
        System.out.println(linkedMap);
        // Порядок LRU:
        linkedMap = new LinkedHashMap<Integer, String>(16, 0.75f, true);
        linkedMap.putAll(new CountingMapData(9));
        System.out.println(linkedMap);
        for (int i = 0; i < 6; i++) // Обращения к элементам:
            linkedMap.get(i);
        System.out.println(linkedMap);
        linkedMap.get(0);
        System.out.println(linkedMap);
    }
}