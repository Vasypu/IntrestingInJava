package strings;

import java.util.ArrayList;
import java.util.List;

/**
 *  Непреднамеренная рекурсия
 *
 *  Допустим, вы хотите, чтобы метод toString() выводил адрес объекта вашего класса.
 *  Казалось бы, для этого достаточно использовать ссылку this.
 *  Но при попытке создать объект InfiniteRecursion и вывести его вы получите очень
 *  длинную последовательность исключений.
 *  Проблемы возникают из-за автоматического преобразования типов для String:
 *  " InfiniteRecursion address: " + this
 *  Компилятор видит объект String, за которым следует + и нечто, не являющееся строкой;
 *  соответственно, он пытается преобразовать this в String. Для этого он вызывает метод
 *  toString(), порождающий рекурсивный вызов.
 *  Если вам действительно потребуется вывести адрес объекта, задача решается вызовом
 *  метода toString() класса Object. Таким образом, вместо this следует использовать
 *  выражение super.toString().
 */

public class InfiniteRecursion {
    public String toString() {
        return " InfiniteRecursion address: " + this + "\n";
    }

    public static void main(String[] args) {
        List<InfiniteRecursion> v = new ArrayList<InfiniteRecursion>();
        for (int i = 0; i < 10; i++)
            v.add(new InfiniteRecursion());
        System.out.println(v);
    }
}
