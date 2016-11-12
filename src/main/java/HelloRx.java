import rx.Observable;

/**
 * Created by jobaer on 11/7/2016.
 */
public class HelloRx {
    public static void main(String[] args) {
        System.out.println("Hello, RxJava!");
        testSimpleCreation();
        testMultipleCreation();
    }

    private static void testMultipleCreation() {
        Observable<String> multi = Observable.just("Hello", "World", "!");
        multi.subscribe(System.out::println);
    }

    private static void testSimpleCreation() {
        Observable<String> helloObservable = Observable.just("Hello, World!");
        helloObservable.subscribe(System.out::println);
    }
}
