package containers;

import colections.MapOfList;
import colections.Pet;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

// Так как у всех питомцев в этом примере есть имена, они сортируются сначала по типу,
// затем по имени внутри типа.
public class Individual implements Comparable<Individual> {
    private static long counter = 0;
    private final long id = counter++;
    private String name;

    public Individual(String name) { this.name = name; }

    // Параметр 'name' не обязателен:
    public Individual() { }

    public String toString() { return getClass().getSimpleName() + (name == null ? "" : " " + name); }
    public long id() { return id; }
    public boolean equals(Object o) { return o instanceof Individual && id == ((Individual) o).id; }

    public int hashCode() {
        int result = 17;
        if (name != null)
            result = 37 * result + name.hashCode();
        result = 37 * result + (int) id;
        return result;
    }

    public int compareTo(Individual arg) {
        // Сначала сравнить по имени класса:
        String first = getClass().getSimpleName();
        String argFirst = arg.getClass().getSimpleName();
        int firstCompare = first.compareTo(argFirst);
        if (firstCompare != 0) return firstCompare;
        if (name != null && arg.name != null) {
            int secondCompare = name.compareTo(arg.name);
            if (secondCompare != 0) return secondCompare;
        }
        return (arg.id < id ? -1 : (arg.id == id ? 0 : 1));
    }
}

class IndividualTest {
    public static void main(String[] args) {
        Set<Individual> pets = new TreeSet<Individual>();
        for (List<? extends Pet> lp : MapOfList.petPeople.values())
            for (Pet p : lp)
                pets.add(p);
        System.out.println(pets);
    }
}