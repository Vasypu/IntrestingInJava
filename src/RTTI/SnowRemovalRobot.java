package RTTI;

/**
 *  Null-объекты
 *  <p>
 *  Если вы работаете с интерфейсами вместо конкретных классов, для автоматического создания
 *  null-объектов можно использовать динамический заместитель.
 *  <p>
 *  Когда вам понадобится null-объект Robot, вы просто вызовете newNullRobot(), передавая тип
 *  Robot, для которого нужен заместитель. Заместитель выполняет требования к интерфейсам Robot
 *  и Null и предоставляет конкретное имя замещаемого типа.
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

interface Operation {
    String description();
    void command();
}

interface Robot {
    String name();
    String model();
    List<Operation> operations();
    class Test {
        public static void test(Robot r) {
            if (r instanceof Null)
                System.out.println("[Null Robot]");
            System.out.println("Robot name: " + r.name());
            System.out.println("Robot model: " + r.model());
            for (Operation operation : r.operations()) {
                System.out.println(operation.description());
                operation.command();
            }
        }
    }
}

public class SnowRemovalRobot implements Robot {
    private String name;
    public SnowRemovalRobot(String name) { this.name = name;}
    public String name() { return name; }
    public String model() { return "SnowBot Series 11"; }
    public List<Operation> operations() {
        return Arrays.asList(
                new Operation() {
                    public String description() { return name + " can shovel snow"; }
                    public void command() { System.out.println(name + " shoveling snow"); }
                },
                new Operation() {
                    public String description() { return name + " can chip ice"; }
                    public void command() { System.out.println(name + " chipping ice"); }
                },
                new Operation() {
                    public String description() { return name + " can clear the roof"; }
                    public void command() { System.out.println(name + " clearing roof"); }
                }
        );
    }
    public static void main(String[] args) {
        Robot.Test.test(new SnowRemovalRobot("Slusher"));
    }
}

// Можно предположить, что в программе будет использоваться много разновидностей Robot,
// и null-объект должен делать что-то особенное для каждого типа Robot — в данном случае
// это означает включение информации о точном типе Robot.
class NullRobotProxyHandler implements InvocationHandler {
    private String nullName;
    private Robot proxied = new NRobot();
    NullRobotProxyHandler(Class<? extends Robot> type) {
        nullName = type.getSimpleName() + " NullRobot";
    }
    private class NRobot implements Null, Robot {
        public String name() { return nullName; }
        public String model() { return nullName; }
        public List<Operation> operations() { return Collections.emptyList(); }
    }
    public Object
    invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(proxied, args);
    }
}

class NullRobot {
    public static Robot newNullRobot(Class<? extends Robot> type) {
        return (Robot) Proxy.newProxyInstance(
                NullRobot.class.getClassLoader(),
                new Class[]{ Null.class, Robot.class },
                new NullRobotProxyHandler(type));
    }
    public static void main(String[] args){
        Robot[] bots = { new SnowRemovalRobot("SnowBee"),
                newNullRobot(SnowRemovalRobot.class) };
        for (Robot bot : bots)
            Robot.Test.test(bot);
    }
}