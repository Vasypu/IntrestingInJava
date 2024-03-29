package containers;

import java.util.WeakHashMap;

/**
 *  WeakHashMap
 *  <p>
 *  WeakHashMap хранит слабые ссылки на свои ключевые объекты, поэтому записи могут автоматически удаляться,
 *  когда ключ теряет все обычные ссылки.
 *  Реализация Map, позволяющая GC автоматически удалять неиспользуемые объекты. Когда ключ объекта
 *  нигде не используется в нашем приложении, эта запись будет удалена из коллекции.
 */
class Element {
    private String ident;
    public Element(String id) { ident = id; }
    public String toString() { return ident; }
    public int hashCode() { return ident.hashCode(); }
    public boolean equals(Object r) {
        return r instanceof Element &&
                ident.equals(((Element)r).ident);
    }
    protected void finalize() {
        System.out.println("Finalizing " + getClass().getSimpleName() + " " + ident);
    }
}

class Key extends Element {
    public Key(String id) {
        super(id);
    }
}

class Value extends Element {
    public Value(String id) {
        super(id);
    }
}

// При запуске программы вы увидите, что уборщик мусора пропускает каждый третий ключ, так как для
// них были созданы обычные ссылки, помещенные в массив keys, а это значит, что для их объектов не
// разрешена уборка мусора.
public class CanonicalMapping {
    public static void main(String[] args) {
        int size = 1000;
        // Или использовать размер, заданный в командной строке:
        if(args.length > 0)
            size = new Integer(args[0]);

        Key[] keys = new Key[size];
        WeakHashMap<Key,Value> map = new WeakHashMap<Key,Value>();

        for(int i = 0; i < size; i++)
        {
            Key k = new Key(Integer.toString(i));
            Value v = new Value(Integer.toString(i));
            if (i % 3 == 0)
                keys[i] = k; // Сохраняем как "настоящие" ссылки
            map.put(k, v);
        }
        System.gc();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
