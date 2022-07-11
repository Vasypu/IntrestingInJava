package interfaces;

import java.util.Random;

/**
 *  Поля в интерфейсах.
 *
 *  Все поля в интерфейсах автоматически static и final. Поля в интерфейсах
 *  не могут быть пустыми константами, но могут инициализироваться не-константными
 *  выражениями. Так как поля статические, они инициализируются при первой загрузке
 *  класса, которая происходит при первом обращении к любому из полей интерфейса.
 *  Поля не являются частью интерфейса, данные хранятся в статической области памяти,
 *  отведенной для данного интерфейса.
 */

interface RandVals {
    Random RAND = new Random(47);
    int RANDOM_INT = RAND.nextInt(10);
//    int RANDOM_INT_2;                         не может быть пустой константой
    long RANDOM_LONG = RAND.nextLong() * 10;
    float RANDOM_FLOAT = RAND.nextLong() * 10;
    double RANDOM_DOUBLE = RAND.nextDouble() * 10;
}

public class TestRandVals {
    public static void main(String[] args) {
        System.out.println(RandVals.RANDOM_INT);
        System.out.println(RandVals.RANDOM_LONG);
        System.out.println(RandVals.RANDOM_FLOAT);
        System.out.println(RandVals.RANDOM_DOUBLE);
    }
}
