package generics;

import java.util.ArrayList;
import java.util.List;

/**
 *  Исключения
 *  <p>
 *  Из-за стирания возможности использования обобщений с исключениями сильно ограниченны. Блок catch не может
 *  перехватывать исключение обобщенного типа, потому что тип исключения должен быть известен и во время компиляции,
 *  и во время выполнения. Кроме того, обобщенный класс не может прямо или косвенно наследовать от Throwable (это
 *  препятствует определению обобщенных исключений, которые невозможно перехватить). Однако параметры-типы могут
 *  использоваться в секции throws объявления метода. Это позволяет писать обобщенный код, изменяемый по типу
 *  контролируемого исключения.
 *  K - это второе параметризированное исключение, которое демонстрирует возможность независимого изменения исключений.
 */
interface Processor<T,E extends Exception, K extends Exception> {
    void process(List<T> resultCollector) throws E, K;
}

class ProcessRunner<T,E extends Exception, K extends Exception> extends ArrayList<Processor<T,E,K>> {
    List<T> processAll() throws E, K {
        List<T> resultCollector = new ArrayList<T>();
        for (Processor<T,E,K> processor : this)
            processor.process(resultCollector);
        return resultCollector;
    }
}

class Failure1 extends Exception {}
class Failure3 extends Exception {}
class Processor1 implements Processor<String,Failure1,Failure3> {
    static int count = 3;
    public void process(List<String> resultCollector) throws Failure1 {
        if(count-- > 1)
            resultCollector.add("Hep I");
        else
            resultCollector.add("Ho I");
        if(count < 0)
            throw new Failure1();
    }
}

class Failure2 extends Exception {}
class Processor2 implements Processor<Integer,Failure2,Failure3> {
    static int count = 2;

    public void process(List<Integer> resultCollector) throws Failure2, Failure3 {
        if (count-- == 0)
            resultCollector.add(47);
        else {
            resultCollector.add(11);
        }
        if (count == 0) throw new Failure3();
        if (count < 0) throw new Failure2();
    }
}

public class ThrowGenericException {
    public static void main(String[] args) {
        ProcessRunner<String, Failure1,Failure3> runner = new ProcessRunner<String, Failure1,Failure3>();
        for(int i = 0; i < 3; i++)
            runner.add(new Processor1());
        try {
            System.out.println(runner.processAll());
        } catch(Failure1 | Failure3 e) {
            System.out.println(e);
        }
        ProcessRunner<Integer,Failure2,Failure3> runner2 = new ProcessRunner<Integer, Failure2,Failure3>();
        for(int i = 0; i < 3; i++)
            runner2.add(new Processor2());
        try {
            System.out.println(runner2.processAll());

        } catch(Failure2 | Failure3 e) {
            System.out.println(e);
        }
    }
}