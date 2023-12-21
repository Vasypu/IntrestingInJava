package containers;

import java.lang.ref.*;
import java.util.LinkedList;

/**
 *  Удержание ссылок
 *  <p>
 *  Библиотека java.lang.ref содержит набор классов, которые позволяют проводить уборку мусора с большей гибкостью
 *  и особенно полезны при наличии в программе больших объектов, потенциально опасных в смысле нехватки памяти.
 *  <p>
 *  Weak References
 *  Если JVM обнаружит объект, имеющий только слабые ссылки (т.е. ни одной сильной или мягкой ссылки, связанной с
 *  каким-либо объектом), этот объект будет помечен для сбора мусора. Для создания таких ссылок используется класс
 *  java.lang.ref.WeakReference. Эти ссылки используются в приложениях реального времени при создании DBConnection.
 *  <p>
 *  Soft References
 *  Мягкие ссылки: В мягких ссылках, даже если объект свободен для сборки мусора, он также не собирается, пока JVM
 *  сильно не нуждается в памяти. Объекты очищаются из памяти, когда JVM исчерпывает память. Для создания таких ссылок
 *  используется класс java.lang.ref.SoftReference.
 *  Phantom References
 *  Фантомные ссылки: Объекты, на которые ссылаются фантомные ссылки, подлежат сборке мусора. Но прежде чем удалить их
 *  из памяти, JVM помещает их в очередь, называемую "очередью ссылок". Они попадают в очередь ссылок после вызова метода
 *  finalize(). Для создания таких ссылок используется класс java.lang.ref.PhantomReference.
 */
class VeryBig {
    private static final int SIZE = 10000;
    private long[] la = new long[SIZE];
    private String ident;
    public VeryBig(String id) { ident = id; }
    public String toString() { return ident; }
    protected void finalize() {
        System.out.println("Finalizing " + ident);
    }
}

public class References {
    private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<VeryBig>();

    public static void checkQueue() {
        Reference<? extends VeryBig> inq = rq.poll();
        if (inq != null)
            System.out.println("In queue: " + inq.get());
    }

    public static void main(String[] args) {
        int size = 10;
        // Или использовать размер, заданный в командной строке:
        if(args.length > 0)
            size = new Integer(args[0]);

        LinkedList<SoftReference<VeryBig>> sa = new LinkedList<SoftReference<VeryBig>>();
        for(int i = 0; i < size; i++) {
            sa.add(new SoftReference<VeryBig>(new VeryBig("Soft " + i), rq));
            System.out.println("Just created: " + sa.getLast());
            checkQueue();
        }

        LinkedList<WeakReference<VeryBig>> wa = new LinkedList<WeakReference<VeryBig>>();
        for(int i = 0; i < size; i++) {
            wa.add(new WeakReference<VeryBig>(new VeryBig("Weak " + i), rq));
            System.out.println("Just created: " + wa.getLast());
            checkQueue();
        }

        SoftReference<VeryBig> s = new SoftReference<VeryBig>(new VeryBig("Soft"));
        WeakReference<VeryBig> w = new WeakReference<VeryBig>(new VeryBig("Weak"));
        System.gc();
        LinkedList<PhantomReference<VeryBig>> pa = new LinkedList<PhantomReference<VeryBig>>();
        for(int i = 0; i < size; i++) {
            pa.add(new PhantomReference<VeryBig>(new VeryBig("Phantom " + i), rq));
            System.out.println("Just created: " + pa.getLast());
            checkQueue();
        }
    }
}