package RTTI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *  Фабрика.
 *  <p>
 *  Используется фабричный метод create() для создания дочерних объектов Part.
 */

// Параметр-тип T позволяет create() возвращать разные типы для разных реализаций Factory.
interface Factory<T> { T create(); }

class Part {
    public String toString() {
        return getClass().getSimpleName();
    }

    static List<Factory<? extends Part>> partFactories = new ArrayList<Factory<? extends Part>>();

    static {
        // Для Collections.addAll() выдается предупреждение
        // "неконтролируемое создание обобщенного массива"
        partFactories.add(new FuelFilter.Factory());
        partFactories.add(new AirFilter.Factory());
        partFactories.add(new CabinAirFilter.Factory());
        partFactories.add(new OilFilter.Factory());
        partFactories.add(new FanBelt.Factory());
        partFactories.add(new PowerSteeringBelt.Factory());
        partFactories.add(new GeneratorBelt.Factory());
    }

    private static Random rand = new Random(47);

    public static Part createRandom() {
        int n = rand.nextInt(partFactories.size());
        return partFactories.get(n).create();
    }
}

class Filter extends Part {}

class FuelFilter extends Filter {
    // Создание фабрики класса для каждого конкретного типа:
    static class Factory implements RTTI.Factory<FuelFilter> {
        public FuelFilter create () { return new FuelFilter(); }
    }
}

class AirFilter extends Filter {
    public static class Factory implements RTTI.Factory<AirFilter> {
        public AirFilter create() { return new AirFilter(); }
    }
}

class CabinAirFilter extends Filter {
    public static class Factory implements RTTI.Factory<CabinAirFilter> {
        public CabinAirFilter create() { return new CabinAirFilter(); }
    }
}

class OilFilter extends Filter {
    public static class Factory implements RTTI.Factory<OilFilter> {
        public OilFilter create() { return new OilFilter(); }
    }
}

class Belt extends Part {}

class FanBelt extends Belt {
    public static class Factory implements RTTI.Factory<FanBelt> {
        public FanBelt create() { return new FanBelt(); }
    }
}

class GeneratorBelt extends Belt {
    public static class Factory implements RTTI.Factory<GeneratorBelt> {
        public GeneratorBelt create() { return new GeneratorBelt(); }
    }
}

class PowerSteeringBelt extends Belt {
    public static class Factory implements RTTI.Factory<PowerSteeringBelt> {
        public PowerSteeringBelt create() { return new PowerSteeringBelt(); }
    }
}

public class RegisteredFactories {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++)
            System.out.println(Part.createRandom());
    }
}
