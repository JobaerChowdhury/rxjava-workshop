package basics;

import domain.Person;
import rx.Observable;
import service.PersonService;

import java.util.List;
import java.util.Optional;

import static service.PersonService.getPersonById;

/**
 * Few basic operations
 */
public class BasicOperations {

    Observable<Integer> filterEven(Observable<Integer> numbers) {
        return numbers.filter(num -> num % 2 == 0);
    }

    Observable<Person> filterBelowFifty(Observable<Person> persons) {
        return persons.filter(person -> person.getAge() < 50);
    }

    Observable<Person> getPerson(Integer id) {
        Person person = getPersonById(id);
        return Observable.just(person);
    }

    Observable<Person> getPersons(List<Integer> idList) {
        return Observable.from(idList).map(PersonService::getPersonById);
    }

    Observable<Person> getPersonSafe(Integer id) {
        Optional<Person> personOptional = PersonService.mayGetPersonById(id);
        if (personOptional.isPresent()) {
            return Observable.just(personOptional.get());
        } else {
            return Observable.empty();
        }
    }

    Observable<Person> getPersonsSafe(List<Integer> idList) {
        return Observable.from(idList).flatMap(this::getPersonSafe);
    }
}
