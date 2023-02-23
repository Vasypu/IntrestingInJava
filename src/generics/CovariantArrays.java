package generics;

import java.util.ArrayList;
import java.util.List;

/**
 *  Маски
 */
class Fruit {}
class Apple extends Fruit {}
class Jonathan extends Apple {}
class Orange extends Fruit {}

// Первая строка main() создает массив Apple и присваивает его ссылке на массив Fruit. Это разумно — яблоко (Apple)
// является разновидностью фрукта (Fruit), так что массив элементов Apple также может рассматриваться как массив Fruit.
// Но если фактическим типом массива является Арр1е[], то в массив могут помещаться только объекты типа Apple или типов,
// производных от Apple, что фактически работает как во время компиляции, так и во время выполнения. Но обратите внимание:
// компилятор позволяет поместить объект Fruit в массив. Для компилятора это выглядит логично, потому что он работает со
// ссылкой Fruit [ ], — так почему бы не разрешить включение в массив объекта Fruit или типа, производного от Fruit
// (например, Orange)? Во время компиляции это допустимо. Однако ядро времени выполнения знает, что оно имеет дело с
// Apple[], и выдает исключение при помещении в массив постороннего типа.
public class CovariantArrays {
    public static void main(String[] args) {
        Fruit[] fruit = new Apple[10];
        fruit[0] = new Apple();    // 0K
        fruit[1] = new Jonathan(); // 0K
        // Тип времени выполнения - Apple[], а не Fruit[] и не Orange[]:
        try {
        // Компилятор позволяет добавить Fruit:
            fruit[0] = new Fruit(); // ArrayStoreException
        } catch(Exception e) { System.out.println(e); }
        try {
        // Компилятор позволяет добавлять Orange:
            fruit[0] = new Orange(); // ArrayStoreException
        } catch(Exception e) { System.out.println(e); }
    }
}

// На первый взгляд это читается как «Контейнер с элементами Apple нельзя присвоить контейнеру Fruit», помните, что
// применение обобщений не сводится к контейнерам. По сути говорится другое: «Обобщение с Apple не может быть присвоено
// обобщению с Fruit». Если (как в случае с массивами) компилятор знает о коде достаточно, чтобы понять, что в нем
// задействованы контейнеры, возможно, он мог бы немного смягчить требования. Но компилятор такой информацией не
// располагает и поэтому отказывается разрешать «восходящее преобразование». Впрочем, на деле это не является
// «восходящим преобразованием» — контейнер List с элементами Apple не является List с элементами Fruit. В контейнере
// List с элементами Apple могут храниться объекты типа Apple и типов, производных от Apple, а в контейнере List с
// элементами Fruit могут храниться любые разновидности Fruit. Да, в том числе и Apple, но от этого контейнер не
// становится контейнером List с элементами Apple; он остается контейнером List с элементами Fruit. Контейнер List с
// элементами Apple не эквивалентен по типу контейнеру List с элементами Fruit, несмотря на то что Apple является
// разновидностью Fruit.
// Настоящая проблема заключается в том, что речь идет о типе контейнера, а не о типе элементов, хранящихся в контейнере.
// В отличие от массивов обобщения не обладают встроенной ковариантностью. Это связано с тем, что массивы полностью
// определяются в языке, а следовательно, для них реализованы встроенные проверки как на стадии компиляции, так и на
// стадии выполнения, тогда как с обобщениями компилятор и исполнительная система не знают, что вы хотите делать со
// своими типами и какие при этом должны использоваться правила.
class NonCovariantGenerics {
    // Ошибка компиляции: несовместимые типы:
//    List<Fruit> flist = new ArrayList<Apple>();
}

// В некоторых ситуациях между двумя типами желательно установить некую разновидность отношений восходящего преобразования.
// Для этой цели и используются маски (wildcards).
// Теперь flist имеет тип List<? extends Fruit>, что можно прочитать как «список с элементами любого типа, производного от Fruit».
// Однако это не означает, что в List можно будет хранить любые виды Fruit. Маска относится к определенному типу, так что это означает
// «некоторый конкретный тип, не указанный для ссылки flist». Итак, присваиваемый объект List должен содержать некоторый указанный тип
// (например, Fruit или Apple), но для восходящего преобразования к flist этот тип несущественен.
// Если единственное ограничение состоит в том, что List может содержать объекты Fruit или типа, производного от Fruit, но для вас
// несущественно, что это за тип, то что можно сделать с таким контейнером List? Если вы не знаете, какой тип хранится в List, как
// безопасно добавить объект? Как и в случае с «восходящим преобразованием» массива в CovariantArrays.java, это невозможно — только
// на этот раз запрет исходит от компилятора, а не от исполнительной системы. Проблема обнаруживается быстрее.
// Кто-то скажет, что это уже перебор, потому что теперь мы не можем даже добавить Apple в контейнер List, в котором, как только что
// было сказано, будут храниться объекты Apple. Да, но ведь компилятор этого не знает! List< ? extends Fruit> может на законных
// основаниях указывать на List<Orange>. После такого «восходящего преобразования» теряется способность передачи чего-либо, даже Object.
class GenericsAndCovariance {
    public static void main(String[] args) {
        // Маски обеспечивают ковариантность:
        List<? extends Fruit> flist = new ArrayList<Apple>();
        // Ошибка компиляции: добавление объектов невозможно:
//         flist.add(new Apple());
//         flist.add(new Fruit());
//         flist.add(new Object());
        flist.add(null); // Допустимо, но неинтересно
        // Мы знаем, что возвращается как минимум Fruit:
        Fruit f = flist.get(0);
    }
}