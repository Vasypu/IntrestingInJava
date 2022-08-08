package innerclasses;

import java.util.ArrayList;
import java.util.List;

/**
 *  Внутренние классы и система управления.
 *
 *  Еще один наглядный пример использования внутренних классов.
 */

abstract class Event {
    private long eventTime;
    protected final long delayTime;
    public Event(long delayTime) {
        this.delayTime = delayTime;
        start();
    }
    public void start() {
        eventTime = System.nanoTime() + delayTime;
    }
    public boolean ready() {
        return System.nanoTime() >= delayTime;
    }
    public abstract void action();
}

class Controller {
    private List<Event> eventList = new ArrayList<Event>();
    public void addEvent(Event c) { eventList.add(c); }
    public void run() {
        while (eventList.size() > 0) {
            for (Event e : new ArrayList<Event>(eventList))
                if (e.ready()) {
                    System.out.println(e);
                    e.action();
                    eventList.remove(e);
                }
        }
    }
}

class GreenhouseControls extends Controller {
    private boolean light = false;
    public class LightOn extends Event {
        public LightOn(long delayTime) { super(delayTime); }
        public void action() { light = true; }
        public String toString() { return "Свет включен"; }
    }
    public class LightOff extends Event {
        public LightOff(long delayTime) { super(delayTime); }
        public void action() { light = false; }
        public String toString() { return "Свет выключен"; }
    }

    private boolean water = false;
    public class WaterOn extends Event {
        public WaterOn(long delayTime) { super(delayTime); }
        public void action() { water = true; }
        public String toString() { return "Полив включен"; }
    }
    public class WaterOff extends Event {
        public WaterOff(long delayTime) { super(delayTime); }
        public void action() { water = false; }
        public String toString() { return "Полив отключен"; }
    }

    private String thermostat = "День";
    public class ThermostatNight extends Event {
        public ThermostatNight(long delayTime) { super(delayTime); }
        public void action() { thermostat = "Ночь"; }
        public String toString() { return "Термостат использует ночной режим"; }
    }
    public class ThermostatDay extends Event {
        public ThermostatDay(long delayTime) { super(delayTime); }
        public void action() { thermostat = "День"; }
        public String toString() { return "Термостат использует дневной режим"; }
    }
    public class Bell extends Event {
        public Bell(long delayTime) { super(delayTime); }
        public void action() { addEvent(new Bell(delayTime)); }
        public String toString() { return "Бам!"; }
    }
    public class Restart extends Event {
        private Event[] eventList;
        public Restart(long delayTime, Event[] eventList) {
            super(delayTime);
            this.eventList = eventList;
            for (Event e : eventList)
                addEvent(e);
        }
        public void action() {
            for (Event e : eventList) {
                e.start();
                addEvent(e);
            }
            start();
            addEvent(this);
        }
        public String toString() { return "Перезапуск системы"; }
    }

    public static class Terminate extends Event {
        public Terminate(long delayTime) { super(delayTime); }
        public void action() { System.exit(0); }
        public String toString() { return "Отключение"; }
    }
}

public class GreenhouseController {
    public static void main(String[] args) {
        GreenhouseControls gc = new GreenhouseControls();
        gc.addEvent(gc.new Bell(900));
        Event[] eventList = {
                gc.new ThermostatNight(0),
                gc.new LightOn(200),
                gc.new LightOff(400),
                gc.new WaterOn(600),
                gc.new WaterOff(800),
                gc.new ThermostatDay(1400)
        };
        gc.addEvent(gc.new Restart(2000, eventList));
        if (args.length == 1) {
            gc.addEvent(new GreenhouseControls.Terminate(Integer.parseInt(args[0])));
        }
        gc.run();
    }
}