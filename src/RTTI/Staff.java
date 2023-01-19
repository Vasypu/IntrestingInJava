package RTTI;

/**
 *  Null-объекты
 *  <p>
 *  Если отсутствие объекта представляется встроенным значением null, то ссылку при каждом
 *  использовании приходится проверять на null. Многочисленные проверки утомительны, а код
 *  становится громоздким. Проблема в том, что null не имеет собственного поведения (если
 *  не считать возбуждения исключения NullPointerException при попытке выполнения операции).
 *  Иногда в таких ситуациях помогает концепция null-объекта2, принимающего сообщения для
 *  объекта, который он «изображает», но возвращающего значения, указывающие на отсутствие
 *  «настоящего» объекта
 */

import java.util.ArrayList;

interface Null {}

class Person {
    public final String first;
    public final String last;
    public final String address;

    public Person(String first, String last, String address) {
        this.first = first;
        this.last = last;
        this.address = address;
    }
    public String toString() { return "Person: " + first + " " + last + " " + address; }
    public static class NullPerson extends Person implements Null {
        private NullPerson() { super("None", "None", "None"); }
        public String toString() { return "NullPerson"; }
    }

    public static final Person NULL = new NullPerson();
}

class Position {
    private String title;
    private Person person;
    public Position(String jobTitle, Person employee) {
        title = jobTitle;
        person = employee;
        if (person == null) person = Person.NULL;
    }

    public Position(String jobTitle) {
        title = jobTitle;
        person = Person.NULL;
    }
    public String getTitle() { return title; }
    public void setTitle(String newTitle) { title = newTitle; }

    public Person getPerson() { return person; }
    public void setPerson(Person newPerson) {
        person = newPerson;
        if (person == null)
            person = Person.NULL;
    }
    public String toString() {
        return "Position: " + title + " " + person;
    }
}

public class Staff extends ArrayList<Position> {
    public void add(String title, Person person) {
        add(new Position(title, person));
    }

    public void add(String... titles) {
        for (String title : titles)
            add(new Position(title));
    }

    public Staff(String... titles) { add(titles); }

    public boolean positionAvailable(String title) {
        for(Position position : this)
            if(position.getTitle().equals(title) && position.getPerson() == Person.NULL)
                return true;
        return false;
    }

    public void fillPosition(String title, Person hire) {
        for (Position position : this)
            if (position.getTitle().equals(title) && position.getPerson() == Person.NULL) {
                position.setPerson(hire);
                return;
            }
        throw new RuntimeException("Position " + title + " not available");
    }

    public static void main(String[] args) {
        Staff staff = new Staff("President", "СТО",
                "Marketing Manager", "Product Manager",
                "Project Lead", "Software Engineer",
                "Software Engineer", "Software Engineer",
                "Software Engineer", "Test Engineer",
                "Technical Writer");
        staff.fillPosition("President", new Person("Me", "Last", "The Top, Lonely At"));
        staff.fillPosition("Project Lead", new Person("Janet", "Planner", "The Burbs"));
        if(staff.positionAvailable("Software Engineer"))
            staff.fillPosition("Software Engineer", new Person("Bob", "Coder", "Bright Light City"));
        System.out.println(staff);
    }
}
