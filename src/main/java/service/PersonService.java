package service;

import domain.Person;

import java.util.*;

/**
 * A dummy Person service
 */
public class PersonService {
    private static Map<Integer, Person> personTable = new HashMap<Integer, Person>() {
        {
            put(1, new Person(1, "Jakob", 23));
            put(2, new Person(2, "Reidar", 40));
            put(3, new Person(3, "David", 45));
            put(4, new Person(4, "Ann", 52));
            put(5, new Person(5, "Lise", 38));
            put(6, new Person(6, "Kjetil", 45));
            put(7, new Person(7, "Petter", 51));
        }
    };

    public static List<Person> getAllPersons() {
        return new ArrayList<>(personTable.values());
    }

    public static Person getPersonById(Integer id) {
        return personTable.get(id);
    }

    public static Optional<Person> mayGetPersonById(Integer id) {
        return personTable.containsKey(id) ? Optional.of(personTable.get(id)) : Optional.empty();
    }
}
