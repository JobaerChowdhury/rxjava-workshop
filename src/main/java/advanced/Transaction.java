package advanced;

import rx.Observable;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Simple Transaction interface
 * <p>
 * Created by jobaer on 1/25/17.
 */
public interface Transaction {
    int getID();

    LocalDate getDate();

    Integer getAmount();

    Observable<Integer> getBalance();
}
