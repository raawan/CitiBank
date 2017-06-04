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

    /*
    Assuming negative balances are not allowed
     */
    private BigDecimal balance ;
    private static BigDecimal ZERO_VALUE = new BigDecimal("0");

    Card() {
        this(ZERO_VALUE);
    }

    Card(BigDecimal balance) {
        if(balance.compareTo(ZERO_VALUE)<0) {
            throw new NegativeBalanceNotAllowed();
        }
        this.balance= balance;
    }

    public BigDecimal loadMoney(BigDecimal amount) {

        BigDecimal result =  this.balance.add(amount);
        if(result.compareTo(ZERO_VALUE)<0) {
            throw new NegativeBalanceNotAllowed();
        }
        return result;
    }

    public BigDecimal debit(BigDecimal amount) {
        return balance.subtract(amount);
    }
}
