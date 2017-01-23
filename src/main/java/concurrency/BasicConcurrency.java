package concurrency;

import rx.Observable;

import static helpers.ImplementationHelper.threadName;

public class BasicConcurrency {
    public static void main(String[] args) {
        basicSubscribe();
    }

    private static void basicSubscribe() {
        Observable<String> values = Observable.just("Alpha", "Beta", "Gamma");
        Observable<Integer> lengths = values.map(String::length);

        lengths.subscribe(len -> {
            System.out.println("Received " + len + " on thread " + threadName());
        });
    }
}
