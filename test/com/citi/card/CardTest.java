package com.citi.card;

import org.junit.jupiter.api.Test;

/**
 * == electronic pre-paid cash card ==
 *
 * Loading money onto the card.
 · Spending credit balances from the card.
 · Using the card simultaneously at multiple retailers.
 */
public class CardTest {

    @Test
    public void GIVEN_ZeroBalance_THEN_OnLoading10PoundsCreditBalanceIs10Pound() {

        double balance = Card.loadMoney(10);
        assert balance == 10.00;
    }

    @Test
    public void GIVEN_5Balance_THEN_OnLoading10PoundsCreditBalanceIs10Pound() {

        double balance = Card.loadMoney(10);
        assert balance == 15.00;
    }

}
