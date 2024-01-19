package io;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class OSExecute {
    public static void command(String command) {
        boolean err = false;
        try {
            Process process = new ProcessBuilder(command.split(" ")).start();
            BufferedReader results = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            while ((s = results.readLine()) != null)
                System.out.println(s);
            BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            // Оповещение oб ошибках и возврат ненулевого значения вызывающему процессу при возникновении проблем:
            while((s = errors.readLine())!= null) {
                System.err.println(s);
                err = true;
            }
        } catch(Exception e) {
            // Для системы Windows 2000, которая выдает исключение для командной строки по умолчанию:
            if(!command.startsWith("CMD /С")) command("CMD /С " + command);
            else
                throw new RuntimeException(e);
        }
        if(err)
            throw new OSExecuteException("Errors executing " + command);
    }
}

class OSExecuteException extends RuntimeException { public OSExecuteException(String why) { super(why); } }

class OSExecuteDemo {
    public static void main(String[] args) {
        OSExecute.command("javap OSExecuteDemo");
    }
}