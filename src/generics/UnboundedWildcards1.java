package generics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Неограниченные маски
 *  <p>
 *  Во многих ситуациях компилятор действительно совершенно не интересует, используете ли вы сам тип или <?>.
 *  В таких случаях <?> можно рассматривать как обычное украшение. Тем не менее маска все же полезна, потому
 *  что она фактически говорит: «Этот код написан с учетом обобщений, и здесь имеется в виду, что в обобщенном
 *  параметре может передаваться любой тип».
 *  List в действительности означает «неспециализированный контейнер List, в котором могут храниться любые объекты
 *  Object», тогда как List<?> означает «специализация List для конкретного типа, который нам неизвестен».
 *  Отличия List от List<Object> заключаются в том, что List нетипизирован, и поэтому вообще не выполняются проверки
 *  типов, что в конечном итоге приводит к определенным предупреждениям и может привести к странному поведению во время выполнения.
 */
public class UnboundedWildcards1 {
    static List list1;
    static List<?> list2;
    static List<? extends Object> list3;
    static void assign1(List list) {
        list1 = list;
        list2 = list;
//        list3 = list; // Предупреждение: неконтролируемое преобразование
//         Обнаружен: List, требуется: List<? extends Object>
    }
    static void assign2(List<?> list) {
        list1 = list;
        list2 = list;
        list3 = list;
    }
    static void assign3(List<? extends Object> list) {
        list1 = list;
        list2 = list;
        list3 = list;
    }
    public static void main(String[] args) {
        assign1(new ArrayList());
        assign2 (new ArrayList());
//        assign3(new ArrayList()); // Предупреждение:
        // Неконтролируемое преобразование. Обнаружен: ArrayList
        // Требуется: List<? extends Object>
        assign1(new ArrayList<String>());
        assign2(new ArrayList<String>());
        assign3(new ArrayList<String>());
        // Обе формы допустимы как List<?>:
        List<?> wildList = new ArrayList();
        wildList = new ArrayList<String>();
        assign1(wildList);
        assign2(wildList);
        assign3(wildList);
    }
}

// Компилятор не всегда обращает внимание на различия между, допустим, List и List<?>; создается впечатление,
// что это одно и то же, а это может породить путаницу. В самом деле, поскольку обобщенный аргумент стирается
// до первого ограничения, List<?> кажется эквивалентом List<object>, а List по сути тоже является List<Object>
// — впрочем, оба эти утверждения не совсем точны. List в действительности означает «неспециализированный
// контейнер List, в котором могут храниться любые объекты Object», тогда как List<?> означает «специализация
// List для конкретного типа, который нам неизвестен».
class UnboundedWildcards2 {
    static Map map1;
    static Map<?,?> map2;
    static Map<String,?> map3;
    static void assign1(Map map) { map1 = map; }
    static void assign2(Map<?,?> map) { map2 = map; }
    static void assign3(Map<String,?> map) { map3 = map; }
    public static void main(String[] args) {
        assign1(new HashMap());
        assign2(new HashMap());
//        assign3(new HashMap()); // Предупреждение: Неконтролируемое преобразование. Обнаружен: HashMap, требуется: Map<String,?>
        assign1(new HashMap<String,Integer>());
        assign2(new HashMap<String,Integer>());
        assign3(new HashMap<String,Integer>());
    }
}

// В rawArgs() компилятор знает, что Holder является обобщенным типом, поэтому даже несмотря на то, что здесь он
// выражается неспециализированным типом, компилятор знает, что передача Object методу set() небезопасна. Так как
// это неспециализированный тип, методу set() можно передать объект любого типа, и этот объект будет приведен к
// Object восходящим преобразованием. Итак, каждый раз, когда вы используете неспециализированный тип, вы отказываетесь
// от проверки на стадии компиляции. Вызов get() демонстрирует ту же проблему: при отсутствии T результатом может
// быть только Object.

// Метод unboundedArg() подчеркивает различия между Holder и Holder<?> — он выявляет те же проблемы, но сообщает о них как об ошибках,
// а не как о предупреждениях, потому что неспециализированный тип Holder может содержать комбинацию любых типов, а
// Holder<?> содержит однородную коллекцию некоторого конкретного типа, а следовательно, передать Object просто не
// получится.

// В wildSubtype() ограничения для типа Holder ослабляются до Holder для любых типов, расширяющих T. И снова это означает,
// что типом т может быть Fruit, тогда как holder может содержать Holder<Apple>. Чтобы предотвратить помещение Orange в
// Holder<Apple>, вызов set () (или любого другого метода, получающего параметр-тип) запрещен. Но при этом вы знаете, что
// все, что выходит из Holder< ? extends Fruit>, будет по меньшей мере Fruit, поэтому вызов get() (или любого метода,
// возвращающего параметр-тип) разрешен.

// Применение маски супертипа продемонстрировано в методе wildSupertype(), поведение которого обратно поведению wildSubtype():
// holder может содержать контейнер с элементами любого типа, который является разновидностью базового класса т. Таким образом,
// set () может принять T, так как все, что работает с базовым типом, будет полиморфно работать с производным типом (отсюда т).
// Однако попытка вызова get() пользы не принесет, потому что тип, хранимый в holder, может быть абсолютно любым супертипом,
// поэтому единственным безопасным вариантом является Object. Этот пример также показывает ограниченные возможности выполнения
// операций с неограниченным параметром в unbounded(): вы не можете вызвать get() или set() для T, потому что у вас нет T.

