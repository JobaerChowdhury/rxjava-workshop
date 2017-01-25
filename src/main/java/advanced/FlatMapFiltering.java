package advanced;

import rx.Observable;

import static helpers.ImplementationHelper.sleep;

/**
 * Example filtering on Observable property
 * Created by jobaer on 1/25/17.
 */
public class FlatMapFiltering {
    public static void main(String[] args) {
        Observable<Transaction> transactionObservable = filterUnderHundred();
        transactionObservable.subscribe(System.out::println);
        sleep(3000);
    }

    private static Observable<Transaction> getTransactions() {
        return TransactionService.importFromDb();
    }

    private static Observable<Transaction> filterTodaysTransaction() {
        throw new RuntimeException("Not implemented yet");
    }

    private static Observable<Transaction> filterUnderHundred() {
        throw new RuntimeException("Not implemented yet");
    }
}
