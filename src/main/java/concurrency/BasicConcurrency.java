package concurrency;

import rx.Observable;
import rx.schedulers.Schedulers;

import static helpers.ImplementationHelper.sleep;
import static helpers.ImplementationHelper.threadName;

public class BasicConcurrency {
    public static void main(String[] args) {
        testObserveOn();
    }

    private static void basicSubscribe() {
        Observable<String> values = Observable.just("Alpha", "Beta", "Gamma");
        Observable<Integer> lengths = values.map(String::length);

        lengths.subscribe(len -> {
            System.out.println("Received " + len + " on thread " + threadName());
        });
    }

    private static void testSubscribeOn() {
        Observable<String> values = Observable.just("Alpha", "Beta", "Gamma");

        values.subscribeOn(Schedulers.io())
                .map(String::length)
                .subscribe(len -> {
                    System.out.println("Received " + len + " on thread " + threadName());
                });
        sleep(3000);
    }

    private static void testObserveOn() {
        Observable<Integer> source = Observable.range(1, 10);

        source.map(i -> i * 100)
                .doOnNext(i -> System.out.println("Emitting " + i + " on thread " + threadName()))
                .observeOn(Schedulers.computation())
                .map(i -> i * 10)
                .subscribe(i -> System.out.println("Received " + i + " on thread " + threadName()));

        sleep(3000);
    }
}
