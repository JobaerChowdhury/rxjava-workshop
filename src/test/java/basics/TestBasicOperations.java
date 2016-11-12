package basics;

import domain.Person;
import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;
import service.PersonService;

import java.util.Arrays;
import java.util.List;

import static service.PersonService.getPersonById;

public class TestBasicOperations {
    @Test
    public void testFilterEven() {
        Observable<Integer> numbers1 = Observable.from(Arrays.asList(12, 23, 11, 12, 22, 32, 989));
        BasicOperations basics = new BasicOperations();
        TestSubscriber<Integer> subscriber = new TestSubscriber<>();

        Observable<Integer> odds1 = basics.filterEven(numbers1);
        odds1.subscribe(subscriber);

        subscriber.assertNoErrors();
        subscriber.assertValues(23, 11, 989);
        subscriber.assertCompleted();
    }

    @Test
    public void testFilterBelowFifty() {
        Observable<Person> personObservable = Observable.from(PersonService.getAllPersons());
        BasicOperations basicOperations = new BasicOperations();
        TestSubscriber<Person> subscriber = new TestSubscriber<>();

        Observable<Person> result = basicOperations.filterBelowFifty(personObservable);
        result.subscribe(subscriber);

        subscriber.assertNoErrors();
        subscriber.assertValueCount(2);
        subscriber.assertCompleted();
    }

    @Test
    public void testGetPerson() {
        BasicOperations basicOperations = new BasicOperations();
        TestSubscriber<Person> subscriber = new TestSubscriber<>();

        Observable<Person> personObservable = basicOperations.getPerson(2);
        personObservable.subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertValue(getPersonById(2));
    }

    @Test
    public void testGetPersons() {
        BasicOperations basicOperations = new BasicOperations();
        TestSubscriber<Person> subscriber = new TestSubscriber<>();

        Observable<Person> personObservable = basicOperations.getPersons(Arrays.asList(1, 2, 3));
        personObservable.subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertValues(getPersonById(1), getPersonById(2), getPersonById(3));
        subscriber.assertCompleted();
    }

    @Test
    public void testGetPersonSafe() {
        BasicOperations basicOperations = new BasicOperations();

        TestSubscriber<Person> subscriber = new TestSubscriber<>();
        Observable<Person> personObservable = basicOperations.getPersonSafe(2);
        personObservable.subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertValue(getPersonById(2));

        TestSubscriber<Person> subscriber2 = new TestSubscriber<>();
        Observable<Person> nonExistent = basicOperations.getPersonSafe(21);
        nonExistent.subscribe(subscriber2);
        subscriber2.assertNoErrors();
        subscriber2.assertCompleted();
    }

    @Test
    public void testGetPersonsSafe() {
        BasicOperations basicOperations = new BasicOperations();
        TestSubscriber<Person> subscriber = new TestSubscriber<>();

        Observable<Person> personObservable = basicOperations.getPersonsSafe(Arrays.asList(1, 21, 4));
        personObservable.subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertValues(getPersonById(1), getPersonById(4));
        subscriber.assertCompleted();
    }

}
