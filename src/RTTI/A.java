package RTTI;

/**
 *  Интерфейсы и информация типов
 *  <p>
 *  Одной из важных целей ключевого слова interface является возможность изоляции компонентов,
 *  снижающая связность системы. Программирование на уровне интерфейсов позволяет достичь этой
 *  цели, но информация типа позволяет обойти это ограничение — интерфейсы не обеспечивают
 *  стопроцентной гарантии снижения связности.
 */

import RTTI.packageaccess.HiddenC;
import java.lang.reflect.Method;

public interface A { void f(); }

class B implements A {
    public void f() {}
    public void g() {}
}

// Затем интерфейс реализуется, но разработчик при желании может добраться до фактического
// типа реализации
class InterfaceViolation {
    public static void main(String[] args) {
        A a = new B();
        a.f();
        // a.g(); // Ошибка компиляции
        System.out.println(a.getClass().getName());
        if(a instanceof B) {
            B b = (B) a;
            b.g();
        }
    }
}

// Проще всего использовать для реализации доступ на уровне пакета, чтобы внешние клиенты
// не могли видеть ее
class HiddenImplementation {
    public static void main(String[] args) throws Exception {
        A a = HiddenC.makeA();
        a.f();
        System.out.println(a.getClass().getName());
        // Ошибка компиляции: не удается найти имя 'C':
        /* if(a instanceof С) {
        С с = (C)a;
        c.g();
    } */
        // Сюрприз! Рефлексия позволяет вызвать g():
        callHiddenMethod(a, "g");
        // И даже менее доступные методы!
        callHiddenMethod(a, "u");
        callHiddenMethod(a, "v");
        callHiddenMethod(a, "w");
    }

// Как видите, рефлексия позволяет добраться до любых методов и вызвать даже закрытые методы.
// Зная имя метода, можно вызвать setAccessible(true) для объекта Method.
    static void callHiddenMethod(Object a, String methodName) throws Exception {
        Method g = a.getClass().getDeclaredMethod(methodName);
        g.setAccessible(true);
        g.invoke(a);
    }
}
