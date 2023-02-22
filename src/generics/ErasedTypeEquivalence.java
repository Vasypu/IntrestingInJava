package generics;

import java.util.*;

/**
 *  Загадка стирания
 *  <p>
 *  В обобщенном коде информация о параметрах-типах обобщения недоступна. Вы можете узнать
 *  такие аспекты, как идентификатор параметра-типа и ограничения обобщенного типа — но не
 *  фактические параметры-типы, использованные для создания конкретного экземпляра.
 *  Обобщения реализуются с использованием стирания (erasure). Иначе говоря, при использовании
 *  обобщения любая конкретная информация о типе теряется. В обобщении известно лишь то, что
 *  вы используете объект. Таким образом, List<String> и List<Integer> во время выполнения по
 *  сути являются одним типом. Обе формы «стираются» до «низкоуровневого» типа List.
 */

// Логика подсказывает, что ArrayList<String> и ArrayList<Integer> являются разными типами.
// Разные типы ведут себя по-разному; так, при попытке поместить Integer в ArrayList<String>
// вы получите другое поведение, чем при помещении Integer в ArrayList<Integer>. И все же из
// приведенной программы следует, что они относятся к одному типу.
public class ErasedTypeEquivalence {
    public static void main(String[] args) {
        Class cl = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(cl == c2);
    }
}

// Согласно документации JDK, метод Class.getTypeParameters() «возвращает массив объектов TypeVariable,
// представляющих переменные типов, объявленные в объявлении обобщения...» Это наводит на мысль, что
// вы можете получить информацию о параметрах-типах. Но как видно из результатов, метод возвращает
// только идентификаторы, представляющие параметры, а эта информация интереса не представляет.
class Frob {}
class Fnorkle {}
class Quark<Q> {}
class Particle<POSITION,MOMENTUM> {}
class LostInformation {
    public static void main(String[] args) {
        List<Frob> list = new ArrayList<Frob>();
        Map<Frob,Fnorkle> map = new HashMap<>();
        Quark<Fnorkle> quark = new Quark<Fnorkle>();
        Particle<Long,Double> p = new Particle<Long,Double>();
        System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(map.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(quark.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(p.getClass().getTypeParameters()));
    }
}