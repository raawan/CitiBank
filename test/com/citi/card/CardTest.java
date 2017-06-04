package com.citi.card;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.concurrent.CyclicBarrier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * == electronic pre-paid cash card ==
 * <p>
 * Loading money onto the card.
 * · Spending credit balances from the card.
 * · Using the card simultaneously at multiple retailers.
 */
public class CardTest {

    @Test
    public void GIVEN_ZeroBalance_THEN_OnLoading10PoundsCreditBalanceIs10Pound() {

        Card card = new Card();
        card.loadMoney(new BigDecimal("10"));
        assert card.getBalance().equals(new BigDecimal("10"));
    }

    @Test
    public void GIVEN_5Balance_THEN_OnLoading10PoundsCreditBalanceIs10Pound() {
        Card card = new Card(new BigDecimal("5"));
        card.loadMoney(new BigDecimal("10"));
        assert card.getBalance().equals(new BigDecimal("15"));
    }

    @Test
    public void GIVEN_5pound10penceBalance_THEN_OnLoading10pound20pencePoundsCreditBalanceIs15Pound30Pence() {
        Card card = new Card(new BigDecimal("5.10"));
        card.loadMoney(new BigDecimal("10.20"));
        assert card.getBalance().equals(new BigDecimal("15.30"));
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
            card.loadMoney(new BigDecimal("-10"));
        });
    }

    @Test
    public void GIVEN_20PoundBalanceAnd5PoundsToDebit_THEN_BalanceShouldBe15Pounds() {
        Card card = new Card(new BigDecimal("20"));
        card.debit(new BigDecimal("5"));
        assert card.getBalance().equals(new BigDecimal("15"));
    }

    @Test
    public void GIVEN_20Pound70PenceBalanceAnd5Pounds90PenceToDebit_THEN_BalanceShouldBe14Pounds80pence() {
        Card card = new Card(new BigDecimal("20.70"));
        card.debit(new BigDecimal("5.90"));
        assert card.getBalance().equals(new BigDecimal("14.80"));
    }

    @Test
    public void GIVEN_5PoundBalanceAnd7PoundsToDebit_THEN_NegativeBalanceExceptionThrown() {
        Card card = new Card(new BigDecimal("5"));
        assertThrows(NegativeBalanceNotAllowed.class, () -> {
            card.debit(new BigDecimal("7"));
        });
    }

    @Test
    public void GIVEN_MultipleSimultaneousDebits_THEN_correctFunctionality() throws Exception {
        Card card = new Card(new BigDecimal("20001"));
        final int numberOfThreads = 20;
        final int debitPerThread = 1000;
        final BigDecimal debitValue = new BigDecimal("1");

        CyclicBarrier entryBarrier = new CyclicBarrier(numberOfThreads + 1);
        CyclicBarrier exitBarrier = new CyclicBarrier(numberOfThreads + 1);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < debitPerThread; i++) {
                    card.debit(debitValue);
                }
            }
        };

        for (int i = 0; i < numberOfThreads; i++) {
            new SynchedThread(runnable, entryBarrier, exitBarrier).start();
        }

        assertEquals(new BigDecimal("20001"), card.getBalance());
        entryBarrier.await();
        exitBarrier.await();
        assertEquals(new BigDecimal("1"), card.getBalance());
    }
}
