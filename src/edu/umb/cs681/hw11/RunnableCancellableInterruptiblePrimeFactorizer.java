package edu.umb.cs681.hw11;

import edu.umb.cs681.hw06.RunnableCancellablePrimeFactorizer;


public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer {

    RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to, int latency) {
        super(dividend, from, to, latency);
    }

    public void generatePrimeFactors() {
        // the only method here which differs from hw06, and difference seems trivial
        while (true) {
            this.setLock();
            try {
                if (this.isDone() || this.getDividend() == 1 || this.getDivisor() > to) {
                    return;
                }
            } finally {
                this.unsetLock();
            }
            this.factorizingStep();
            // hw06 gave below construct at the top of the loop
            try {
                Thread.sleep(this.getLatency());
            } catch (InterruptedException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

}


