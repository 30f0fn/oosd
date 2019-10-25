package edu.umb.cs681.hw10;

public class Main {

    private static void run(long from, long to, int latency, int patience) {
        System.out.printf("\tRunning RunnableCancellableInterruptiblePrimeGenerator\n \t with latency of %dms and patience of %dms...\n", latency, patience);        
        RunnableCancellableInterruptiblePrimeGenerator gen =
            new RunnableCancellableInterruptiblePrimeGenerator(from, to, latency);
        try {
            Thread thread = new Thread(gen);
            thread.start();
            Thread.sleep(patience);
            gen.setDone();
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // System.out.print("\t");
        // gen.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
        // System.out.print("\n");
        System.out.println("\t" + gen.getPrimes().size() + " prime numbers are found.");


    }

    public static void main(String[] args) {
        System.out.println("HW10...\n");
        
        
        run(1, 10000, 1, 1);
        run(1, 10000, 1, 10);
        run(1, 10000, 10, 100);
        run(1, 10000, 10, 1000);
    }
}
