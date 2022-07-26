package final_classes_and_methods;

import java.util.Random;

/**
 * С ключивым словом final поле с примитивным типом данных становится неизменным, если же оно
 * помечено еще словом static то оно существует в единственном экземпляре.
 * Для ссылок с ключевым словом final объект не становится неизменным, то есть можно менять его
 * значения, но нельзя присвоить новый объект этой ссылке.
 */

class Value {
    int i;
    public Value(int i) { this.i = i; }
}

public class FinalData {
    private static Random rand = new Random(47);
    private String id;
    public FinalData(String id) { this.id = id; }
//  Могут быть константой времени компиляции
    private final int valueOne = 9;
    private static final int VALUE_TWO = 99;
//  Типичная открытая константа
    public static final int VALUE_THREE = 39;
//  Не может быть константой времени компиляции
    private final int i4 = rand.nextInt(20);
    static final int INT_5 = rand.nextInt(20);
    private Value v1 = new Value(11);
    private final Value v2 = new Value(22);
    private static final Value VAL_3 = new Value(33);
//  Массивы
    private final int [] a = { 1, 2, 3, 4, 5, 6 };

    @Override
    public String toString() {
        return  id + ": " + " i4 = " + i4 + " INT_5 = " + INT_5;
    }

    public static void main(String[] args) {
        FinalData fdl = new FinalData("fdl");
//      ! fdl.valueOne++;
        fdl.v2.i++;                                // Объект не является неизменным
        fdl.v1 = new Value(9);
        for (int i = 0; i < fdl.a.length; i++)
            fdl.a[i]++;
//      Ошибка, ссылку нельзя изменить
//        fdl.v2 = new Value(0);
//        VAL_3 = new Value(1);
//        fdl.a = new int[3];
        System.out.println(fdl);
        System.out.println("Создаем FinalData");
        FinalData fd2 = new FinalData("fd2");
        System.out.println(fdl);
        System.out.println(fd2);
    }
}
