package edu.umb.cs681.hw05;

public class Main {

    public static void main(String[] args) {
        System.out.println("HW05...\n\tRunning RunnableCancellablePrimeGenerator for 1 second...");
        RunnableCancellablePrimeGenerator gen =
            new RunnableCancellablePrimeGenerator(1, 100);
        try {
            Thread thread = new Thread(gen);
            thread.start();
            Thread.sleep(1);
            gen.setDone();
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gen.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
        System.out.println("\n\t" + gen.getPrimes().size() + " prime numbers are found.");
    }
}
