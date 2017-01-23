package helpers;

import org.apache.commons.lang3.NotImplementedException;
import rx.Observable;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ImplementationHelper {
    private static final Random rand = new Random();

    public static <T> T todo() {
        throw new NotImplementedException("Todo");
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static <T> Observable<T> withRandomDelay(Observable<T> sourceObservable) {
        int randomDelay = ThreadLocalRandom.current().nextInt(500, 1000);
        return sourceObservable.delay(randomDelay, TimeUnit.MILLISECONDS);
    }

    public static String threadName() {
        return Thread.currentThread().getName();
    }

    public static int randInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
