package io;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *  Управление процессами
 *  <p>
 *  Часто в программах Java возникает необходимость выполнения других программ операционной системы, с управлением
 *  вводом и выводом таких программ.
 *  <p>
 *  Для получения стандартного вывода от выполняемой программы вызывается метод getInputStream(). Он возвращает объект
 *  InputStream, из которого можно читать данные.
 *  Результаты программы передаются по строкам, поэтому для их чтения используется метод readLine(). В нашем примере
 *  строки просто выводятся на консоль, но их также можно сохранить и вернуть из метода command().
 *  Ошибки программы направляются в стандартный поток ошибок и сохраняются вызовом getErrorStream(). Если во время
 *  выполнения происходили ошибки, то информация о них выводится и выдается исключение OSExecuteException, чтобы вызывающая
 *  программа могла обработать проблему.
 */
public class OSExecute {
    public static void command(String command) {
        boolean err = false;
        try {
            Process process = new ProcessBuilder(command.split(" ")).start();
            BufferedReader results = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            while((s = results.readLine()) != null)
                System.out.println(s);
            BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            // Оповещение oб ошибках и возврат ненулевого значения вызывающему процессу при возникновении проблем:
            while((s = errors.readLine()) != null) {
                System.err.println(s);
                err = true;
            }
        } catch(Exception e) {
            // Для системы Windows 2000, которая выдает исключение для командной строки по умолчанию:
            if(!command.startsWith("CMD /С"))
                command("CMD /С " + command);
            else
                throw new RuntimeException(e);
        }
        if(err)
            throw new OSExecuteException("Errors executing " + command);
    }
}

class OSExecuteException extends RuntimeException {
    public OSExecuteException(String why) { super(why); }
}

class OSExecuteDemoTwo {
    public static void main(String[] args) {
        OSExecute.command("javap out/production/java/io/OSExecuteDemo");
    }
}