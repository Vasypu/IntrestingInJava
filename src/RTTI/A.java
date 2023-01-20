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

// Если реализовать интерфейс как закрытый внутренний класс, все равно с помощью рефлексии
// удается добраться до реализации.
class InnerA {
    private static class C implements A {
        public void f() { System.out.println("public C.f()"); }
        public void g() { System.out.println("public C.g()"); }
        void u() { System.out.println("package C.u()"); }
        protected void v() { System.out.println("protected C.v()"); }
        private void w() { System.out.println("private C.w()"); }
    }
    public static A makeA() { return new C(); }
}

class InnerImplementation {
    public static void main(String[] args) throws Exception {
        A a = InnerA.makeA();
        a.f();
        System.out.println(a.getClass().getName());
        // Reflection still gets into the private class:
        HiddenImplementation.callHiddenMethod(a, "g");
        HiddenImplementation.callHiddenMethod(a, "u");
        HiddenImplementation.callHiddenMethod(a, "v");
        HiddenImplementation.callHiddenMethod(a, "w");
    }
}

// Даже если создать анонимный класс, все равно с помощью рефлексии
// удается добраться до реализации.
class AnonymousA {
    public static A makeA() {
        return new A() {
            public void f() { System.out.println("public C.f()"); }
            public void g() { System.out.println("public C.g()"); }
            void u() { System.out.println("package C.u()"); }
            protected void v() { System.out.println("protected C.v()"); }
            private void w() { System.out.println("private C.w()"); }
        };
    }
}

class AnonymousImplementation {
    public static void main(String[] args) throws Exception {
        A a = AnonymousA.makeA();
        a.f();
        System.out.println(a.getClass().getName());
        // Отражение позволяет получить доступ к анонимному классу:
        HiddenImplementation.callHiddenMethod(a, "g");
        HiddenImplementation.callHiddenMethod(a, "u");
        HiddenImplementation.callHiddenMethod(a, "v");
        HiddenImplementation.callHiddenMethod(a, "w");
    }
}