package basics;

import rx.Observable;

import java.util.List;

/**
 * Hello world in Observable world!
 */
public class BasicObservables {
    public static void main(String[] args) {
        System.out.println("Testing basic observables");
        BasicObservables basicObservables = new BasicObservables();
        basicObservables.basicSubscribe();
    }

    Observable<String> basicCreation() {
        return Observable.create(subscriber -> {
            subscriber.onNext("Hello");
            subscriber.onNext("World");
        });
    }

    void basicSubscribe() {
        Observable<String> stringObservable = basicCreation();
        stringObservable.subscribe(s -> System.out.println(s));
    }

    Observable<String> createFromJust1(String s) {
        return Observable.just(s);
    }

    Observable<String> createFromJust2(String s1, String s2) {
        return Observable.just(s1, s2);
    }

    Observable<String> createFromFrom(List<String> list) {
        return Observable.from(list);
    }

    Observable<Integer> createError() {
        return Observable.create(subscriber -> {
            subscriber.onError(new RuntimeException("Observable creation failed!"));
        });
    }
}
