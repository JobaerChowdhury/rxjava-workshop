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

    /**
     * Creates an Observable<String> that emits two strings - "Hello" and "World"
     */
    Observable<String> basicCreation() {
        throw new RuntimeException("Not implemented");
    }

    void basicSubscribe() {
        Observable<String> stringObservable = basicCreation();
        stringObservable.subscribe(s -> System.out.println(s));
    }

    Observable<String> createFromJust1(String s) {
        throw new RuntimeException("Not implemented");
    }

    Observable<String> createFromJust2(String s1, String s2) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Create a Observable<String> from the given list parameter
     */
    Observable<String> createFromList(List<String> list) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Create an Observable that throws an error.
     */
    Observable<Integer> createError() {
        throw new RuntimeException("Not implemented");
    }
}
