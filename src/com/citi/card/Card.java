package com.citi.card;

/**
 * == electronic pre-paid cash card ==
 *
 * Loading money onto the card.
 · Spending credit balances from the card.
 · Using the card simultaneously at multiple retailers.
 */
public class Card {

    private double balance ;

    Card(double balance) {
        this.balance= balance;
    }

    public double loadMoney(double amount) {
        return balance+amount;
    }
}
