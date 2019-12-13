package edu.umb.cs681.hw06;

import java.util.concurrent.locks.ReentrantLock;
import java.util.LinkedList;


import edu.umb.cs681.threads.primes.RunnablePrimeFactorizer;


public abstract class AbstractFactorizerWorker extends RunnablePrimeFactorizer {
    protected boolean done = false;
    protected ReentrantLock lock;
    protected int latency;
    protected long divisor;

    public AbstractFactorizerWorker(long dividend, long from, long to, int latency) {
        super(dividend, from, to);
    }

    public void setDone(){
        setLock();
        try {
            done = true;
        } finally {
            unsetLock();
        }
    }

    public void generatePrimeFactors() {
        while (true) {
            try {
                Thread.sleep(latency);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }  // pace execution to make cancellation predictable (for testing)
            setLock();
            try {
                if (done || dividend == 1 || divisor > to) {
                    return;
                }
            } finally {
                unsetLock();
            }
            factorizingStep();
        }
    }


    protected void factorizingStep() {
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

    protected void setLock() {
        lock.lock();
    }

    protected void unsetLock() {
        lock.unlock();
    }



}

