package concurrency;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

import static helpers.ImplementationHelper.sleep;

public class BasicConcurrency {
    public static void main(String[] args) {
        System.out.println("Testing basic concurrency");
        createBasicThread();
        sleep(1000);
    }

    private static void createBasicThread() {
        Observable<Object> words = Observable.create(s -> {
            new Thread(() -> {
                System.out.println("Creating on " + Thread.currentThread());
                s.onNext("one");
                s.onNext("two");
                s.onNext("three");
                s.onNext("four");
                s.onCompleted();
            }).start();
        });

        words.subscribeOn(Schedulers.newThread()).
                subscribe(s -> {
            System.out.println("Subscribe on " + Thread.currentThread());
            System.out.println(s);
        });
        System.out.println("After something ");
    }

    private static void syncAsyncMix() {
        Observable<Long> asyncObservable = Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(10);
        asyncObservable
                .doOnNext(i -> System.out.println(Thread.currentThread()))
                .filter(i -> i % 2 == 0)
                .map(i -> "Value " + i + " processed on " + Thread.currentThread())
                .subscribe(s -> System.out.println("SOME VALUE => " + s + ". Current thread " + Thread.currentThread()));
        System.out.println("Will print BEFORE values are emitted " + Thread.currentThread());
    }

    private static void checkOperators() {
        Observable<Integer> numbers = Observable.create(s -> {
            s.onNext(1);
            s.onNext(2);
            s.onNext(3);
            s.onCompleted();
        });

        numbers.map(i -> i * 2).subscribe(System.out::println);
    }

    private static void basicCreation() {
        Observable<String> hello = Observable.create(s -> {
            s.onNext("Hello");
            s.onNext("World");
            s.onCompleted();
        });

        hello.subscribe(System.out::println);
    }

    private static void createInterval() {
        Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(10)
                .subscribe(System.out::println);
    }


}
