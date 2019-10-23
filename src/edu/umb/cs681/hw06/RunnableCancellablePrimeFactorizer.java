package edu.umb.cs681.hw06;

import java.util.concurrent.locks.ReentrantLock;
import java.util.LinkedList;


import edu.umb.cs681.threads.primes.RunnablePrimeFactorizer;


class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
    private boolean done = false;
    private ReentrantLock lock;
    private int latency;
    private long divisor;

    RunnableCancellablePrimeFactorizer(long dividend, long from, long to, int latency) {
        super(dividend, from, to);
        this.factors = new LinkedList<Long>();
        this.latency = latency; // to test cancellation
        this.lock = new ReentrantLock();
        this.divisor = from;
    }

    public void setDone(){
        lock.lock();
        try {
            done = true;
        } finally {
            lock.unlock();
        }
    }

    public void generatePrimeFactors() {
        while (true) {
            try {
                // give other thread a chance to call setDone() on me
                // there must be a better way :(
                Thread.sleep(1);
            }  catch (InterruptedException e) {
                e.printStackTrace();
            } 
            lock.lock();
            if (done || dividend == 1 || divisor > to) {
                lock.unlock();
                return;
            }
            try {
                factorizingStep();
                Thread.sleep(latency);
            }  catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }


    private void factorizingStep() {
        if(divisor > 2 && isEven(divisor)) {
            divisor++;
            return;
        }
        if(dividend % divisor == 0) {
            factors.add(divisor);
            // System.out.printf("\t\tPF: added %d\n", divisor);
            dividend /= divisor;
        } else {
            int increment = (divisor == 2 ? 1 : 2);
            divisor = divisor + increment;
        }
    };


}

