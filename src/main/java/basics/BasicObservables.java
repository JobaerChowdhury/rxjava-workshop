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

    Observable<String> createFromFrom(List<String> list) {
        throw new RuntimeException("Not implemented");
    }

    Observable<Integer> createError() {
        throw new RuntimeException("Not implemented");
    }
}
