package com.citi.card;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * == electronic pre-paid cash card ==
 * <p>
 * Loading money onto the card.
 * · Spending credit balances from the card.
 * · Using the card simultaneously at multiple retailers.
 */
public final class Card {

    /*
        Assuming negative balances are not allowed
    */
    private BigDecimal balance;
    private static final BigDecimal ZERO_VALUE = new BigDecimal("0");
    private final Lock lock = new ReentrantLock();

    Card() {
        this(ZERO_VALUE);
    }

    Card(BigDecimal balance) {
        setBalance(balance);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    private void setBalance(BigDecimal balance) {
        checkNegativeBalance(balance);
        this.balance = balance;
    }

    public void loadMoney(BigDecimal amount) {
        lock.lock();
        setBalance(this.balance.add(amount));
        lock.unlock();
    }

    public void debit(BigDecimal amount) {
        lock.lock();
        setBalance(this.balance.subtract(amount));
        lock.unlock();
    }

    private void checkNegativeBalance(BigDecimal result) {
        if (result.compareTo(ZERO_VALUE) < 0) {
            throw new NegativeBalanceNotAllowed();
        }
    }
}
