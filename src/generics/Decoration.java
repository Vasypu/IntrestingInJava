package generics;

import java.util.Date;

/**
 *  Использование паттерна «Декоратор»
 *  <p>
 *  Класс, полученный в результате применения примесей, содержит все нужные методы, но типом объекта, полученного
 *  от использования декораторов, будет последний тип, которым он был декорирован. Итак, хотя вы можете добавить
 *  более одного уровня, фактический тип будет определяться последним уровнем, так что видны только методы последнего
 *  уровня, тогда как тип примеси образуется всеми смешиваемыми типами. Следовательно, существенный недостаток
 *  паттерна «Декоратор» заключается в том, что он фактически работает с одним уровнем декорирования (последним),
 *  а решения с примесями, пожалуй, выглядит более естественно. Таким образом, паттерн «Декоратор» может считаться
 *  ограниченным решением задачи при помощи примесей.
 */
class BasicTwo {
    private String value;
    public void set(String val) { value = val; }
    public String get() { return value; }
}
class Decorator extends BasicTwo {
    protected BasicTwo basic;
    public Decorator(BasicTwo basic) { this.basic = basic; }
    public void set(String val) { basic.set(val); }
    public String get() { return basic.get(); }
}
class TimeStampedTwo extends Decorator {
    private final long timeStamp;
    public TimeStampedTwo(BasicTwo basic) {
        super(basic);
        timeStamp = new Date().getTime();
    }
    public long getStamp() { return timeStamp; }
}
class SerialNumberedTwo extends Decorator {
    private static long counter = 1;
    private final long serialNumber = counter++;
    public SerialNumberedTwo(BasicTwo basic) { super(basic); }
    public long getSerialNumber () { return serialNumber; }
}

public class Decoration {
    public static void main(String[] args) {
        TimeStampedTwo t = new TimeStampedTwo(new BasicTwo());
        TimeStampedTwo t2 = new TimeStampedTwo(new SerialNumberedTwo(new BasicTwo()));
//        t2.getSerialNumber(); // Недоступно
        SerialNumberedTwo s = new SerialNumberedTwo(new BasicTwo());
        SerialNumberedTwo s2 = new SerialNumberedTwo(new TimeStampedTwo(new BasicTwo()));
//        s2.getStamp(); // Недоступно
    }
}
