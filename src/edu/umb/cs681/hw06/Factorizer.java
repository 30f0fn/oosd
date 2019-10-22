package edu.umb.cs681.hw06;

import java.util.LinkedList;

class Factorizer {

    private long dividend;
    private int numThreads;
    private int latency;
    private LinkedList<RunnableCancellablePrimeFactorizer> runnables;
    private LinkedList<Thread> threads;
    private LinkedList<Long> factorsFound;

    Factorizer(long dividend, int numThreads, int latency) {

        this.dividend = dividend;
        this.numThreads = numThreads;
        this.latency = latency;

        this.runnables = new LinkedList<RunnableCancellablePrimeFactorizer>();
        this.threads = new LinkedList<Thread>();
        this.factorsFound = new LinkedList<Long>();

        System.out.printf("\tFactorization of %d with %d threads\n",
                          dividend, numThreads);

        long portionSize = ((long)Math.sqrt(dividend) - 1) / numThreads + 1;
        
        for (long i = 1; i <= Math.sqrt(dividend); i += portionSize) {
            RunnableCancellablePrimeFactorizer runnable
                = new RunnableCancellablePrimeFactorizer
                (dividend, i + 1, i + portionSize, latency);
            runnables.add(runnable);

            Thread thread = new Thread(runnable);
            threads.add(thread);

            System.out.printf("\t\tSpawning thread %d to scan from %d to %d\n",
                              thread.getId(), i+1, i + portionSize);
        }
    }


    public void run() {
            
        threads.forEach( (t) -> {
                t.start();
                System.out.printf("\t\tStarting process in thread %d\n", t.getId());
            });
        
    }    

    public void finish() {
        threads.forEach( (t) -> {
                try {
                    t.join();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            });

        runnables.forEach( (factorizer) ->
                           factorsFound.addAll(factorizer.getPrimeFactors()) );
        
    }

    public void stop() {
        runnables.forEach( (r) -> r.setDone());
        threads.forEach( (t) -> {
                System.out.printf("\t\tStopping process in thread %d\n",
                                  t.getId());
            });
        runnables.forEach( (factorizer) ->
                           factorsFound.addAll(factorizer.getPrimeFactors()) );

    }

    public LinkedList<Long> getResults() {
        return factorsFound;
    }

    public void printResults() {

        System.out.println("\tFinal result: " + factorsFound + "\n");
        
    }

}
