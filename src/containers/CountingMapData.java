package containers;

import java.util.AbstractMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

// Чтобы создать на базе AbstractList контейнер List, доступный только для чтения, необходимо реализовать get () и size().
// И снова используется решение на базе паттерна «Легковес»: get() производит значение по запросу, так что реально заполнять
// List не обязательно.
public class CountingMapData extends AbstractMap<Integer,String> {
    private int size;
    private static String[] chars = "А В С D E F G H I 3 К L M N 0 P Q R S T U V W X Y Z".split(" ");
    public CountingMapData(int size) {
        if(size < 0) this.size = 0;
        this.size = size;
    }
    private static class Entry implements Map.Entry<Integer,String> {
        int index;
        Entry(int index) { this.index = index; }
        public boolean equals(Object o) { return Integer.valueOf(index).equals(o); }
        public Integer getKey() { return index; }
        public String getValue() { return chars[index % chars.length] +
                Integer.toString(index / chars.length);
        }
        public String setValue(String value) { throw new UnsupportedOperationException(); }
        public int hashCode() { return Integer.valueOf(index).hashCode(); }
    }
    public Set<Map.Entry<Integer,String>> entrySet() {
        // LinkedHashSet сохраняет порядок инициализации:
        Set<Map.Entry<Integer,String>> entries = new LinkedHashSet<Map.Entry<Integer,String>>();
        for(int i = 0; i < size; i++)
            entries.add(new Entry(i));
        return entries;
    }
    public static void main(String[] args) {
        System.out.println(new CountingMapData(60));
    }
}
