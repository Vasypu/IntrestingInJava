package containers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Vector;

/**
 *  Vector и Enumeration - древние контейнеры
 */
public class Enumerations {
    public static void main(String[] args) {
        Vector<String> v = new Vector<String>(Countries.names(10));
        Enumeration<String> e = v.elements();
        while (e.hasMoreElements())
            System.out.print(e.nextElement() + ", ");
        // Получение Enumeration для Collection:
        //e = Collection.enumeration(new ArrayList<String>()); // уже не поддерживается в новых версиях java
    }
}
