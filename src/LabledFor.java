public class LabledFor {
    public static void main(String[] args) {
        int i = 0;
        outer:
        for (; true ;) {
            inner:
            for (; i < 10; i++) {
                System.out.println("i = " + i);
                if (i == 2) {
                    System.out.println("continue");
                    continue;
                }
                if (i == 3) {
                    System.out.println("break");
                    i++;
                    break;
                }
                if(i == 7) {
                    System.out.println("continue outer");
                    i++;
                    continue outer;
                }
                if (i == 8) {
                    System.out.println("break outer");
                    break outer;
                }
                for (int k = 0; k < 5; k++) {
                    if (k == 3) {
                        System.out.println("continue inner");
                        continue inner;
                    }
                }
            }
        }
    }
}

class LabledWhile {
    public static void main(String[] args) {
        int i = 0;
        outer:
        while (true) {
            System.out.println("Внешний цикл while");
            while (true) {
                i++;
                System.out.println("i = " + i);
                if (i == 1) {
                    System.out.println("continue");
                    continue;
                }
                if (i == 3) {
                    System.out.println("continue outer");
                    continue outer;
                }
                if (i == 5) {
                    System.out.println("break");
                    break;
                }
                if (i == 7) {
                    System.out.println("break outer");
                    break outer;
                }
            }
        }
    }
}

/**
 * Обычная команда continue переводит исполнение к началу текущего внутреннего цикла, программа продолжает работу.
 * Команад continue с меткой вызывает переход к метке и повторный вход в цикл, следующий прямо за этой меткой.
 * Команда break завершает выполнение цикла.
 * Команда break с меткой завершает выполнение внутреннего цикла и цикла, который находится после указанной метки.
 */
