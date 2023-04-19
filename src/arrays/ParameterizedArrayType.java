package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 *  Массивы и обобщения
 *  <p>
 *  В общем случае, обобщения эффективны на границах класса или метода. Во внутренней
 *  реализации обобщения обычно становятся бесполезными из-за стирания.
 */

// Нельзя создать массивы параметризованных типов:
class Some {
//    Peel<Banana>[] peels = new Peel<Banana>[10]; // Недопустимо
}

class ClassParameter<T> {
    public T[] f(T[] arg) { return arg; }
}

class MethodParameter {
    public static <T> T[] f(T[] arg) { return arg; }
}

// Однако сам тип массива может быть параметризован.
// Обратите внимание на удобство использования параметризованного метода вместо параметризованного класса:
// вам не нужно создавать экземпляр класса с параметром для всех типов, к которым он применяется, и его
// можно сделать статическим
public class ParameterizedArrayType {
    public static void main(String[] args) {
        Integer[] ints = { 1, 2, 3, 4, 5 };
        Double[] doubles = { 1.1, 2.2, 3.3, 4.4, 5.5 };
        Integer[] ints2 = new ClassParameter<Integer>().f(ints);
        Double[] doubles2 = new ClassParameter<Double>().f(doubles);
        ints2 = MethodParameter.f(ints);
        doubles2 = MethodParameter.f(doubles);
    }
}

// Как видите, после создания ссылки на List<string>[] обеспечивается частичная проверка времени компиляции.
// Проблема в том, что массивы ковариантны, поэтому List<String>[] также является Object[], и вы можете
// использовать это обстоятельство для присваивания ArrayList<Integer> массиву без ошибок во время компиляции
// или выполнения.
class ArrayOfGenerics {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
//        компилятор не позволяет создать экземпляр массива обобщенного типа, однако он позволяет создать ссылку на такой массив
        List<String>[] ls;
        List[] la = new List[10];
        ls = (List<String>[]) la; // "Неконтролируемое" предупреждение
        ls[0] = new ArrayList<String>();
        // Проверка во время компиляции приводит к ошибке:
        //! ls[1] = new ArrayList<Integer>();

        // Проблема: List<String> является подтипом Object
        Object[] objects = ls; // Значит, присваивание допустимо
        // Компилируется и выполняется без ошибок:
        objects[1] = new ArrayList<Integer>();

        // Однако при простейших потребностях можно создать массив
        // обобщений, хотя и с "неконтролируемым" предупреждением:
        List<BerylliumSphere>[] spheres = (List<BerylliumSphere>[]) new List[10];
        for (int i = 0; i < spheres.length; i++)
            spheres[i] = new ArrayList<BerylliumSphere>();
    }
}

// Нельзя создать массив обобщенного типа. Стирание снова встает на пути — этот пример пытается создать массивы типов,
// которые были стерты, а следовательно, стали неизвестными. Можно создать массив Object и преобразовать его, но без
// аннотации @SuppressWarnings во время компиляции будет выдано предупреждение, потому что массив в действительности
// не содержит тип T и не осуществляет динамической проверки. Другими словами, если создать массив String[], Java как
// во время компиляции, так и во время выполнения будет следить за тем, чтобы в массив помещались только объекты String.
// Но если создать массив Object[], в него можно будет поместить что угодно, кроме примитивных типов.
class ArrayOfGenericType<T> {
    T[] array; // 0K
    @SuppressWarnings("unchecked")
    public ArrayOfGenericType(int size) {
        //! array = new T[size]; // Illegal
        array =(T[])new Object[size]; // "Неконтролируемое" предупреждение
    }
    // Недопустимо:
    //! public <U> U[] makeArray() { return new U[10]; }
}