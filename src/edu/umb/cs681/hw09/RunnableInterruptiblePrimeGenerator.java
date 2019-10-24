package edu.umb.cs681.hw09;

import java.util.concurrent.locks.ReentrantLock;
import edu.umb.cs681.threads.primes.RunnablePrimeGenerator;


public class RunnableInterruptiblePrimeGenerator extends RunnablePrimeGenerator {
    private volatile boolean done = false;
    private ReentrantLock lock = new ReentrantLock();
	
    public RunnableInterruptiblePrimeGenerator(long from, long to) {
        super(from, to);
    }

    public void generatePrimes(){
        for (long n = from; n <= to; n++) {
            lock.lock();
            try {
                if (Thread.interrupted()) {
                    // System.out.println("Stopped generating prime numbers.");
                    // this.primes.clear();
                    break;
                }
                if (isPrime(n)) {
                    this.primes.add(n);
                }
            } finally {
                lock.unlock();
            };
        }
    }

}
