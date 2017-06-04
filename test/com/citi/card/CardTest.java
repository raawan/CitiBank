package com.citi.card;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

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

        Card card = new Card();
        BigDecimal balance = card.loadMoney(new BigDecimal("10"));
        assert balance.equals(new BigDecimal("10"));
    }

    @Test
    public void GIVEN_5Balance_THEN_OnLoading10PoundsCreditBalanceIs10Pound() {
        Card card = new Card(new BigDecimal("5"));
        BigDecimal balance = card.loadMoney(new BigDecimal("10"));
        assert balance.equals(new BigDecimal("15"));
    }

    @Test
    public void GIVEN_5pound10penceBalance_THEN_OnLoading10pound20pencePoundsCreditBalanceIs15Pound30Pence() {
        Card card = new Card(new BigDecimal("5.10"));
        BigDecimal balance = card.loadMoney(new BigDecimal("10.20"));
        assert balance.equals(new BigDecimal("15.30"));
    }

    @Test
    public void NoNegativeBalnceCardIsAllowed() {

        assertThrows(NegativeBalanceNotAllowed.class, () -> {
            Card card = new Card(new BigDecimal("-10"));
        });
    }

    @Test
    public void GIVEN_5Balance_THEN_OnLoadingNegative10Pounds_NegativeBalanceExceptionThrown() {
        Card card = new Card(new BigDecimal("5"));
        assertThrows(NegativeBalanceNotAllowed.class, () -> {
            BigDecimal balance = card.loadMoney(new BigDecimal("-10"));
        });

    }
}
