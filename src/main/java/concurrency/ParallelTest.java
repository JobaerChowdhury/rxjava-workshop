package concurrency;

import rx.Observable;
import rx.schedulers.Schedulers;

import static helpers.ImplementationHelper.randInt;
import static helpers.ImplementationHelper.sleep;
import static helpers.ImplementationHelper.threadName;

/**
 * Example of parallel processing.
 * Details on - http://tomstechnicalblog.blogspot.com/2015/11/rxjava-achieving-parallelization.html
 */
public final class ParallelTest {
    public static void main(String[] args) {
        parallel2();
    }

    private static void runParallel() {
        Observable<Integer> vals = Observable.range(1, 10);
        vals.flatMap(val -> Observable.just(val)
                .subscribeOn(Schedulers.computation())
                .map(ParallelTest::intenseCalculation))
                .subscribe(val -> System.out.println("Subscriber received "
                        + val + " on "
                        + threadName()));

        sleep(10000);
    }

    private static void parallel2() {
        Observable<Integer> vals = Observable.range(1, 10);

        vals.flatMap(val -> Observable.just(val)
                .subscribeOn(Schedulers.computation())
                .map(ParallelTest::intenseCalculation))
                .toList()
                .subscribe(
                        val -> System.out.println("Subscriber received " + val + " on " + threadName()));
        sleep(10000);
    }

    private static void sequentialOnThread() {
        Observable<Integer> vals = Observable.range(1, 10);
        vals.subscribeOn(Schedulers.computation())
                .map(ParallelTest::intenseCalculation).subscribe(val -> {
            System.out.println("Subscriber received " + val + " on " + threadName());
        });
    }

    private static void sequentialProcessing() {
        Observable<Integer> vals = Observable.range(1, 10);
        vals.map(ParallelTest::intenseCalculation).subscribe(val -> {
            System.out.println("Subscriber received " + val + " on " + threadName());
        });
    }

    private static int intenseCalculation(int i) {
        try {
            System.out.println("Calculating " + i + " on " + threadName());
            Thread.sleep(randInt(500, 2000));
            return i;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}