// В методе main() мы видим, какие из этих методов могут получать те или иные типы аргументов без ошибок и предупреждений.
// Для обеспечения миграционной совместимости rawArgs() будет получать все разновидности Holder без выдачи предупреждений.
// Метод unboundedArg() также принимает все типы, хотя, как упоминалось ранее, в теле метода он работает с ними иначе.

// Если передать неспециализированную ссылку Holder методу, который получает «точный» обобщенный тип (без маски), вы получите
// предупреждение, потому что точный аргумент ожидает получить информацию, не существующую в неспециализированном типе. И если
// передать неограниченную ссылку exact1(), то у компилятора не будет информации о типе для установления возвращаемого типа.

// Мы видим, что метод exact2() имеет больше всего ограничений, поскольку он должен получать Holder<T> и аргумент типа T;
// не получив точных аргументов, он выдает ошибки и предупреждения.
class Wildcards {
    // Неспециализированный аргумент:
    static void rawArgs(Holder holder, Object arg) {
        // holder.set(arg); // Предупреждение: Неконтролируемый вызов set(T) как члена
        // неспециализированного типа Holder
        // holder.set(new Wildcards()); // То же предупреждение

        // Невозможно - 'T' отсутствует:
        // T t = holder.get();

        // Допустимо, но информация типа теряется:
        Object obj = holder.get();
    }

// Аналогично rawArgs(), но с выдачей ошибок вместо предупреждений:
static void unboundedArg(Holder<?> holder, Object arg) {
    // holder.set(arg); // Ошибка: set(capture of ?) в Holder<capture of ?>
    // не может применяться к (Object)
    // holder.set(new Wildcards()); // Та же ошибка

    // Невозможно - 'T' отсутствует:
    // T t = holder.get();

    // Допустимо, но информация типа теряется:
    Object obj = holder.get();
}

    static <T> T exact1(Holder<T> holder) {
        T t = holder.get();
        return t;
    }

    static <T> T exact2(Holder<T> holder, T arg) {
        holder.set(arg);
        T t = holder.get();
        return t;
    }

    static <T> T wildSubtype(Holder<? extends T> holder, T arg) {
        // holder.set(arg); // Ошибка: set(capture of ? extends T) в
        // Holder<capture of ? extends T>
        // не может применяться к (T)
        T t = holder.get();
        return t;
    }

    static <T> void wildSupertype(Holder<? super T> holder, T arg) {
        holder.set(arg);
        // T t = holder.get(); // Ошибка: Несовместимые типы: обнаружен Object, требуется T
        // Допустимо, но информация типа теряется:
        Object obj = holder.get();
    }

    public static void main(String[] args) {
        Holder raw = new Holder<Long>();
        // Или:
        raw = new Holder();
        Holder<Long> qualified = new Holder<Long>();
        Holder<?> unbounded = new Holder<Long>();
        Holder<? extends Long> bounded = new Holder<Long>();
        Long lng = 1L;

        rawArgs(raw, lng);
        rawArgs(qualified, lng);
        rawArgs(unbounded, lng);
        rawArgs(bounded, lng);

        unboundedArg(raw, lng);
        unboundedArg(qualified, lng);
        unboundedArg(unbounded, lng);
        unboundedArg(bounded, lng);

        // Object r1 = exact1(raw); // Предупреждения:
        // Неконтролируемое преобразование Holder в Holder<T>
        // Неконтролируемый вызов метода: exact1(Holder<T>)
        // применяется к (Holder)
        Long r2 = exact1(qualified);
        Object r3 = exact1(unbounded); // Должен вернуть Object
        Long r4 = exact1(bounded);

        // Long г5 = exact2(raw, lng); // Предупреждения:
        // Неконтролируемое преобразование Holder в Holder<Long>
        // Неконтролируемый вызов метода: exact2(Holder<T>,T)
        // применяется к (Holder,Long)
        Long гб = exact2(qualified, lng);
        // Long r7 - exact2(unbounded> lng); // Ошибка:
        // exact2(Holder<T>,T) не может применяться // к (Holder<capture of ?>,Long)
        // Long r8 = exact2(bounded, lng); // Ошибка:
        // exact2(Holder<T>,T) не может применяться // к (Holder<capture of ? extends Long>jLong)

        // Long r9 = wildSubtype(raw, lng); // Предупреждения:
        // Неконтролируемое преобразование Holder // в Holder<? extends Long>
        // Неконтролируемый вызов метода:
        // wildSubtype(Holder<? extends T>,T)
        // применяется к (Holder,Long)
        Long r10 = wildSubtype(qualified, lng);
        // Допустимо, но может возвращать только Object:
        Object r11 = wildSubtype(unbounded, lng);
        Long r12 = wildSubtype(bounded, lng);
        // wildSupertype(raw, lng)j // Предупреждения:
        // Неконтролируемое преобразование Holder // в Holder<? super Long>
        // Неконтролируемый вызов метода:
        // wildSupertype(Holder<? super T>,T)
        // применяется к (Holder,Long)
        wildSupertype(qualified, lng);
        // wildSupertype(unbounded, lng); // Ошибка:
        // wildSupertype(Holder<? super T>jT) не может
        // применяться к (Holder<capture of ?>,Long)
        // wildSupertype(bounded, lng); // Ошибка:
        // wildSupertype(Holder<? super T>,T) не может // применяться к (Holder<capture of ? extends Long>jLong)
    }
}