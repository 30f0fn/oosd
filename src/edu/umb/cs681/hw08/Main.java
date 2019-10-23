package edu.umb.cs681.hw08;

import java.util.stream.IntStream;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        System.out.println("HW 08...");
        int numThreads = 100;
        LinkedList<Thread> threads = new LinkedList<Thread>();
        IntStream.range(0, numThreads).forEach((i) -> {
                Thread thread = new Thread(() -> ConcurrentSingleton.getInstance());
                threads.add(thread);
            });
        System.out.println("\tStarting 100 parallel calls of ConcurrentSingleton.getInstance()...");
        threads.forEach((t) -> t.start());
        joinAll(threads);
        System.out.println("\t...finished all calls");
        ConcurrentSingleton.printNumConstructorCalls();
    }

    private static void joinAll(LinkedList<Thread> threads) {
        threads.forEach( (t) -> {
                try {
                    t.join();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            });
    }
}
