package RTTI;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 *  Если точный тип объекта неизвестен, RTTI сообщит вам его. Однако в этом случае существуют ограничения:
 *  тип должен быть известен еще во время компиляции программы, иначе определить его с помощью RTTI и сделать
 *  с этой информацией что-то полезное будет невозможно. Другими словами, компилятор должен располагать
 *  информацией обо всех классах, с которыми вы работаете.
 *  <p>
 *  Класс Class вводит понятие (reflection), для которого существует
 *  дополнительная библиотека java.lang.reflect, состоящая из классов Field, Method и Constructor (каждый
 *  реализует интерфейс Member). Объекты этих классов создаются JVM, чтобы представлять соответствующие
 *  члены неизвестного класса.
 *  <p>
 *  Важно понимать, что в механизме reflection нет ничего сверхъестественного. Когда вы используете reflection
 *  для общения с объектом неизвестного типа, виртуальная машина JVM рассматривает его и видит, что он
 *  принадлежит определенному классу (это делает и обычное RTTI), но перед тем как проводить с ним некоторые
 *  действия, необходимо загрузить соответствующий объекту файл .class. Таким образом, файл .class для класса
 *  этого объекта должен быть доступен JVM либо в сети, либо в локальной системе. Отсюда заключение: разница
 *  между традиционным RTTI и отражением в том, что при использовании RTTI файл .class открывается и изучается
 *  компилятором.
 */

// программа выводит методы и конструкторы переданного класса
public class ShowMethods {
    private static String usage = "usage:\n" +
                    "ShowMethods qualified.class.name\n" +
                    "То show all methods in class or:\n" +
                    "ShowMethods qualified.class.name word\n" +
                    "То search for methods involving 'word'";
    private static Pattern p = Pattern.compile("\\w+\\.");  // сократит название метода с RTTI.ShowMethods.main до main
    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println(usage);
            System.exit(0);
        }
        int lines = 0;
        try {
            Class<?> c = Class.forName(args[0]);
            Method[] methods = c.getMethods();
            Constructor[] ctors = c.getConstructors();
            if(args.length == 1) {
                for (Method method : methods) {
                    System.out.println(p.matcher(method.toString()).replaceAll(""));
                }
                for (Constructor ctor : ctors) {
                    System.out.println(p.matcher(ctor.toString()).replaceAll(""));
                }
                lines = methods.length + ctors.length;
            } else {
                for(Method method : methods)
                    if(method.toString().indexOf(args[1]) != -1) {
                        System.out.println(p.matcher(method.toString()).replaceAll(""));
                        lines++;
                    }
                for(Constructor ctor : ctors)
                    if(ctor.toString().indexOf(args[1]) != -1) {
                        System.out.println(p.matcher(ctor.toString()).replaceAll(""));
                        lines++;
                    }
            }
            System.out.println("lines " + lines);
        } catch(ClassNotFoundException e){
            System.out.println("No such class: " + e);
        }
    }
}
