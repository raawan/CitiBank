package com.citi.card;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by minal on 6/4/17.
 */
public class SynchedThread extends Thread {

    private CyclicBarrier entryBarrier;
    private CyclicBarrier exitBarrier;

    public SynchedThread(Runnable runnable, CyclicBarrier entryBarrier, CyclicBarrier exitBarrier) {
        super(runnable);
        this.entryBarrier = entryBarrier;
        this.exitBarrier = exitBarrier;
    }

    @Override
     public void run() {
        try {
            entryBarrier.await();
            super.run();
            exitBarrier.await();
        } catch(Exception ie) {
            throw new RuntimeException(ie);
        }
    }
}
