package RTTI;

/**
 *  Динамические заместители
 *  <p>
 *  Заместитель (proxy) является одним из основных паттернов проектирования. Он представляет собой объект,
 *  который подставляется наместо «настоящего» объекта для предоставления дополнительных или других
 *  операций — обычно подразумевающих взаимодействие с «настоящим» объектом, поэтому заместитель чаще
 *  всего выполняет функции посредника.
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Interface {
    void doSomething();
    void somethingElse(String arg);
}

class RealObject implements Interface {
    public void doSomething() { System.out.println("doSomething"); }
    public void somethingElse(String arg) { System.out.println("somethingElse " + arg); }
}

class SimpleProxy implements Interface {
    private Interface proxied;
    public SimpleProxy(Interface proxied) { this.proxied = proxied; }
    public void doSomething() {
        System.out.println("SimpleProxy doSomething");      // промежуточная операция
        proxied.doSomething();
    }

    public void somethingElse(String arg) {
        System.out.println("SimpleProxy somethingElse " + arg); // промежуточная операция
        proxied.somethingElse(arg);
    }
}

class SimpleProxyDemo {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }
    public static void main(String[] args) {
        consumer(new RealObject());
        consumer(new SimpleProxy(new RealObject()));
    }
}

// В общем случае выполняется промежуточная операция, после чего вызов Method. invoke()
// перенаправляет запрос замещаемому объекту с передачей необходимых аргументов.
class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;
    public DynamicProxyHandler(Object proxied) { this.proxied = proxied; }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("**** proxy: " + proxy.getClass() +
                ", method: " + method + ", args: " + args);
        if(args != null)
            for(Object arg : args)
                System.out.println(" " + arg);
        return method.invoke(proxied, args);
    }
}

// Динамический заместитель создается вызовом статического метода Proxy.newProxy- Instance().
// Динамический посредник перенаправляет все вызовы обработчику, так что конструктор обработчика
// вызовов обычно получает ссылку на «настоящий» объект для перенаправления запросов после
// выполнения его промежуточной операции
public class SimpleDynamicProxy {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

    public static void main(String[] args) {
        RealObject real = new RealObject();
        consumer(real);
        // Insert a proxy and call again:
        Interface proxy = (Interface) Proxy.newProxyInstance(
                Interface.class.getClassLoader(),   // загрузчик классов (как правило, можно просто передать загрузчик из уже загруженного объекта)
                new Class[]{ Interface.class },     // список интерфейсов (не классов и не абстрактных классов), которые должны реализоваться заместителем
                new DynamicProxyHandler(real));     // обработчик вызовов (реализация интерфейса InvocationHandler)
        consumer(proxy);
    }
}

class MethodSelector implements InvocationHandler {
    private Object proxied;
    public MethodSelector(Object proxied) { this.proxied = proxied; }

    public Object
    invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("interesting"))
            System.out.println("Proxy detected the interesting method");
        return method.invoke(proxied, args);
    }
}

interface SomeMethods {
    void boring1();
    void boring2();
    void interesting(String arg);
    void boring3();
}

class Implementation implements SomeMethods {
    public void boring1() { System.out.println("boring1"); }
    public void boring2() { System.out.println("boring2"); }
    public void interesting(String arg) { System.out.println("interesting " + arg); }
    public void boring3() { System.out.println("boring3"); }
}

class SelectingMethods {
    public static void main(String[] args) {
        SomeMethods proxy = (SomeMethods)Proxy.newProxyInstance(
                SomeMethods.class.getClassLoader(),
                new Class[]{ SomeMethods.class },
                new MethodSelector(new Implementation()));
        proxy.boring1();
        proxy.boring2();
        proxy.interesting("bonobo");
        proxy.boring3();
    }
}