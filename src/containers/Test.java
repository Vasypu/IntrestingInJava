package containers;

// Среда для проведения хронометражного тестирования контейнеров
public abstract class Test<C> {
    String name;

    public Test(String name) { this.name = name; }
    // Метод переопределяется для разных тестов.
    // Возвращает фактическое количество повторений теста
    abstract int test(C container, TestParam tp);
}