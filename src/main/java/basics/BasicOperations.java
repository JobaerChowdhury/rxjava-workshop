package basics;

import domain.Person;
import rx.Observable;

import java.util.List;

/**
 * Few basic operations
 */
public class BasicOperations {

    Observable<Integer> filterEven(Observable<Integer> numbers) {
        throw new RuntimeException("Not implemented");
    }

    Observable<Person> filterBelowFifty(Observable<Person> persons) {
        throw new RuntimeException("Not implemented");
    }

    Observable<Person> getPerson(Integer id) {
        throw new RuntimeException("Not implemented");
    }

    Observable<Person> getPersons(List<Integer> idList) {
        throw new RuntimeException("Not implemented");
    }

    Observable<Person> getPersonSafe(Integer id) {
        throw new RuntimeException("Not implemented");
    }

    Observable<Person> getPersonsSafe(List<Integer> idList) {
        throw new RuntimeException("Not implemented");
    }
}
