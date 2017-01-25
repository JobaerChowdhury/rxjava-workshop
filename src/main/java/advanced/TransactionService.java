package advanced;

import rx.Observable;

import static helpers.ImplementationHelper.withRandomDelay;

/**
 * A transaction service
 * <p>
 * Created by jobaer on 1/25/17.
 */
public class TransactionService {
    public static Observable<Transaction> importFromDb() {
        return withRandomDelay(Observable.range(1, 20).map(TransactionService::createDummyTransaction));
    }

    private static Transaction createDummyTransaction(int id) {
        return new TransactionImpl(id);
    }
}
