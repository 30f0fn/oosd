package edu.umb.cs681.hw09;

public class Main {

    public static void main(String[] args) {
        System.out.println("HW09...\n\tRunning RunnableInterruptiblePrimeGenerator for 1 ms...");
        RunnableInterruptiblePrimeGenerator gen =
            new RunnableInterruptiblePrimeGenerator(1, 10000000L);
        try {
            Thread thread = new Thread(gen);
            thread.start();
            Thread.sleep(1);
            thread.interrupt();
        thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gen.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
        System.out.println("\n\t" + gen.getPrimes().size() + " prime numbers are found.");
    }
}
