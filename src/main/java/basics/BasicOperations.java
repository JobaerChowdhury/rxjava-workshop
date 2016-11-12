package basics;

import domain.Person;
import rx.Observable;
import service.PersonService;

import java.util.List;
import java.util.Optional;

/**
 * Few basic operations
 */
public class BasicOperations {
    public static void main(String[] args) {
        System.out.println("Testing basic operations");
    }

    Observable<Integer> filterEven(Observable<Integer> numbers) {
        return numbers.filter(n -> n % 2 != 0);
    }

    Observable<Person> filterBelowFifty(Observable<Person> persons) {
        return persons.filter(p -> p.getAge() > 50);
    }

    Observable<Person> getPerson(Integer id) {
        return Observable.just(id).map(i -> PersonService.getPersonById(i));
    }

    Observable<Person> getPersons(List<Integer> idList) {
        return Observable.from(idList).map(id -> PersonService.getPersonById(id));
    }

    Observable<Person> getPersonSafe(Integer id) {
        return Observable.just(id).flatMap(i -> {
            Optional<Person> person = PersonService.mayGetPersonById(i);
            if (person.isPresent()) {
                return Observable.just(person.get());
            } else {
                return Observable.empty();
            }
        });
    }

    Observable<Person> getPersonsSafe(List<Integer> idList) {
        return Observable.from(idList).flatMap(id -> getPersonSafe(id));
    }
}
