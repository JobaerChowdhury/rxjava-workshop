package advanced;

import rx.Observable;

import java.time.LocalDate;

import static helpers.ImplementationHelper.random;
import static helpers.ImplementationHelper.withRandomDelay;

/**
 * Implementation of Transaction class
 * <p>
 * Created by jobaer on 1/25/17.
 */
public class TransactionImpl implements Transaction {
    private int id;
    private LocalDate date;
    private int amount;

    public TransactionImpl(int id) {
        this.id = id;

        LocalDate today = LocalDate.now();
        int random = random(0, 2);
        this.date = today.minusDays(random);

        this.amount = random(1, 100);
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public Integer getAmount() {
        return amount;
    }

    @Override
    public Observable<Integer> getBalance() {
        int balance = this.amount * 10;
        Observable<Integer> balanceObservable = Observable.just(balance);
        return withRandomDelay(balanceObservable);
    }

    @Override
    public String toString() {
        return "TransactionImpl{" +
            "id=" + id +
            ", date=" + date +
            ", amount=" + amount +
            '}';
    }
}
