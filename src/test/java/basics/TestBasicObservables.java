package basics;

import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jobaer on 11/8/2016.
 */
public class TestBasicObservables {
    @Test
    public void testBasicCreation() {
        BasicObservables basicObservables = new BasicObservables();
        Observable<String> stringObservable = basicObservables.basicCreation();

        TestSubscriber<String> subscriber = new TestSubscriber<>();
        stringObservable.subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertValues("Hello", "World");
        subscriber.onCompleted();
    }

    @Test
    public void testCreateFromJust1() {
        BasicObservables basicObservables = new BasicObservables();
        TestSubscriber<String> subscriber = new TestSubscriber<>();
        Observable<String> rxjava = basicObservables.createFromJust1("rxjava");
        rxjava.subscribe(subscriber);

        subscriber.assertNoErrors();
        subscriber.assertValue("rxjava");
        subscriber.assertCompleted();
    }

    @Test
    public void testCreateFromJust2() {
        BasicObservables basicObservables = new BasicObservables();
        Observable<String> rxjava = basicObservables.createFromJust2("rxjava", "rocks");
        TestSubscriber<String> subscriber = new TestSubscriber<>();
        rxjava.subscribe(subscriber);

        subscriber.assertNoErrors();
        subscriber.assertValues("rxjava", "rocks");
        subscriber.assertCompleted();
    }

    @Test
    public void testCreateFromFrom() {
        BasicObservables basicObservables = new BasicObservables();
        List<String> stringList = Arrays.asList("I", "am", "learning", "rx");
        Observable<String> observable = basicObservables.createFromFrom(stringList);
        TestSubscriber<String> subscriber = new TestSubscriber<>();
        observable.subscribe(subscriber);

        subscriber.assertNoErrors();
        subscriber.assertReceivedOnNext(stringList);
        subscriber.assertCompleted();
    }

    @Test
    public void testCreateError() {
        BasicObservables basicObservables = new BasicObservables();
        Observable<Integer> observable = basicObservables.createError();
        TestSubscriber<Integer> subscriber = new TestSubscriber<>();
        observable.subscribe(subscriber);

        subscriber.assertError(RuntimeException.class);
    }
}
