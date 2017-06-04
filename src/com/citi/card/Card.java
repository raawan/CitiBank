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

    Card() {
        this(new BigDecimal("0"));
    }

    Card(BigDecimal balance) {
        if(balance.compareTo(new BigDecimal("0"))<0) {
            throw new NegativeBalanceNotAllowed();
        }
        this.balance= balance;
    }

    public BigDecimal loadMoney(BigDecimal amount) {
        return this.balance.add(amount);
    }
}
