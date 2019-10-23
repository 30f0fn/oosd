package edu.umb.cs681.hw06;

import java.util.LinkedList;
import java.util.ListIterator;

class Factorizer {

    private long dividend;
    private int numThreads;
    private int latency;
    private LinkedList<RunnableCancellablePrimeFactorizer> runnables;
    private LinkedList<Thread> threads;
    private LinkedList<Long> factorsFound; // for iterating by index
    private boolean finished = false;

    protected Factorizer(long dividend, int numThreads, int latency) {

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

    public void stop() {
        setDoneAll();
        joinAll();
        assembleFactors();
    }

    public void finish() {
        joinAll();
        finished = true;
        assembleFactors();
    }

    public LinkedList<Long> getResults() {
        return factorsFound;
    }

    public void printResults() {
        System.out.println("\tFactors found: " + factorsFound + "\n");
    }

    public boolean wasPrime() {
        this.run();
        this.finish();
        return this.factorsFound.size() == 0;
    }

    private void assembleFactors() {
        getFromFactorizers();
        addLastFactor();
    }

    private void getFromFactorizers() {
        // factorsFound inherits sortedness from the runnables
        runnables.stream().forEach((runnable) -> {
            runnable.getPrimeFactors()
            .forEach((maybeNext) -> {
                    {if (toAdd(maybeNext)) {
                        factorsFound.add(maybeNext);
                        }
                    }
                });
            });
        addLastFactor();
    }

    private boolean toAdd(long maybeNext) {
        return factorsFound
            .stream()
            .allMatch((foundFactor) ->
                      foundFactor == maybeNext
                      || maybeNext % foundFactor != 0);
    }

    private void addLastFactor() {
        long toAdd = dividend;
        if (finished) {
            for (long f: factorsFound) {
                toAdd /= f;
            }
            if (toAdd > 1) {
                factorsFound.add(toAdd);
            }
        }
    }

    
    private void joinAll() {
        threads.forEach( (t) -> {
                try {
                    t.join();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            });
        
    }

    private void setDoneAll() {
        runnables.forEach( (r) -> r.setDone());
        // System.out.printf("setDoneAll\n");
        threads.forEach( (t) -> {
                System.out.printf("\t\tStopping process in thread %d\n",
                                  t.getId());
            });
    }

}
