package final_class_and_methods;

class Gizmo {
    public void spin() {}
}

public class FinalArguments {
    void with(final Gizmo g) {
//        g = new Gizmo();                  //нельзя изменять final аргументы метода
    }

    void without(Gizmo g) {
        g = new Gizmo();
        g.spin();
    }
//    void f(final int i) {i++;}            //нельзя изменять final аргументы метода
    int g(final int i) { return i + 1; }

    public static void main(String[] args) {
        FinalArguments bf = new FinalArguments();
        bf.without(null);
//        bf.with(new Gizmo());
        bf.with(null);
    }
}
