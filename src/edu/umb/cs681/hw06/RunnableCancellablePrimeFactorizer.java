package edu.umb.cs681.hw06;

import java.util.concurrent.locks.ReentrantLock;
import java.util.LinkedList;


import edu.umb.cs681.threads.primes.RunnablePrimeFactorizer;


class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
    private boolean done = false;
    private ReentrantLock lock;
    private int latency;

    RunnableCancellablePrimeFactorizer(long dividend, long from, long to, int latency) {
        super(dividend, from, to);
        this.factors = new LinkedList<Long>();
        this.latency = latency; // to test cancellation
        this.lock = new ReentrantLock();
    }

    public void setDone(){
        lock.lock();
        try {
            done = true;
        } finally {
            lock.unlock();
        }
    }

    private boolean checkDone() {
        System.out.printf(""); // I don't know why it doesn't work without this
        return this.done; 
    }

    public void generatePrimeFactors() {
        long divisor = from;
        while( !checkDone() && dividend != 1 && divisor <= to ){
            try {
                lock.lock();
                Thread.sleep(latency);
                if(divisor > 2 && isEven(divisor)) {
                    divisor++;
                    continue;
                }
                if(dividend % divisor == 0) {
                    Factorizer f = new Factorizer(divisor, 1, 0);
                    if (f.wasPrime()) {
                            factors.add(divisor);
                        }
                    dividend /= divisor;
                }else {
                    if(divisor==2){ divisor++; }
                    else{ divisor += 2; }
                }
            }  catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    
}
