package advanced;

/**
 * Created by jobaer on 1/25/17.
 */


import rx.Observable;
import rx.schedulers.Schedulers;
import service.ArticleService;

import java.util.Random;

/**
 * Example of parallel processing.
 * Details on - http://tomstechnicalblog.blogspot.com/2015/11/rxjava-achieving-parallelization.html
 */
public final class TestConcurrency {

    private static final Random rand = new Random();

    public static void main(String[] args) {
        runParallel();
    }

    private static void runParallel() {
        Observable<Integer> vals = ArticleService.searchForArticles("whatever");
        vals
            .flatMap(val -> Observable.just(val)
                .subscribeOn(Schedulers.computation())
                .map(ArticleService::loadArticleSync))
            .subscribe(val -> System.out.println("Subscriber received "
                + val + " on "
                + threadName()));

        waitSleep();
    }

    private static void parallel2() {
        Observable<Integer> vals = Observable.range(1, 10);

        vals.flatMap(val -> Observable.just(val)
            .subscribeOn(Schedulers.computation())
            .map(TestConcurrency::intenseCalculation))
            .toList()
            .subscribe(
                val -> System.out.println("Subscriber received " + val + " on " + threadName()));
        waitSleep();
    }

    private static void sequentialOnThread() {
        Observable<Integer> vals = Observable.range(1, 10);
        vals.subscribeOn(Schedulers.computation())
            .map(TestConcurrency::intenseCalculation).subscribe(val -> {
            System.out.println("Subscriber received " + val + " on " + threadName());
        });
//        waitSleep();
    }

    private static void sequentialProcessing() {
        Observable<Integer> vals = Observable.range(1, 10);
        vals.map(TestConcurrency::intenseCalculation).subscribe(val -> {
            System.out.println("Subscriber received " + val + " on " + threadName());
        });
    }

    private static String threadName() {
        return Thread.currentThread().getName();
    }

    public static void waitSleep() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int intenseCalculation(int i) {
        try {
            System.out.println("Calculating " + i + " on " + threadName());
            Thread.sleep(randInt(500, 2000));
            return i;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
}
