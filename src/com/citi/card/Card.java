package com.citi.card;

import java.math.BigDecimal;

/**
 * == electronic pre-paid cash card ==
 *
 * Loading money onto the card.
 · Spending credit balances from the card.
 · Using the card simultaneously at multiple retailers.
 */
public class Card {

    public BigDecimal getBalance() {
        return balance;
    }

    private void setBalance(BigDecimal balance) {
        checkNegativeBalance(balance);
        this.balance = balance;
    }

    /*
            Assuming negative balances are not allowed
             */
    private BigDecimal balance ;
    private static BigDecimal ZERO_VALUE = new BigDecimal("0");

    Card() {
        this(ZERO_VALUE);
    }

    Card(BigDecimal balance) {
        setBalance(balance);
    }

    public void loadMoney(BigDecimal amount) {
        setBalance(this.balance.add(amount));
    }

    public void debit(BigDecimal amount) {
        setBalance(this.balance.subtract(amount));
    }

    private void checkNegativeBalance(BigDecimal result) {
        if(result.compareTo(ZERO_VALUE)<0) {
            throw new NegativeBalanceNotAllowed();
        }
    }
}
