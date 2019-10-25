package edu.umb.cs681.hw10;

import edu.umb.cs681.hw05.RunnableCancellablePrimeGenerator;

public class RunnableCancellableInterruptiblePrimeGenerator extends RunnableCancellablePrimeGenerator {

    int latency;
	
    public RunnableCancellableInterruptiblePrimeGenerator(long from, long to, int latency) {
        super(from, to);
        this.latency = latency;
    }
	
    public void generatePrimes(){
        for (long n = from; n <= to; n++) {
            // System.out.printf("Considering %d\n", n);
            this.setLock();
            try {
                if(this.isDone()){
                    System.out.println("\tStopped generating prime numbers.");
                    break;
                }
            } finally {
                this.unsetLock();
            };
            if (isPrime(n)){
                // System.out.printf("found the prime %d\n", n);
                this.primes.add(n);
            }
            try {
                Thread.sleep(latency);
            } catch (InterruptedException e) {
                continue;
            }
        }
    }
}
