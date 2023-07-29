package colections;

import containers.CountingMapData;

import java.util.LinkedHashMap;

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